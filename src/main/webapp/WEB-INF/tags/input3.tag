<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@ attribute name="path"      required="true"  rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="label"     required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="labeWidth" required="true"  rtexprvalue="true" type="java.lang.Integer" %>
<%@ attribute name="pat"       required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="place"     required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="title"     required="false" rtexprvalue="true" type="java.lang.String" %>

<fmt:message key="${place}" var="holder"/>
<fmt:message key="${title}" var="title"/>
<spring:bind path="${path}"><c:if test="${status.error}" var="haserror"/></spring:bind>

<div class="row form-group ${haserror?' has-error':''}">
    <div class="col-sm-${labeWidth}"> 
        <label class="pan-body-title"><spring:message code="${label}" text="${label}" /></label>
    </div> 
    <div class="col-sm-${12-labeWidth}"> 
        <form:input path="${path}" pattern="${pat}"  placeholder="${holder}" title="${title}"/>
    </div> 
    <form:errors path="${path}" cssClass="col-sm-offset-${labeWidth} col-sm-${12-labeWidth} text-error" element="div"/>
</div>



