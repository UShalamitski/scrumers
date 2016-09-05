<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<fmt:bundle basename="messages">
<ui:html3>

    <c:url value="/searchGenres" var="action" />
    <ui:navbar active="backlog" action="${action}"/>

    <div class="container-fluid padding_top8em"> 
        <form:form  action="savepasswd.html" metod="POST" modelAttribute="user">

            <div class="row">
                <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 ">
                    <c:if test="${not empty messageSuccess && empty errorPasswd && empty errorConfirmed}">
                        <ui:alert color="#3E7048" key="${messageSuccess}" clazz="success" />
                    </c:if>
                    <c:if test="${not empty errorPasswd && empty messageSuccess && empty errorConfirmed}">
                        <ui:alert color="#EE5F5B" key="${errorPasswd}" clazz="danger" />
                    </c:if>
                    <c:if test="${not empty errorConfirmed && empty messageSuccess && empty errorPasswd}">
                        <ui:alert color="#E08217" key="${errorConfirmed}" clazz="warning" />
                    </c:if>
                </div>
            </div>

            <div class="row">
                <div class="pan_head  col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 ">
                    <span class="pan-head-title"><fmt:message key="psswd.edit"/></span>
                </div>
            </div>

            <form:hidden path="id" />

            <div class="row">
                <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 ">
                    <div class="pan_body">
                        <ui:passwd3 name="passwdOld" label="user.pswd.old" labeWidth="4"  />
                        <ui:passwd3 path="passwd" label="user.pswd.new" labeWidth="4" />
                        <ui:passwd3 name="passwdConfirm" label="user.pswd.confirm" labeWidth="4"  />
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="pan_footer  col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 ">
                    <input type="submit" class="but" value="<fmt:message key="save"/>" > 
                </div>
            </div>

        </form:form>
    </div>


</ui:html3>
</fmt:bundle>
