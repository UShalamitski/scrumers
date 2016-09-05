<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<ui:html3>

    <c:url value="/searchGenres" var="action" />
    <ui:navbar active="products" action="${action}"/>

    <div class="container-fluid padding_top9"> 
            <c:if test="${not empty errorUserName}">
                <div class="col-sm-12 col-md-10 col-md-offset-1">
                            <ui:alert color="#EA4949" key="${errorUserName}" clazz="danger" />
                </div>
            </c:if>
            <div class="row">
                <div class="pan_head  col-sm-12 col-md-10 col-md-offset-1">
                    <span class="pan-head-title"><fmt:message key="products"/></span>
                    <a data-toggle="modal" data-target="#newProdModal" class="float_r not" param_p_id="" param_p_name="" param_p_descr="">
                        <i class="pan_head_icon fa fa-plus fa-fw pull-right"></i>
                    </a>
                    <sec:authorize access="hasRole('admin')">
                        <c:url value="/product_all.html" var="href"/>
                    </sec:authorize>
                    <sec:authorize access="!hasRole('admin')">
                        <c:url value="/product_my.html" var="href"/>
                    </sec:authorize>
                    <a href="${href}" class="float_r" >
                        <i class="simple_pan_head_icon fa fa-th-list fa-fw pull-right"></i>
                    </a>
                </div>
            </div>

            <div class="row">
                <div class="pan_body_table col-sm-12 col-md-10 col-md-offset-1 ">
                    <sec:authorize access="hasRole('admin')">
                        <ui:productsAdmin/>
                    </sec:authorize>
                    <sec:authorize access="!hasRole('admin')">
                        <ui:products/>
                    </sec:authorize>
                </div>
            </div>

            <div class="row">
                <div class="pan_footer  col-sm-12 col-md-10 col-md-offset-1 ">
                    <a type="submit" class="but not" data-toggle="modal" data-target="#newProdModal" param_p_id="" param_p_name="" param_p_descr=""><fmt:message key="product.new.add"/></a>
                </div>
            </div>

    </div>

    <fmt:message key="product.owner" var="title"/>
    <ui:modaChangeOwner id="cahngeOnwerProdModal" action="/product_change_owner.html" title="${title}" hidden="param2" />
    <ui:modalDelete id="myModal" hidden="param1" action="/product_delete.html?table=true"/>
    <fmt:message key="product" var="title"/>
    <ui:modalProduct id="newProdModal" name_id="p_name" descr_id="p_descr" action="/product_save.html?table=true" title="${title}" />

<script>
    $('.link').on('click',editModal);
    $('.not').on('click',cleanModal);
</script>

</ui:html3>

