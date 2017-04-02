package com.scrumers

import groovy.sql.Sql
import org.apache.tools.ant.filters.ReplaceTokens
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Copy

class DataBasePlugin implements Plugin<Project> {

    private static final String POSTGRES_DRIVER = "org.postgresql.Driver"
    private static final String LIQUIBASE_PLUGIN = "org.liquibase.gradle"
    private static final String CONFIGURATION_SCHEMA = "configuration"
    private static final String DATABASE_URL = "jdbc:postgresql://"
    private static final String LOCAL_DB = "localDB"

    private String changelog
    private String buildResourceDir
    private String srcResourceDir
    private String scriptsDir
    private String liquibaseDir

    @Override
    void apply(Project project) {
        project.plugins.apply(LIQUIBASE_PLUGIN)
        initVariables(project)
        initJdbcDriver(project);
        configureLiquibase(project)
        addTasks(project);
    }

    def initVariables(Project project) {
        srcResourceDir = "${project.projectDir}/src/main/resources"
        buildResourceDir = "${project.buildDir}/resources/main"
        scriptsDir = buildResourceDir + "/scripts"
        liquibaseDir = buildResourceDir + "/liquibase"

        if (project.hasProperty(LOCAL_DB) == "true") {
            changelog = "${liquibaseDir}/main-local-changelog.xml"
        } else {
            changelog = "${liquibaseDir}/main-changelog.xml"
        }
    }

    def initJdbcDriver(Project project) {
        // Finds a jdbc driver
        File driver = project.buildscript.configurations.classpath.find { File file ->
            file.name.contains(project.properties.jdbcPostgresqlVersion)
        }
        // Loads and registers a JDBC driver for database access
        URLClassLoader loader = GroovyObject.class.classLoader
        loader.addURL(driver.toURI().toURL())
    }

    def configureLiquibase(Project project) {
        project.liquibase {
            activities {
                main {
                    url("${DATABASE_URL}${project.properties.scrumersDbHost}/${project.properties.scrumersDbName}")
                    username(project.properties.scrumersDbAdminUser)
                    password(project.properties.scrumersDbAdminPassword)
                    defaultSchemaName(CONFIGURATION_SCHEMA)
                    changeLogFile(changelog)
                    classpath "/."
                }
            }
        }
    }

    def addTasks(Project project) {

        project.task([type: Copy, description: 'Prepares resources', group: 'Database', dependsOn: [project.clean]], "prepareResources") {
            from(srcResourceDir) {
                filteringCharset = "UTF-8"
            }
            eachFile { fileDetails ->
                if (!fileDetails.file.text.contains("logicalFilePath")) {
                    filter { line ->
                        line.replaceAll '<databaseChangeLog', '<databaseChangeLog logicalFilePath="' + fileDetails.path + '" '
                    }
                }
                filter(ReplaceTokens, tokens: [
                        path            : liquibaseDir,
                        scrumersDbName  : project.properties.scrumersDbName,
                        schema: project.properties.scrumersDbSchema
                ])
            }
            into(buildResourceDir)
        }

        project.task([description: 'Creates the database', group: 'Database', dependsOn: [project.prepareResources]], 'createDatabase') {
            doLast {
                def sql;
                try {
                    sql = Sql.newInstance("${DATABASE_URL}${project.properties.scrumersDbHost}/", "${project.properties.scrumersDbRootUser}",
                            "${project.properties.scrumersDbRootPassword}", POSTGRES_DRIVER)
                    sql.execute(project.file(scriptsDir + "/db_create.sql").text)
                } finally {
                    sql.close()
                }
            }
        }

        project.task([description: 'Creates the schema', group: 'Database', dependsOn: [project.prepareResources]], 'createSchemas') {
            doLast {
                def sql;
                try {
                    sql = Sql.newInstance("${DATABASE_URL}${project.properties.scrumersDbHost}/${project.properties.scrumersDbName}",
                            "${project.properties.scrumersDbRootUser}", "${project.properties.scrumersDbRootPassword}", POSTGRES_DRIVER)
                    sql.execute(project.file(scriptsDir + "/schemas.sql").text)
                } finally {
                    sql.close()
                }
            }
        }

        project.task([description: 'Drops the database', group: 'Database', dependsOn: [project.prepareResources]], 'dropDatabase') {
            doLast {
                def sql;
                try {
                    sql = Sql.newInstance("${DATABASE_URL}${project.properties.scrumersDbHost}/", "${project.properties.scrumersDbRootUser}",
                            "${project.properties.scrumersDbRootPassword}", POSTGRES_DRIVER)
                    sql.execute(project.file(scriptsDir + "/db_drop.sql").text)
                } finally {
                    sql.close()
                }
            }
        }

        project.task([description: 'Updates the database', dependsOn: [project.prepareResources, project.update], group: 'Database'], "updateDatabase") {
        }

        project.task([description: 'Rolls back the last <liquibaseCommandValue> change sets the database', group: 'Database',
                      dependsOn  : [project.rollbackCount]], 'rollbackCountDatabase') {
        }

        project.task([description: 'Rolls back the database to the state it was in when the <liquibaseCommandValue> tag was applied',
                      group      : 'Database', dependsOn: [project.rollback]], 'rollbackDatabase') {
        }

        project.task([description: 'Rolls back the database to the state it was in the <liquibaseCommandValue> date/time',
                      group      : 'Database', dependsOn: [project.rollbackToDate]], 'rollbackToDateDatabase') {
        }
    }

}
