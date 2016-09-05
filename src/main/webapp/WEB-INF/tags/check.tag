<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@ attribute name="path"      required="true"  rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="label"     required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="labeWidth" required="true"  rtexprvalue="true" type="java.lang.Integer" %>
<fmt:message key="${label}" var="holder"/>
<div class="form-group">
    <label class="col-sm-${labeWidth} control-label"><spring:message code="${holder}" text="${holder}" /></label>
    <div class="col-sm-${12-labeWidth}">
        <div class="checkbox">
            <label>
                <form:checkbox path="${path}"/>
            </label>
        </div>
    </div>
</div>
