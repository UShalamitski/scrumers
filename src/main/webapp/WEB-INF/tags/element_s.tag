<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<%@ attribute name="story" required="true" rtexprvalue="true" type="com.scrumers.model.Story" %>
<%@ attribute name="modalId" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="modalId2" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="delActionComment" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="st" required="false" rtexprvalue="true" type="java.lang.String" %>

<li id="item_${story.id}" class="${st}" estimate="${story.estimate}" points="${story.storyPoints}">
    <div class=myrow>
        <span class="text_num">#${story.storyId}</span>
        <span class=" text-overflow"><a href="task_board.html?sid=${story.id}&storyId=${story.storyId}">${story.name}</a></span>
        <span class="icons"> 
            <a name="id" value="${story.id}" data-toggle="modal" data-target="${modalId}" >
                <i class="del fa fa-trash-o fa-fw pull-right"></i>
            </a>
            <a id="${story.id}" data-toggle="modal" data-target="${modalId2}" class="link" param_s_id="${story.id}" param_s_storyId="${story.storyId}" param_s_status="${story.statusId}" param_s_assignee="${story.assignee}" param_s_name="${story.name}" param_s_descr="${story.descr}" param_s_points="${story.storyPoints}" param_s_estimate="${story.estimate}" param_s_track="${story.track}" param_s_demo="${story.howToDemo}">
                <i class="edit fa fa-pencil fa-fw pull-right"></i>
            </a>
                <c:set var="commentsHtml" value="" />
                <c:set var="nameClass" value="'comment-userName'" />
                <c:set var="timeClass" value="'comment-time'" />
                <c:set var="deleteClass" value="'comment-delete'" />
                <fmt:message key="delete" var="delete"/>
                <c:forEach items="${story.comments}" var="comment">
                    <fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm:ss" value="${comment.dateComment}" var="dateComment"/> 
                    <c:url value="/${delActionComment}.html?cid=${comment.id}&login=${comment.login}" var="href"/>
                    <c:set var="href" value="'${href}'" />
                    <c:set var="commentsHtml" value="${commentsHtml.concat('<span class=')}"/>
                    <c:set var="commentsHtml" value="${commentsHtml.concat(nameClass)}"/>
                    <c:set var="commentsHtml" value="${commentsHtml.concat('>')}"/>
                    <c:set var="commentsHtml" value="${commentsHtml.concat(comment.login)}"/>
                    <c:set var="commentsHtml" value="${commentsHtml.concat('</span><span class=')}"/>
                    <c:set var="commentsHtml" value="${commentsHtml.concat(timeClass)}"/>
                    <c:set var="commentsHtml" value="${commentsHtml.concat('>')}"/>
                    <c:set var="commentsHtml" value="${commentsHtml.concat(dateComment)}"/>
                    <c:set var="commentsHtml" value="${commentsHtml.concat('</span>')}"/>
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name eq comment.login}">
                            <c:set var="commentsHtml" value="${commentsHtml.concat('<span class=')}"/>
                            <c:set var="commentsHtml" value="${commentsHtml.concat(timeClass)}"/>
                            <c:set var="commentsHtml" value="${commentsHtml.concat('>')}"/>
                            <c:set var="commentsHtml" value="${commentsHtml.concat('<a href=')}"/>
                            <c:set var="commentsHtml" value="${commentsHtml.concat(href)}"/>
                            <c:set var="commentsHtml" value="${commentsHtml.concat('class=')}"/>
                            <c:set var="commentsHtml" value="${commentsHtml.concat(deleteClass)}"/>
                            <c:set var="commentsHtml" value="${commentsHtml.concat(' >')}"/>
                            <c:set var="commentsHtml" value="${commentsHtml.concat(delete)}"/>
                            <c:set var="commentsHtml" value="${commentsHtml.concat('</a></span>')}"/>
                        </c:when>
                        <c:otherwise>
                            <sec:authorize ifAnyGranted="admin">
                                <c:set var="commentsHtml" value="${commentsHtml.concat('<span class=')}"/>
                                <c:set var="commentsHtml" value="${commentsHtml.concat(timeClass)}"/>
                                <c:set var="commentsHtml" value="${commentsHtml.concat('>')}"/>
                                <c:set var="commentsHtml" value="${commentsHtml.concat('<a href=')}"/>
                                <c:set var="commentsHtml" value="${commentsHtml.concat(href)}"/>
                                <c:set var="commentsHtml" value="${commentsHtml.concat('class=')}"/>
                                <c:set var="commentsHtml" value="${commentsHtml.concat(deleteClass)}"/>
                                <c:set var="commentsHtml" value="${commentsHtml.concat(' >')}"/>
                                <c:set var="commentsHtml" value="${commentsHtml.concat(delete)}"/>
                                <c:set var="commentsHtml" value="${commentsHtml.concat('</a></span>')}"/>
                            </sec:authorize>
                        </c:otherwise>
                    </c:choose>

                    <c:set var="commentsHtml" value="${commentsHtml.concat('<br><br>')}"/>
                    <c:set var="commentsHtml" value="${commentsHtml.concat(comment.text)}"/>
                    <c:set var="commentsHtml" value="${commentsHtml.concat('<hr>')}"/>
                </c:forEach>

            <a id="${story.id}" data-toggle="modal" data-target="#commentsModal" class="comments" param_story_id_comment="${story.id}" param_story_comments="${commentsHtml}">
                <i class="comment fa fa-comment-o fa-fw pull-right"></i>
            </a>
        </span>  
    </div>  
</li>


