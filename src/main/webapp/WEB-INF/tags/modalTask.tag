<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<%@ attribute name="id" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="title" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" rtexprvalue="true" type="java.lang.String" %>

<div class="modal-window modal" id="${id}" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-content" >

        <div class="modal-body size2">

            <div class="modal-title">
                ${title}
                <a data-dismiss="modal" class="float_r not" >
                    <i class="icon fa fa-times fa-fw pull-right"></i>
                </a>
            </div>

            <div class="modal-container size2">
                <c:url value="${action}" var="href" />
                <form:form  action="${href}" metod="POST" modelAttribute="taskModel">
                    <form:hidden path="id"  id="t_id"/>
                    <form:hidden path="taskId" id="t_task_id"/>
                    <form:hidden path="statusId" id="t_status"/>
                    <input type="hidden" id="t_sid" name="sid" >
                    <input type="hidden" id="t_story_id" name="storyId" >
                    <ui:textarea2 path="summary" id="t_summary" label="task.summary" labeWidth="3"  place="task.summary" title="task.summary"/>
                    <ui:number path="estimatePre" id="t_estimate" label="story.estimate" labeWidth="3" title="story.estimate.title"/>
                    <div class="row form-group">
                        <div class="col-sm-3"> 
                            <fmt:message key="story.assignee" var="label"/>
                            <label class="pan-body-title"><spring:message code="${label}" text="${label}" /></label>
                        </div>
                        <div class="col-sm-9"> 
                            <select id="t_assignee" name="assignee" class="">
                                <option value="0" label="-">-</option>
                                <c:forEach items="${users}" var="user">
                                    <option value="${user.id}" label="${user.fname} ${user.sname}">${user.fname} ${user.sname}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="modal_footer">
                            <input type="submit" class="but" value="<fmt:message key="save"/>" > 
                        </div>
                    </div>
                </form:form>
            </div>

        </div>
    </div>
</div>