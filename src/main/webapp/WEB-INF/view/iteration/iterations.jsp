<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<ui:html3>

    <c:url value="/searchGenres" var="action" />
    <ui:navbar active="iterations" action="${action}"/>

    <div class="container-fluid padding_top7"> 

        <div class="page">
            <div class="container left-container"> 
                <div class="body">
                    <div class="title">
                        <fmt:message key="iterations"/>
                        <a data-toggle="modal" data-target="#newIterModal" class="float_r not" param_i_id="" i_name="" i_descr="" i_sdate=""  i_edate="">
                            <i class="icon-title fa fa-plus fa-fw pull-right"></i>
                        </a>
                        <c:url value="/iterations.html?table=true" var="href"/>
                        <a href="${href}" class="float_r" >
                            <i class="simple-icon-title fa fa-list-alt fa-fw pull-right"></i>
                        </a>
                    </div>
                    <div class="ul">
                        <c:if test="${empty iterations}">
                            <div class="empty">
                                <fmt:message key="empty"/>
                            </div>
                        </c:if>
                        <c:forEach items="${iterations}" var="iteration" varStatus="status">
                            <c:choose>
                                <c:when test="${active_iteration.id eq iteration.id}">
                                    <c:url value="li-active" var="href" />
                                </c:when>
                                <c:otherwise>
                                    <c:url value="li" var="href" />
                                </c:otherwise>
                            </c:choose>
                            <div class="${href}">
                                <span class=" text_num">#${status.count}</span>
                                <c:url value="/iteration_switch.html?iid=${iteration.id}" var="href"/>
                                <span class=" text-overflow"><a href="${href}">${iteration.name}</a></span>
                                <span class="icons"> 

                                    <sec:authorize access="!hasRole('admin')">
                                        <c:choose>
                                            <c:when test="${iteration.idCreator eq principal}">
                                                <a value="${iteration.id}" data-toggle="modal" data-target="#modalDelete2">
                                                    <i class="del fa fa-trash-o fa-fw pull-right"></i>
                                                </a>
                                                <a  data-toggle="modal" data-target="#newIterModal"  class="link1" param_i_id="${iteration.id}" param_i_name="${iteration.name}" 
                                                                param_i_descr="${iteration.descr}" 
                                                                param_datepicker1="<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${iteration.dateStart}"/>" 
                                                                param_datepicker2="<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${iteration.dateEnd}"/>">
                                                    <i class="edit fa fa-pencil fa-fw pull-right"></i>
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <a   value="" data-toggle="" data-target="">
                                                    <i class="del-table-disabled fa fa-trash-o fa-fw pull-right"></i>
                                                </a>
                                                <a  data-toggle="" data-target="" class="" id="" >
                                                    <i class="edit-table-disabled fa fa-pencil fa-fw pull-right"></i>
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </sec:authorize>

                                    <sec:authorize access="hasRole('admin')">
                                        <a value="${iteration.id}" data-toggle="modal" data-target="#modalDelete2">
                                            <i class="del fa fa-trash-o fa-fw pull-right"></i>
                                        </a>
                                        <a  data-toggle="modal" data-target="#newIterModal" class="link1" param_i_id="${iteration.id}" param_i_name="${iteration.name}" 
                                                                param_i_descr="${iteration.descr}" 
                                                                param_datepicker1="<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${iteration.dateStart}"/>" 
                                                                param_datepicker2="<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${iteration.dateEnd}"/>">
                                            <i class="edit fa fa-pencil fa-fw pull-right"></i>
                                        </a>
                                    </sec:authorize>

                                </span> 
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <div class="container right-container2"> 
                <div class="body">
                    <div class="title">
                        <c:choose>
                            <c:when test="${not empty active_iteration}">
                                <fmt:message key="iteration"/> '${active_iteration.name}'
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="iteration"/>
                            </c:otherwise>
                        </c:choose>
                        <a data-toggle="modal" data-target="#newStoryModal" class="float_r not" param_s_id="" param_s_story_id="0" param_s_status="1" param_s_assignee="" param_s_name="" param_s_descr="" param_s_points="" param_s_estimate="0" param_s_track="" param_s_demo="">
                            <i class="icon-title fa fa-plus fa-fw pull-right"></i>
                        </a>
                        <c:url value="/scrum_board.html" var="href"/>
                        <a href="${href}" class="float_r" >
                            <i class="simple-icon-title fa fa-table fa-fw pull-right"></i>
                        </a>
                    </div>

                        <div class="row">
                            <div class="move col-sm-12">

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
                                                        <ui:element_s2  modalId="#modalDelete1" modalId2="#newStoryModal" story="${story}" st="cl1" />
                                                    </c:when>
                                                    <c:when test="${story.statusId eq 2}">
                                                        <ui:element_s2  modalId="#modalDelete1" modalId2="#newStoryModal" story="${story}"  st="cl2" />
                                                    </c:when>
                                                    <c:when test="${story.statusId eq 3}">
                                                        <ui:element_s2  modalId="#modalDelete1" modalId2="#newStoryModal" story="${story}"  st="cl3" />
                                                    </c:when>
                                                    <c:when test="${story.statusId eq 4}">
                                                        <ui:element_s2  modalId="#modalDelete1" modalId2="#newStoryModal" story="${story}"  st="cl4" />
                                                    </c:when>
                                                </c:choose>
                                            </c:forEach>
                                        </ul>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                        </div>

                </div>
            </div>  
            
        </div>
    </div>

    <ui:modalDelete id="modalDelete2" hidden="param2" action="/iteration_delete.html"/>
    <ui:modalDelete id="modalDelete1" hidden="param1" action="iteration_deleteStory.html"/>
    <fmt:message key="iteration" var="title"/>
    <ui:modalIteration id="newIterModal" name_id="i_name" descr_id="i_descr" action="/iteration_save.html" title="${title}" />
    <fmt:message key="story" var="title"/>
    <ui:modalStory id="newStoryModal" name_id="s_name" descr_id="s_descr" storyPoints_id="s_points"  estimate_id="s_estimate"  track_id="s_track" howToDemo_id="s_demo"  action="iteration_saveStory.html" title="${title}" />
    <fmt:message key="comments" var="comments"/>

<script>
    window.onload = func2a;
    $('.link1').on('click', editModal);
    $('.not').on('click', cleanModal);
    $('.link').on('click', editModal);
    $('.not').on('click', editModal);
    $('.comments').on('click', editModal);
</script>


</ui:html3>
