<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ attribute name="title" required="true" rtexprvalue="true" type="java.lang.String" %>
<h1><fmt:message key="${title}"/></h1>
