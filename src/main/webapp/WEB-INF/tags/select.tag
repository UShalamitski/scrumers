<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@ attribute name="path"      required="true"  rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="label"     required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="labeWidth" required="true"  rtexprvalue="true" type="java.lang.Integer" %>
<%@ attribute name="glyph"     required="false" rtexprvalue="true" type="java.lang.String" %>

<div class="row form-group ${haserror?' has-error':''}">
    <div class="col-sm-${labeWidth}"> 
      <label class="pan-body-title"><spring:message code="${label}" text="${label}" /></label>
    </div>
    <div class="col-sm-${12-labeWidth}"> 
        <select name="${path}" class="">
            <c:forEach items="${roles}" var="role">
                <option value="${role.id}" label="${role.name}"><fmt:message key="team.role.${role.name}"/></option>
            </c:forEach>
        </select>
    </div>
</div>