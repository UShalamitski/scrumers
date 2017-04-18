<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<%@ attribute name="task" required="true" rtexprvalue="true" type="com.scrumers.model.Task" %>
<%@ attribute name="modalId" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="modalId2" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="st" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="sid" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="storyId" required="true" rtexprvalue="true" type="java.lang.String" %>

<li id="item_${task.id}" sid="${sid}" estimate="${task.estimatePre}" class="${st} task-item">

    <div class="myrow first-row">
        <div class="right-box">
            <div class=" text-num">#${storyId}.${task.taskId}</div>
            <div class=" text-overflow">${task.summary}</div>
            <div class="icons"> 
                <a name="id" value="${task.id}" value2="${sid}" value3="${storyId}" data-toggle="modal" data-target="${modalId}" >
                    <i class="del fa fa-trash-o fa-fw pull-right"></i>
                </a>
                <a data-toggle="modal" data-target="${modalId2}" class="link" param_t_sid="${sid}" param_t_story_id="${storyId}" param_t_id="${task.id}" param_t_task_id="${task.taskId}" param_t_status="${task.status}" param_t_summary="${task.summary}" param_t_estimate="${task.estimatePre}"  param_t_assignee="${task.assignee}" >
                    <i class="edit fa fa-pencil fa-fw pull-right"></i>
                </a> 
                <c:url value="/${actionComment}?tid=${task.id}" var="href"/>
                <a id="${task.id}" href="${href}" >
                    <i class="comment fa fa-comment-o fa-fw pull-right"></i>
                </a>
            </div>  
        </div>
    </div>  
    <div class="myrow second-row">
        <div class="first-box">
            <c:choose>
                <c:when test="${task.assignee > 1}">
                    ${task.devName}
                </c:when>
                <c:otherwise>
                    <fmt:message key="story.notassigned"/>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="second-box">
            <c:choose>
                <c:when test="${task.estimatePre eq 0}">
                    ?h 
                </c:when>
                <c:otherwise>
                    ${task.estimatePre}h 
                </c:otherwise>
            </c:choose>
        </div>
    </div>


        </span>  
    </div>  
</li>


