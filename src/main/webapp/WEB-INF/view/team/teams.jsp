<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<ui:html3>

    <c:url value="/searchGenres" var="action" />
    <ui:navbar active="teams" action="${action}"/>

    <div class="container-fluid padding_top7"> 

        <div class="page">
            <div class="container left-container"> 
                <div class="body">
                    <div class="title">
                        <fmt:message key="teams"/>
                        <a data-toggle="modal" data-target="#newTeamModal" class="float_r not" >
                            <i class="icon-title fa fa-plus fa-fw pull-right"></i>
                        </a>
                        <sec:authorize access="hasRole('admin')">
                            <c:url value="/team_all.html?table=true" var="href"/>
                        </sec:authorize>
                        <sec:authorize access="!hasRole('admin')">
                            <c:url value="/team_my.html?table=true" var="href"/>
                        </sec:authorize>
                        <a href="${href}" class="float_r" >
                            <i class="simple-icon-title fa fa-list-alt fa-fw pull-right"></i>
                        </a>
                    </div>
                    <div class="ul">
                        <c:if test="${empty teams}">
                            <div class="empty">
                                <fmt:message key="empty"/>
                            </div>
                        </c:if>
                        <c:forEach items="${teams}" var="team" varStatus="status">
                            <c:choose>
                                <c:when test="${active_team.id eq team.id}">
                                    <c:url value="li-active" var="href" />
                                </c:when>
                                <c:otherwise>
                                    <c:url value="li" var="href" />
                                </c:otherwise>
                            </c:choose>
                            <div class="${href}">
                                <span class=" text_num">#${status.count}</span>
                                <c:choose>
                                    <c:when test="${sessionScope['mode'] eq 'all'}">
                                        <c:url value="/team_all.html?tid=${team.id}" var="href" />
                                    </c:when>
                                    <c:when test="${sessionScope['mode'] eq 'my'}">
                                        <c:url value="/team_my.html?tid=${team.id}" var="href" />
                                    </c:when>
                                </c:choose>
                                
                                <span class=" text-overflow"><a href="${href}">${team.name}</a></span>
                                <span class="icons"> 
                                     <a   value="${team.id}" data-toggle="modal" data-target="#modalDelete">
                                        <i class="del fa fa-trash-o fa-fw pull-right"></i>
                                    </a>
                                    <a  data-toggle="modal" data-target="#newTeamModal" class="link1" param_t_id="${team.id}" param_t_name="${team.name}">
                                        <i class="edit fa fa-pencil fa-fw pull-right"></i>
                                    </a>
                                </span> 
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <div class="container right-container"> 
                <div class="body">
                    <div class="title">
                        <c:choose>
                            <c:when test="${active_team.idCreator eq principal}">
                                <fmt:message key="team"/> &nbsp '${active_team.name}'
                            </c:when>
                            <c:when test="${empty active_team}">
                                <fmt:message key="users"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="team"/> &nbsp '${active_team.name}'
                            </c:otherwise>
                        </c:choose>
                        <a data-toggle="modal" data-target="#addUser" value="${active_team}" class="float_r not" >
                            <i class="icon-title fa fa-plus fa-fw pull-right"></i>
                        </a>
                    </div>
                    <div class="ul">
                        <c:if test="${empty users}">
                            <div class="empty">
                                <fmt:message key="empty"/>
                            </div>
                        </c:if>
                        <c:forEach items="${users}" var="user" varStatus="status">
                            <div class="li">
                                <c:url value="/avatar?id=${user.id}" var="href" />
                                <div class="image" style="background: #aaa url(${href}); background-size: cover; background-position:center;">
                                </div>
                                <div class="first-row">
                                    <span class=" text_num">#${status.count}</span>
                                    <span class=" text-overflow">${user.fname} &nbsp ${user.sname}</span>
                                    <span class="icons"> 
                                    <c:choose>
                                        <c:when test="${active_team.scrumMasterId eq principal or active_team.idCreator eq principal}">
                                            <a data-toggle="modal" data-target="#modalDelete2" class="link" value='${user.id}'>
                                                <i class="del fa fa-trash-o fa-fw pull-right"></i>
                                            </a>
                                            <a data-toggle="modal" data-target="#changeTeamRole" class="link" value='${user.id}'>
                                                <i class="edit  fa fa-pencil fa-fw pull-right"></i>
                                            </a>
                                        </c:when>
                                        <c:otherwise>

                                            <sec:authorize access="!hasRole('admin')">
                                                <a data-toggle="" data-target="" class="" value=''>
                                                    <i class="del-table-disabled fa fa-trash-o fa-fw pull-right"></i>
                                                </a>
                                                <a data-toggle="" data-target="" class="" value=''>
                                                    <i class="edit-table-disabled  fa fa-pencil fa-fw pull-right"></i>
                                                </a>
                                            </sec:authorize>

                                            <sec:authorize access="hasRole('admin')">
                                                <a data-toggle="modal" data-target="#modalDelete2" class="link" value='${user.id}'>
                                                <i class="del fa fa-trash-o fa-fw pull-right"></i>
                                                </a>
                                                <a data-toggle="modal" data-target="#changeTeamRole" class="link" value='${user.id}'>
                                                    <i class="edit  fa fa-pencil fa-fw pull-right"></i>
                                                </a>
                                            </sec:authorize>

                                        </c:otherwise>
                                    </c:choose>

<!--                                         <a data-toggle="modal" data-target="#" class="link" value=''>
                                            <i class="comment fa fa-comment-o fa-fw pull-right"></i>
                                        </a> -->
                                    </span> 
                                </div>
                                <div class="second-row">
                                    <c:choose>
                                        <c:when test="${user.teamRoleId eq 1}">
                                            <span class="text-overflow100"><fmt:message key="team.role.master" /> &nbsp&nbsp | &nbsp&nbsp ${user.email} &nbsp&nbsp | &nbsp&nbsp ${user.phone}</span>
                                        </c:when>
                                        <c:when test="${user.teamRoleId eq 2}">
                                            <span class="text-overflow100"><fmt:message key="team.role.developer" /> &nbsp&nbsp | &nbsp&nbsp ${user.email} &nbsp&nbsp | &nbsp&nbsp ${user.phone}</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-overflow100"><fmt:message key="team.role.unknown" /> &nbsp&nbsp | &nbsp&nbsp ${user.email} &nbsp&nbsp | &nbsp&nbsp ${user.phone}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                </div>
            </div>
            
        </div>
    </div>

    <fmt:message key="user" var="title"/>
    <ui:modalChangeMemberRole id="changeTeamRole" action="/addMember.html" title="${title}" hidden="param2" />
    <ui:modalAddUser id="addUser" action="/addMember.html" title="${title}" hidden="param3" />
    <fmt:message key="team" var="title"/>
    <ui:modalTeam id="newTeamModal" input_id="t_name" action="/team_save.html" title="${title}" />
    <ui:modalDelete id="modalDelete" hidden="param1" action="/team_delete.html" />
    <ui:modalDelete id="modalDelete2" hidden="param4" action="/deleteMember.html">
        <input type="hidden" value='${active_team.id}' name='tid'>
    </ui:modalDelete>

<script>
    $('.link1').on('click',editModal);
    $('.not').on('click',cleanModal);
</script>


</ui:html3>
