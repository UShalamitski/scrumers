<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>


<ui:html3>

    <c:url value="/searchGenres" var="action" />
    <ui:navbar active="backlog" action="${action}"/>

    <div class="container-fluid padding_top8em"> 
        <form:form  action="saveuserinfo.html" metod="POST" modelAttribute="user" enctype="multipart/form-data">

            <div class="row">
                <div class="pan_head  col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 ">
                    <span class="pan-head-title"><fmt:message key="user.profile"/></span>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 ">
                    <div class="pan_body">
                        <form:hidden path="name" />
                        <form:hidden path="id" />
                        <ui:input3 path="fname" label="user.fname" labeWidth="3" place="fname.enter" title="fname.enter"/>
                        <ui:input3 path="sname" label="user.sname" labeWidth="3" place="sname.enter" title="sname.enter"/>
                        <ui:input3 path="email" label="user.email" labeWidth="3" place="user.email.enter" title="user.email.enter"/>
                        <ui:input3 path="phone" label="user.phone" labeWidth="3" place="user.phone.enter" title="user.phone.enter"/>
                        <ui:file path="avatar" label="user.avatar"  />
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

