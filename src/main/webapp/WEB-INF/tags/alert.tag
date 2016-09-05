<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ attribute name="clazz"     required="true"  rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="color"     required="true"   rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="key"       required="true"   rtexprvalue="true" type="java.lang.String" %>

<div class="alert alert-${clazz}" role="alert">
  <strong style="color: ${color};">
    <fmt:message key="${key}" />
  </strong>
</div>