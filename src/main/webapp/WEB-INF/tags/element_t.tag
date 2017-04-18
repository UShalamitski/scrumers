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

<li id="item_${task.id}" sid="${sid}" class="${st}">
    <div class=myrow>
        <span class="text_num">#${storyId}.${task.taskId}</span>
        <span class=" text-overflow">${task.summary}</span>
        <span class="icons"> 
            <a name="id" value="${task.id}" value2="${sid}" value3="${storyId}" data-toggle="modal" data-target="${modalId}" >
                <i class="del fa fa-trash-o fa-fw pull-right"></i>
            </a>
            <a data-toggle="modal" data-target="${modalId2}" class="link" story_id="${sid}" storyId="${storyId}" id="${task.id}" taskId="${task.taskId}" statusId="${task.status}" t_summary="${task.summary}" t_estimatePre="${task.estimatePre}" t_estimateReal="${task.estimateReal}" t_assignee="${task.assignee}" >
                <i class="edit fa fa-pencil fa-fw pull-right"></i>
            </a> 
        </span>  
    </div>  
</li>


