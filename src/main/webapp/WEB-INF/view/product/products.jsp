<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<ui:html3>

    <c:url value="/searchGenres" var="action" />
    <ui:navbar active="products" action="${action}"/>

    <div class="container-fluid padding_top7"> 

        <div class="page">
            <div class="container left-container"> 
                <div class="body">
                    <div class="title">
                        <fmt:message key="products"/>
                        <a data-toggle="modal" data-target="#newProdModal" class="float_r not" param_p_id="" param_p_name="" param_p_descr="">
                            <i class="icon-title fa fa-plus fa-fw pull-right"></i>
                        </a>
                        <sec:authorize access="hasRole('admin')">
                            <c:url value="/product_all.html?table=true" var="href"/>
                        </sec:authorize>
                        <sec:authorize access="!hasRole('admin')">
                            <c:url value="/product_my.html?table=true" var="href"/>
                        </sec:authorize>
                        <a href="${href}" class="float_r" >
                            <i class="simple-icon-title fa fa-list-alt fa-fw pull-right"></i>
                        </a>
                    </div>
                    <div class="ul">
                        <c:if test="${empty products}">
                            <div class="empty">
                                <fmt:message key="empty"/>
                            </div>
                        </c:if>
                        <c:forEach items="${products}" var="product" varStatus="status">
                            <c:choose>
                                <c:when test="${active_product.id eq product.id}">
                                    <c:url value="li-active" var="href" />
                                </c:when>
                                <c:otherwise>
                                    <c:url value="li" var="href" />
                                </c:otherwise>
                            </c:choose>
                            <div class="${href}">
                                <span class=" text_num">#${status.count}</span>
                                <c:url value="/product_switch.html?pid=${product.id}" var="href"/>
                                <span class=" text-overflow"><a href="${href}">${product.name}</a></span>
                                <span class="icons"> 

                                    <sec:authorize access="!hasRole('admin')">
                                        <c:choose>
                                            <c:when test="${product.idCreator eq principal}">
                                                <a   value="${product.id}" data-toggle="modal" data-target="#modalDelete1">
                                                    <i class="del fa fa-trash-o fa-fw pull-right"></i>
                                                </a>
                                                <a  data-toggle="modal" data-target="#newProdModal" class="link" param_p_id="${product.id}" param_p_name="${product.name}" param_p_descr="${product.descr}">
                                                    <i class="edit fa fa-pencil fa-fw pull-right"></i>
                                                </a>
                                            </c:when>
                                            <c:otherwise>
                                                <a   value="" data-toggle="" data-target="">
                                                    <i class="del-table-disabled fa fa-trash-o fa-fw pull-right"></i>
                                                </a>
                                                <a  data-toggle="" data-target="" class="" id="" ">
                                                    <i class="edit-table-disabled fa fa-pencil fa-fw pull-right"></i>
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </sec:authorize>

                                    <sec:authorize access="hasRole('admin')">
                                        <a value="${product.id}" data-toggle="modal" data-target="#modalDelete1">
                                            <i class="del fa fa-trash-o fa-fw pull-right"></i>
                                        </a>
                                        <a  data-toggle="modal" data-target="#newProdModal" class="link" param_p_id="${product.id}" param_p_name="${product.name}" param_p_descr="${product.descr}">
                                            <i class="edit fa fa-pencil fa-fw pull-right"></i>
                                        </a>
                                    </sec:authorize>

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
                            <c:when test="${active_product.idCreator eq principal}">
                                <fmt:message key="product"/> &nbsp '${active_product.name}'
                            </c:when>
                            <c:when test="${empty active_product}">
                                <fmt:message key="teams"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="product"/> &nbsp '${active_product.name}'
                            </c:otherwise>
                        </c:choose>
                        <a data-toggle="modal" data-target="#madalAddTeadToAProdact" class="float_r not" >
                            <i class="icon-title fa fa-plus fa-fw pull-right"></i>
                        </a>
                    </div>
                    <div class="ul">
                        <c:if test="${empty teams}">
                            <div class="empty">
                                <fmt:message key="empty"/>
                            </div>
                        </c:if>
                        <c:forEach items="${teams}" var="team" varStatus="status">
                            <div class="li">
                                <c:url value="/avatar?id=${team.id}" var="href" />
                                <div class="num-block " >
                                    <div class="text" >
                                        ${team.numOfUsers}
                                    </div>
                                </div>
                                <div class="first-row">
                                    <span class=" text_num">#${status.count}</span>
                                    <c:url value="/team_my.html?tid=${team.id}" var="href"/>
                                    <span class=" text-overflow"><a href="${href}">${team.name}</a></span>
                                    <span class="icons"> 
                                    <a data-toggle="modal" data-target="#modalDelete2" class="" value='${team.id}' value2='${active_product.id}'>
                                        <i class="del fa fa-trash-o fa-fw pull-right"></i>
                                    </a>
                                    <a  data-toggle="modal" data-target="#newTeamModal" class="link1" param_t_id="${team.id}" param_t_name="${team.name}">
                                        <i class="edit  fa fa-pencil fa-fw pull-right"></i>
                                    </a>
                                    </span> 
                                </div>
                                <div class="second-row">
                                    <c:choose>
                                        <c:when test="${not empty team.scrumMaster}">
                                            <span class="text-overflow100"><fmt:message key="team.members" /> : ${team.numOfUsers} &nbsp&nbsp | &nbsp&nbsp <fmt:message key="team.role.master" /> : ${team.scrumMaster} &nbsp&nbsp | &nbsp&nbsp Какая-нибудь информация</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-overflow100"><fmt:message key="team.members" /> : ${team.numOfUsers} &nbsp&nbsp | &nbsp&nbsp <fmt:message key="team.role.master" /> : <fmt:message key="team.emptyMaster" /> &nbsp&nbsp | &nbsp&nbsp Какая-нибудь информация</span>
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

    <fmt:message key="product" var="title"/>
    <ui:modalProduct id="newProdModal" name_id="p_name" descr_id="p_descr" action="/product_save.html" title="${title}" />

    <ui:modalDelete id="modalDelete1" hidden="param1" action="/product_delete.html"/>
    <ui:modalDelete id="modalDelete2" hidden="param2" hidden2="param11" action="/product_deleteTeam.html"/>
    <ui:modalAddTeamToAProduct action="product_addTeam.html" id="madalAddTeadToAProdact"/>

    <fmt:message key="team" var="title"/>
    <ui:modalTeam id="newTeamModal" input_id="t_name" action="/product_saveTeam.html" title="${title}">
        <input type="hidden" name="pid" value="${active_product.id}">
    </ui:modalTeam>

<script>
    $('.link').on('click',editModal);
    $('.link1').on('click',editModal);
    $('.not').on('click', cleanModal);
</script>


</ui:html3>
