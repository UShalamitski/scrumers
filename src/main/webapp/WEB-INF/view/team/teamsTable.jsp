<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<ui:html3>

    <c:url value="/searchGenres" var="action" />
    <ui:navbar active="teams" action="${action}"/>

    <div class="container-fluid padding_top9"> 

            <div class="row">
                <div class="pan_head  col-sm-12 col-md-10 col-md-offset-1">
                    <span class="pan-head-title"><fmt:message key="team.my"/></span>
                    <a data-toggle="modal" data-target="#newTeamModal" class="float_r not" param_t_id="" param_t_name="" >
                        <i class="pan_head_icon fa fa-plus fa-fw pull-right"></i>
                    </a>
                    <sec:authorize access="hasRole('admin')">
                        <c:url value="/team_all.html" var="href"/>
                    </sec:authorize>
                    <sec:authorize access="!hasRole('admin')">
                        <c:url value="/team_my.html" var="href"/>
                    </sec:authorize>
                    <a href="${href}" class="float_r" >
                        <i class="simple_pan_head_icon fa fa-th-list-alt fa-fw pull-right"></i>
                    </a>
                </div>
            </div>

            <div class="row">
                <div class="pan_body_table col-sm-12 col-md-10 col-md-offset-1 ">
                    <sec:authorize access="hasRole('admin')">
                        <ui:teamsAdmin/>
                    </sec:authorize>
                    <sec:authorize access="!hasRole('admin')">
                        <ui:teams/>
                    </sec:authorize>
                </div>
            </div>

            <div class="row">
                <div class="pan_footer  col-sm-12 col-md-10 col-md-offset-1 ">
                    <input type="submit" class="but not" data-toggle="modal" data-target="#newTeamModal" value="<fmt:message key="team.new.add"/>" > 
                </div>
            </div>
    </div>

    <ui:modalDelete id="myModal" hidden="param1" action="/team_delete.html?table=true"/>
    <fmt:message key="team" var="title"/>
    <ui:modalTeam id="newTeamModal" input_id="t_name" action="/team_save.html?table=true" title="${title}" />

<script>
    $('.link1').on('click',editModal);
    $('.not').on('click',cleanModal);
</script>

</ui:html3>
