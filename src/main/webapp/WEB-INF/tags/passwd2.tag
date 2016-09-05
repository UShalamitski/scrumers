<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@ attribute name="path"      required="false"  rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="label"     required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="labeWidth" required="true"  rtexprvalue="true" type="java.lang.Integer" %>
<%@ attribute name="glyph"     required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="name"      required="false"  rtexprvalue="true" type="java.lang.String"%>
<c:if test="${not empty path}">
<spring:bind path="${path}"><c:if test="${status.error}" var="haserror"/></spring:bind>
</c:if>
<fmt:message key="passwd.enter" var="holder"/>
<fmt:message key="${label}" var="label"/>
<div class="form-group${haserror?' has-error':''}">
  <label class="col-sm-${labeWidth} control-label"><spring:message code="${label}" text="${label}" /></label>
  <div class="col-sm-${12-labeWidth}">
        <div class="${glyph != null?' input-group':''}">
        <input type="password" class="form-control" name="${name}" placeholder="${holder}">
            <c:if test="${not empty glyph}">
                <span class="input-group-addon"><ui:glyph glyph="${glyph}"/></span>
            </c:if>
       </div>
  </div>

  <form:errors path="${path}" cssClass="col-sm-offset-${labeWidth} col-sm-${12-labeWidth} text-danger" element="div"/>
</div>
