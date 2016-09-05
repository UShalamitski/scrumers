<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<ui:html3>

    <div class="container-fluid">

        <c:url value="/searchGenres" var="action" />
        <ui:navbar active="board" action="${action}"/>

        <div class="title_table_column padding_top4_275em">
            <table class="table title_table_4col">
                <tr>
                    <td class="cl1 ">
                        <fmt:message key="scrum.todo"/>
                        <a data-toggle="modal" data-target="#newStoryModal" class="float_r not" param_s_status="1" param_s_story_id="0" param_s_estimate="0" >
                            <i class="title_table_icon fa fa-plus fa-fw pull-right"></i>
                        </a>
                    </td>
                    <td class="cl2">
                        <fmt:message key="scrum.doing"/>
                        <a data-toggle="modal" data-target="#newStoryModal" class="float_r not" param_s_status="2" param_s_story_id="0" param_s_estimate="0">
                            <i class="title_table_icon fa fa-plus fa-fw pull-right"></i>
                        </a>
                    </td>
                    <td class=" cl3">
                        <fmt:message key="scrum.reviewing"/>
                        <a data-toggle="modal" data-target="#newStoryModal" class="float_r not" param_s_status="3" param_s_story_id="0" param_s_estimate="0">
                            <i class="title_table_icon fa fa-plus fa-fw pull-right"></i>
                        </a>
                    </td>
                    <td class=" cl4">
                        <fmt:message key="scrum.done"/>
                        <a data-toggle="modal" data-target="#newStoryModal" class="float_r not" param_s_status="4" param_s_story_id="0" param_s_estimate="0">
                            <i class="title_table_icon fa fa-plus fa-fw pull-right"></i>
                        </a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="table_column">
            <table class="table table1_4col">
            <tr>
                <td class=" column">
                    <ul id="sortable1" class="connectedSortable4">
                        <c:forEach items="${stories}" var="story" varStatus="status">
                            <c:if test="${story.statusId eq 1}">
                                <ui:element_s2  modalId="#myModal" modalId2="#newStoryModal" story="${story}"  st="cl1" />
                            </c:if>
                        </c:forEach>
                    </ul>
                </td>
                <td class=" column">
                    <ul id="sortable2" class="connectedSortable4">
                        <c:forEach items="${stories}" var="story" varStatus="status">
                            <c:if test="${story.statusId eq 2}">
                                <ui:element_s2  modalId="#myModal" modalId2="#newStoryModal" story="${story}"  st="cl2" />
                            </c:if>
                        </c:forEach>
                    </ul>
                </td>
                <td class=" column">
                    <ul id="sortable3" class="connectedSortable4">
                        <c:forEach items="${stories}" var="story" varStatus="status">
                            <c:if test="${story.statusId eq 3}">
                                <ui:element_s2  modalId="#myModal" modalId2="#newStoryModal" story="${story}"  st="cl3" />
                            </c:if>
                        </c:forEach>
                    </ul>
                </td>
                <td class="column">
                    <ul id="sortable4" class="connectedSortable4">
                        <c:forEach items="${stories}" var="story" varStatus="status">
                            <c:if test="${story.statusId eq 4}">
                                <ui:element_s2  modalId="#myModal" modalId2="#newStoryModal" story="${story}"  st="cl4" />
                            </c:if>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
            </table>
        </div>


        <div class="footer_table_column ">
            <table class="table footer_table_4col">
                <tr>
                    <td>
                        <div id="stat_footer1_item1" class="text"> - stories </div>
                        <div id="stat_footer1_item2" class="text"> - hours </div>
                    </td>
                    <td >
                        <div id="stat_footer2_item1" class="text"> - stories </div>
                        <div id="stat_footer2_item2" class="text"> - hours </div>
                    </td>
                    <td >
                        <div id="stat_footer3_item1" class="text"> - stories </div>
                        <div id="stat_footer3_item2" class="text"> - hours </div>
                    </td>
                    <td>
                        <div id="stat_footer4_item1" class="text"> - stories </div>
                        <div id="stat_footer4_item2" class="text"> - hours </div>
                    </td>
                </tr>
            </table>
        </div>
        
</div>


    <ui:modalDelete id="myModal" hidden="param1" action="/board_del.html"/>
    <fmt:message key="story" var="title"/>
    <ui:modalStory id="newStoryModal" name_id="s_name" descr_id="s_descr" storyPoints_id="s_points"  estimate_id="s_estimate"  track_id="s_track" howToDemo_id="s_demo"  action="story_scrum_board_save.html" title="${title}" />
    <fmt:message key="comments" var="comments"/>
    <script>
        window.onload = func1;
        $('.link').on('click', editModal);
        $('.not').on('click', cleanModal); 
        $('.not').on('click', editModal);
        $('.comments').on('click', editModal);
    </script>

</ui:html3>
