<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@ attribute name="path"      required="false"  rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="label"     required="false" rtexprvalue="true" type="java.lang.String" %>

<div class="file">
    <div class="left-file">
        <c:url value="/avatar?id=${user.id}" var="href" />
        <img class="img-info" src="${href}">
    </div>
    <div class="right-file" >
        <input type="file" accept="image/*" name="file" />
    </div>
</div>
