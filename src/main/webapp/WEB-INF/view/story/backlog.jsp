<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<ui:html3>

    <div class="container-fluid"> 

        <c:url value="/searchGenres" var="action" />
        <ui:navbar active="${active}" action="${action}"/>

        <div class="row">
            <div class="head margin-top-8em col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3">
                <div class="right-head-container">
                    <c:choose>
                        <c:when test="${active eq 'storyList'}">
                            <div class="first-row"> <fmt:message key="iteration.backlog"/></div>
                            <div class="second-row"> <fmt:message key="iteration"/>: &nbsp ${name}</div>
                        </c:when>
                        <c:otherwise>
                            <div class="first-row"> <fmt:message key="product.backlog"/></div>
                            <div class="second-row"> <fmt:message key="product"/>: &nbsp ${name}</div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <a data-toggle="modal" data-target="#newStoryModal" class="float_r not" param_s_status="1" param_s_story_id="0" param_s_estimate="0">
                    <i class="icon-add fa fa-plus fa-fw pull-right"></i>
                </a>
                <c:if test="${active eq 'storyList'}">
                    <c:url value="/scrum_board.html" var="href"/>
                    <a href="${href}" class="float_r" >
                        <i class="icon-board fa fa-table fa-fw pull-right"></i>
                    </a>
                    <c:url value="/iteration_Burndown.html" var="href"/>
                    <a href="${href}" class="float_r" >
                        <i class="icon-chart fa fa-line-chart fa-fw pull-right"></i>
                    </a>
                </c:if>
            </div>
        </div>

        <div class="row">
            <div class="move col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3">
                <c:choose>
                    <c:when test="${empty stories}">
                        <div class="empty-body">
                            <div class="empty">
                                <fmt:message key="empty"/>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                    <ul id="sortable1" class="connectedSortable1">
                        <c:forEach items="${stories}" var="story" varStatus="status">
                            <c:choose>
                                <c:when test="${story.statusId eq 1}">
                                    <ui:element_s2  modalId="#myModal" modalId2="#newStoryModal" story="${story}" st="cl1" />
                                </c:when>
                                <c:when test="${story.statusId eq 2}">
                                    <ui:element_s2  modalId="#myModal" modalId2="#newStoryModal" story="${story}"  st="cl2" />
                                </c:when>
                                <c:when test="${story.statusId eq 3}">
                                    <ui:element_s2  modalId="#myModal" modalId2="#newStoryModal" story="${story}"  st="cl3" />
                                </c:when>
                                <c:when test="${story.statusId eq 4}">
                                    <ui:element_s2  modalId="#myModal" modalId2="#newStoryModal" story="${story}"  st="cl4" />
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </ul>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>

            <div class="row">
                <div class="pan_footer col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3">
                    <input type="submit" class="but not" data-toggle="modal" data-target="#newStoryModal" value="<fmt:message key="story.new"/>" > 
                </div>
            </div>

    </div>

    <ui:modalDelete id="myModal" hidden="param1" action="${url_del}"/>
    <fmt:message key="story" var="title"/>
    <ui:modalStory id="newStoryModal" name_id="s_name" descr_id="s_descr" storyPoints_id="s_points"  estimate_id="s_estimate"  track_id="s_track" howToDemo_id="s_demo"  action="${url_new}" title="${title}" />
    <fmt:message key="comments" var="comments"/>

    <script>
        $('.link').on('click', editModal);
        $('.not').on('click', cleanModal);
        $('.not').on('click', editModal);
        window.onload = ${func};
        $('.comments').on('click', editModal);
    </script>

</ui:html3>
