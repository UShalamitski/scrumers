<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@ attribute name="path" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="type" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="name" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="label" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="labeWidth" required="false" rtexprvalue="true" type="java.lang.Integer" %>
<%@ attribute name="value" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="glyph" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="passwd" required="false" rtexprvalue="true" type="java.lang.Boolean" %>
<%@ attribute name="error" required="false" rtexprvalue="true" type="java.lang.String" %>
<c:if test="${not empty path or not empty name or not empty type}">
<div class="form-group${not empty error?' ' + error:''}">
    <spring:message code="${label}" text="${label}" var="msg" />
    <label class="col-sm-${labeWidth} control-label">${msg}</label>
    <div class="col-sm-${12-labeWidth}">
    <div class="${glyph != null?' input-group':''}">
        <c:if test="${not empty path}">
        <form:input path="${path}" cssClass="form-control" />
        </c:if>

        <c:if test="${empty path and not empty name}">
        <input class="form-control" type="${passwd?'password':'text'}" name="${name}" value="${value}">
        </c:if>

        <c:if test="${empty path and empty name and not empty type}">
        <input type="${type}" name="${value}"/>
        </c:if>

        <c:if test="${not empty glyph}">
        <span class="input-group-addon"><ui:glyph glyph="${glyph}"/></span>
        </c:if>
    </div>
    </div>
</div>
</c:if>
