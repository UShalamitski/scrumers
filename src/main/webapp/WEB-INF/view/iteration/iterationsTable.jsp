<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<ui:html3>

    <c:url value="/searchGenres" var="action" />
    <ui:navbar active="iterations" action="${action}"/>

    <div class="container-fluid padding_top9"> 

            <div class="row">
                <div class="pan_head  col-sm-12 col-md-10 col-md-offset-1">
                    <span class="pan-head-title"><fmt:message key="iterations"/></span>
                    <a data-toggle="modal" data-target="#newIterModal" class="float_r not" >
                        <i class="pan_head_icon fa fa-plus fa-fw pull-right"></i>
                    </a>
                    <c:url value="/iterations.html" var="href"/>
                    <a href="${href}" class="float_r" >
                        <i class="simple_pan_head_icon fa fa-th-list fa-fw pull-right"></i>
                    </a>
                </div>
            </div>

            <div class="row">
                <div class="pan_body_table  col-sm-12 col-md-10 col-md-offset-1 ">
                    <ui:iterations/>
                </div>
            </div>

            <%-- div class="row">
                <div class="pan_line col-sm-12 col-md-10 col-md-offset-1">
                </div>
            </div--%>

            <div class="row">
                <div class="pan_footer  col-sm-12 col-md-10 col-md-offset-1 ">
                    <input type="submit" class="but not" data-toggle="modal" data-target="#newIterModal" value="<fmt:message key="iteration.new"/>" param_i_id="" i_name="" i_descr="" i_sdate=""  i_edate=""> 
                </div>
            </div>

    </div>

    <ui:modalDelete id="myModal" hidden="param1" action="/iteration_delete.html?table=true"/>
    <fmt:message key="iteration" var="title"/>
    <ui:modalIteration id="newIterModal" name_id="i_name" descr_id="i_descr" action="/iteration_save.html?table=true" title="${title}" />

<script>
    $('.link').on('click',editModal);
    $('.not').on('click',cleanModal);
</script>

</ui:html3>
