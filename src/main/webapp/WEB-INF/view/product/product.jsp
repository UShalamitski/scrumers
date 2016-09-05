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
        <c:choose>
            <c:when test="${table eq true}">
                <c:url value="/product_save.html?table=true" var="href" />
            </c:when>
            <c:otherwise>
                <c:url value="/product_save.html" var="href" />
            </c:otherwise>
        </c:choose>
        <form:form  action="${href}" metod="POST" modelAttribute="product">

            <div class="row">
                <div class="pan_head  col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 ">
                    <span class="pan-head-title"><fmt:message key="iteration.new"/></span>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 ">
                    <div class="pan_body">
                        <form:hidden path="id"/>
                        <ui:input3 path="name" label="product.name" title="product.name" labeWidth="3"  place="product.name.ph"/>
                        <ui:textarea path="descr" label="description" title="description" labeWidth="3"  place="product.descr.ph"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="pan_footer  col-sm-10 col-sm-offset-1 col-md-8 col-md-offset-2 col-lg-6 col-lg-offset-3 ">
                    <input type="submit" class="but" value="<fmt:message key="save"/>" > 
                </div>
            </div>

        </form:form>
    </div>

</ui:html3>
