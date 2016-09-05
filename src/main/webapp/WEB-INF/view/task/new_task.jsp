<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<ui:html3>

    <c:url value="${saveAction}" var="action" />
    <ui:navbar active="backlog" action="${action}"/>

    <c:url value="task_board_save.html" var="href" />
    <div class="container-fluid padding_top9"> 
        <form:form  action="${href}" metod="POST" modelAttribute="task">
            <form:hidden path="id" id="t_id"/>
            <form:hidden path="statusId" id="t_statusId"/>

            <input type="hidden" name="storyId" value="${storyId}"/>
            <input type="hidden" name="sid" value="${sid}"/>

            <div class="row">
                <div class="pan_head  col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 ">
                    <span class="pan-head-title"><fmt:message key="story.new"/></span>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 ">
                    <div class="pan_body">
                        <ui:input3 path="summary" label="story.name" labeWidth="3"  place="story.name" title="story.name.title"/>
                        <ui:input3 path="estimatePre" label="story.estimate" labeWidth="3" pat="[0-9]+"  place="story.estimate" title="story.estimate.title"/>
                        <div class="row form-group">
                            <div class="col-sm-3"> 
                                <fmt:message key="story.assignee" var="label"/>
                                <label class="pan-body-title"><spring:message code="${label}" text="${label}" /></label>
                            </div>
                            <div class="col-sm-9"> 
                                <form:select id="select" path="assignee">
                                    <form:option value="0" label="?">?</form:option>
                                    <c:forEach items="${users}" var="user">
                                        <c:choose>
                                            <c:when test="${assignee eq user.id}">
                                                <form:option value="${user.id}" label="${user.fname} ${user.sname}">${user.fname} ${user.sname}</form:option>
                                            </c:when>
                                            <c:otherwise>
                                                <form:option value="${user.id}" label="${user.fname} ${user.sname}">${user.fname} ${user.sname}</form:option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>
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
