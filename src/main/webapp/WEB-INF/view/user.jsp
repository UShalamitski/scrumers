<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@ taglib uri="http://java.scrumers.com/jsp/ui/functions" prefix="uf"%>

<c:if test="${not empty errorRoles}">
    <c:url value="style='color:#ee5f5b;'" var="err" />
</c:if>

<fmt:bundle basename="messages">
    <ui:html>
        <div id="wrapper" class="page pad_top11">
            <div class="container">

                <ui:navbar active="newuser" />
                <c:url value="/save.html" var="href" />
                <div class="row">
                    <div class="col-sm-offset-3 col-sm-6">
                        <form:form cssClass="form-horizontal" action="${href}" method="POST" modelAttribute="user" enctype="multipart/form-data">
                            <form:hidden path="id" />

                            <div class="panel">
                                <div class="panel-heading">
                                    <h4><fmt:message key="user" /></h4>
                                </div>
                                <div class="panel-body">
                                    <ui:input2 path="name" label="user.name" labeWidth="4" place="login.enter" glyph="user"/>
                                    <c:if test="${user.id == null}">
                                        <ui:passwd path="passwd" label="psswd" labeWidth="4" glyph="lock" />
                                    </c:if>
                                        <ui:input2 path="fname" label="user.fname" labeWidth="4" place="fname.enter"/>
                                        <ui:input2 path="sname" label="user.sname" labeWidth="4" place="sname.enter"/>
                                        <ui:file path="avatar" label="user.avatar" labeWidth="4" />
                                        <div class="col-sm-12">
                                            <label class="col-sm-3 control-label" ${err} ><fmt:message key="roles" /></label>
                                            <c:forEach items="${roles}" var="role" varStatus="status">
                                                <div class="${status.first?'':'col-sm-offset-3 '}col-sm-4">
                                                    <div class="checkbox">
                                                        <label> 
                                                            <input type="checkbox" name="role" value="${role.id}"${uf:conteins(role, userRoles) ? ' checked' : ''}>
                                                            ${role.name} ${uf:getProperty(role, 'id')}
                                                        </label>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                            <c:if test="${not empty errorRoles}">
                                                <div class="row" style="color: #ee5f5b;">
                                                    <div class="col-sm-5 col-sm-offset-3">
                                                        <fmt:message key="${errorRoles}" />
                                                    </div>
                                                </div>
                                            </c:if>
                                            <div class="col-sm-4 col-sm-offset-1">
                                            <c:url value="/avatar?id=${user.id}" var="href" />
                                            <img class="img-thumbnail" src="${href}">
                                            </div>
                                        </div>
                                </div>

                                <div class="panel-footer align_r">
                                    <div class="form-group">
                                        <div class="col-sm-offset-3 col-sm-9">
                                            <button class="btn btn-primary" type="submit">
                                                <fmt:message key="save" />
                                            </button>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </form:form>
                    </div>
                </div>

            </div>
        </div>

        <ui:footer/>

    </ui:html>
</fmt:bundle>
