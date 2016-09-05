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
        <table class="table = title_table">
            <tr>
                <td class="cl1 ">
                    <fmt:message key="scrum.todo"/>
                    <a data-toggle="modal" data-target="#newTaskModal" class="float_r not" param_t_estimate="0" param_t_status="1" param_t_task_id="0" param_t_story_id="${storyId}" param_t_sid="${sid}"  >
                        <i class="title_table_icon fa fa-plus fa-fw pull-right"></i>
                    </a>
                </td>
                <td class="cl2">
                    <fmt:message key="scrum.doing"/>
                    <a data-toggle="modal" data-target="#newTaskModal" class="float_r not" param_t_estimate="0" param_t_status="2" param_t_task_id="0" param_t_story_id="${storyId}" param_t_sid="${sid}" >
                        <i class="title_table_icon fa fa-plus fa-fw pull-right"></i>
                    </a>
                </td>
                <td class=" cl4">
                    <fmt:message key="scrum.done"/>
                    <a data-toggle="modal" data-target="#newTaskModal" class="float_r not" param_t_estimate="0" param_t_status="4" param_t_task_id="0" param_t_story_id="${storyId}" param_t_sid="${sid}"  >
                        <i class="title_table_icon fa fa-plus fa-fw pull-right"></i>
                    </a>
                </td>
            </tr>
        </table>
    </div>
    <div class="table_column">
        <table class="table table1">
        <tr>
            <td class=" column">
                <ul id="sortable1" class="connectedSortable3">
                    <c:forEach items="${tasks}" var="task" varStatus="status">
                        <c:if test="${task.statusId eq 1}">
                            <ui:element_t2  modalId="#myModal" modalId2="#newTaskModal" task="${task}" sid="${sid}" storyId="${storyId}" st="cl1" />
                        </c:if>
                    </c:forEach>
                </ul>
            </td>
            <td class=" column">
                <ul id="sortable2" class="connectedSortable3">
                    <c:forEach items="${tasks}" var="task" varStatus="status">
                        <c:if test="${task.statusId eq 2}">
                            <ui:element_t2  modalId="#myModal" modalId2="#newTaskModal" task="${task}" sid="${sid}" storyId="${storyId}" st="cl2" />
                        </c:if>
                    </c:forEach>
                </ul>
            </td>
            <td class=" column">
                <ul id="sortable3" class="connectedSortable3">
                    <c:forEach items="${tasks}" var="task" varStatus="status">
                        <c:if test="${task.statusId eq 4}">
                            <ui:element_t2  modalId="#myModal" modalId2="#newTaskModal" task="${task}" sid="${sid}" storyId="${storyId}" st="cl4" />
                        </c:if>
                    </c:forEach>
                </ul>
            </td>
        </tr>
        </table>
    </div>

    <div class="footer_table_column ">
        <table class="table footer_table_3col">
            <tr>
                <td>
                    <div id="stat_footer1_item1" class="text"> - tasks </div>
                    <div id="stat_footer1_item2" class="text"> - hours </div>
                </td>
                <td >
                    <div id="stat_footer2_item1" class="text"> - tasks </div>
                    <div id="stat_footer2_item2" class="text"> - hours </div>
                </td>
                <td >
                    <div id="stat_footer3_item1" class="text"> - tasks </div>
                    <div id="stat_footer3_item2" class="text"> - hours </div>
                </td>
            </tr>
        </table>
    </div>

</div>

    <ui:modalDelete id="myModal" hidden="param1" hidden2="param11" hidden3="param12" action="/task_delete.html"/>
    <fmt:message key="story" var="title"/>
    <ui:modalTask id="newTaskModal"  action="task_board_save.html" title="${title}" />


    <script>
        window.onload = func4;
        $('.link').on('click', editModal);
        $('.not').on('click', cleanModal); 
        $('.not').on('click', editModal); 
    </script>

</ui:html3>
