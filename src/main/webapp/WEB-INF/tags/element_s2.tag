<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<%@ attribute name="story" required="true" rtexprvalue="true" type="com.scrumers.model.Story" %>
<%@ attribute name="modalId" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="modalId2" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="st" required="false" rtexprvalue="true" type="java.lang.String" %>

<li id="item_${story.id}" class="${st} story-item" estimate="${story.estimate}" points="${story.storyPoints}">
    <div class="myrow first-row">
        <div class="left-box">
            ${story.storyPoints}
        </div>
        <div class="right-box">
            <div class=" text_num">#${story.storyId}</div>
            <div class=" text-overflow"><a href="task_board.html?sid=${story.id}&storyId=${story.storyId}">${story.name}</a></div>
            <span class="icons"> 
                <a name="id" value="${story.id}" data-toggle="modal" data-target="${modalId}" >
                    <i class="del fa fa-trash-o fa-fw pull-right"></i>
                </a>
                <a id="${story.id}" data-toggle="modal" data-target="${modalId2}" class="link" param_s_id="${story.id}" param_s_story_id="${story.storyId}" param_s_status="${story.statusId}" param_s_assignee="${story.assignee}" param_s_name="${story.name}" param_s_descr="${story.descr}" param_s_points="${story.storyPoints}" param_s_estimate="${story.estimate}" param_s_track="${story.track}" param_s_demo="${story.howToDemo}">
                    <i class="edit fa fa-pencil fa-fw pull-right"></i>
                </a> 
                <c:url value="/${actionComment}?sid=${story.id}" var="href"/>
                <a id="${story.id}" href="${href}" >
                    <i class="comment fa fa-comment-o fa-fw pull-right"></i>
                </a>
            </span> 
        </div>
    </div>
    <div class="myrow second-row">
        <div class="first-box">
            <c:choose>
                <c:when test="${story.assignee > 1}">
                    ${story.devName}
                </c:when>
                <c:otherwise>
                    <fmt:message key="story.notassigned"/>
                </c:otherwise>
            </c:choose>
            
        </div>
        <div class="second-box">
            <c:choose>
                <c:when test="${story.estimate eq 0}">
                    &nbsp ${story.doneTasks}/${story.allTasks} &nbsp ?h 
                </c:when>
                <c:otherwise>
                    &nbsp ${story.doneTasks}/${story.allTasks} &nbsp ${story.estimate}h 
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</li>


