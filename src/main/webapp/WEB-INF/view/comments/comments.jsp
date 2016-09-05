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

    <div class="container-fluid padding_top5em"> 

        <div class="comments" >

            <div class="head">
                <a class="icon" href="${url_back}">
                    <i class="icon-back fa fa-arrow-circle-left fa-fw"></i>
                </a>
                <div class="right-head-container">
                    <div class="first-row"> <fmt:message key="comments"/></div>
                    <div class="second-row"> <fmt:message key="story"/>: &nbsp #${story.storyId} ${story.name}</div>
                </div>
            </div>

            <c:url value="${url_new}" var="href" />
            <form id="myform"  action="${href}" method="POST">
                <div class="body">
                    <ul>
                        <c:forEach items="${comments}" var="comment">
                            <c:choose>
                                <c:when test="${uid eq comment.userId}">
                                    <li class="right">
                                </c:when>
                                <c:otherwise>
                                    <li class="left">
                                </c:otherwise>
                            </c:choose>
                                <c:url value="/avatar?id=${comment.userId}" var="href" />
                                <div class="image" style="background: #aaa url(${href}); background-size: cover; background-position:center;"></div>
                                <div class="comment-body">  
                                    <div class="first-row">
                                        <sec:authorize access="hasRole('admin')">
                                            ${comment.name} &nbsp | &nbsp <fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${comment.dateComment}"/> &nbsp | &nbsp 
                                            <c:url value="${url_del}?cid=${comment.id}&sid=${story.id}&login=${comment.login}" var="href" />
                                            <a href="${href}"><fmt:message key="comment.del"/></a>
                                        </sec:authorize>
                                        <sec:authorize access="!hasRole('admin')">
                                            <c:choose>
                                                <c:when test="${uid eq comment.userId}">
                                                    ${comment.name} &nbsp | &nbsp <fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${comment.dateComment}"/> &nbsp | &nbsp 
                                                    <c:url value="${url_del}?cid=${comment.id}&sid=${story.id}" var="href" />
                                                    <a href="${href}"><fmt:message key="comment.del"/></a>
                                                </c:when>
                                                <c:otherwise>
                                                    ${comment.name} &nbsp | &nbsp <fmt:formatDate type="both" pattern="yyyy-MM-dd HH:mm" value="${comment.dateComment}"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </sec:authorize>
                                    </div>
                                    <div class="second-row">
                                        ${comment.text}
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
    
                <div  class="footer">
                    <input type="hidden" name="sid" value="${story.id}"/> 
                    <textarea name="comment"></textarea>
                    <a type="submit" onclick='document.forms["myform"].submit(); return false;' class="float_r" >
                        <i class="icon fa fa-plus fa-fw pull-right"></i>
                    </a>
                </div>
            </form>

        </div>



    </div>

</ui:html3>
