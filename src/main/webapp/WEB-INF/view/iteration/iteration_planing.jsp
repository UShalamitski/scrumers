<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
    prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<ui:html3>
    <c:url value="/searchGenres" var="action" />
    <ui:navbar active="planing" action="${action}" />

    <div class="container-fluid padding_top8em">
            <div class="myrow2 sort">

                <div  id="stories" class="planning-holder">

                    <div class="pan_head_left ">
                        <span class="pan-head-title"><fmt:message key="product.backlog" /></span>
                    </div>
                    <div class="pan_head_right ">
                        <span class="pan-head-title"><fmt:message key="iteration.backlog" /></span>
                    </div>

                    <div class=" prokrutka-left">
                        <ul id="sortable1" class="connectedSortable2">
                            <c:forEach items="${stories_b}" var="story" varStatus="status">
                                <c:choose>
                                    <c:when test="${story.statusId eq 1}">
                                        <ui:element_s2  modalId="#myModal1" modalId2="#newStoryModal" story="${story}"  st="cl1" />
                                    </c:when>
                                    <c:when test="${story.statusId eq 2}">
                                        <ui:element_s2  modalId="#myModal1" modalId2="#newStoryModal" story="${story}"  st="cl2" />
                                    </c:when>
                                    <c:when test="${story.statusId eq 3}">
                                        <ui:element_s2  modalId="#myModal1" modalId2="#newStoryModal" story="${story}"  st="cl3" />
                                    </c:when>
                                    <c:when test="${story.statusId eq 4}">
                                        <ui:element_s2  modalId="#myModal1" modalId2="#newStoryModal" story="${story}"  st="cl4" />
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class=" prokrutka-right">
                        <ul id="sortable2" class="connectedSortable2">
                            <c:forEach items="${stories_i}" var="story_i" varStatus="status">
                                <c:choose>
                                    <c:when test="${story_i.statusId eq 1}">
                                        <ui:element_s2  modalId="#myModal2" modalId2="#newStoryModal" story="${story_i}"  st="cl1" />
                                    </c:when>
                                    <c:when test="${story_i.statusId eq 2}">
                                        <ui:element_s2  modalId="#myModal2" modalId2="#newStoryModal" story="${story_i}"  st="cl2" />
                                    </c:when>
                                    <c:when test="${story_i.statusId eq 3}">
                                        <ui:element_s2  modalId="#myModal2" modalId2="#newStoryModal" story="${story_i}"  st="cl3" />
                                    </c:when>
                                    <c:when test="${story_i.statusId eq 4}">
                                        <ui:element_s2  modalId="#myModal2" modalId2="#newStoryModal" story="${story_i}"  st="cl4" />
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </ul>
                    </div>

                    <div id="stat_footer1" class="pan_foot_left ">
                        <div id="stat_footer1_item1" class="text"> - stories</div>
                        <div id="stat_footer1_item3" class="text"> - hours</div>
                    </div>
                    <div id="stat_footer1" class="pan_foot_right ">
                        <div id="stat_footer2_item1" class="text"> - stories</div>
                        <div id="stat_footer2_item3" class="text"> - hours</div>
                    </div>

                </div>
            </div>
    </div>

    <ui:modalDelete id="myModal2" hidden="param1" action="/iteration_planning_del_from_iter.html"/>
    <ui:modalDelete id="myModal1" hidden="param2" action="/iteration_planning_del_from_backlog.html"/>
    <fmt:message key="story" var="title"/>
    <ui:modalStory id="newStoryModal" name_id="s_name" descr_id="s_descr" storyPoints_id="s_points"  estimate_id="s_estimate"  track_id="s_track" howToDemo_id="s_demo"  action="iteration_planing_save_story.html" title="${title}" />
    <fmt:message key="comments" var="comments"/>

    <script>
        window.onload = func3;
        $('.link').on('click', editModal);
        $('.not').on('click', cleanModal);
        $('.comments').on('click', editModal);
    </script>

</ui:html3>
