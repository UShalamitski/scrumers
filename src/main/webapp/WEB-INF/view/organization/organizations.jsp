<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<ui:html3>

    <c:url value="/searchGenres" var="action" />
    <ui:navbar active="organizations" action="${action}"/>
    <div class="container-fluid padding_top9"> 

            <div class="row">
                <div class="pan_head  col-sm-12 col-md-10 col-md-offset-1">
                    <c:if test="${mode eq 'my'}">
                        <span class="pan-head-title"><fmt:message key="organizations"/></span>
                    </c:if>
                    <c:if test="${mode eq 'all'}">
                        <span class="pan-head-title"><fmt:message key="organizationsAll"/></span>
                    </c:if>
                    <a data-toggle="modal" data-target="#newOrgModal" class="float_r not" param_o_id="" param_o_name="">
                        <i class="pan_head_icon fa fa-plus fa-fw pull-right"></i>
                    </a>
                </div>
            </div>

            <div class="row">
                <div class="pan_body_table  col-sm-12 col-md-10 col-md-offset-1 ">
                    <sec:authorize access="hasRole('admin')">
                        <ui:organizationsAdmin/>
                    </sec:authorize>
                    <sec:authorize access="!hasRole('admin')">
                        <ui:organizations/>
                    </sec:authorize>
                </div>
            </div>

            <div class="row">
                <div class="pan_footer  col-sm-12 col-md-10 col-md-offset-1 ">
                    <input type="submit" class="but not" data-toggle="modal" data-target="#newOrgModal" value="<fmt:message key="organization.new"/>" param_o_id="" param_o_name=""> 
                </div>
            </div>

    </div>

    <ui:modalDelete id="myModal" hidden="param1" action="/organization_delete.html"/>
    <fmt:message key="organization" var="title"/>
    <ui:modalOrganization id="newOrgModal" name_id="o_name"  action="/organization_save.html" title="${title}" />

<script>
    $('.link').on('click',editModal);
    $('.not').on('click',cleanModal);
</script>

</ui:html3>
