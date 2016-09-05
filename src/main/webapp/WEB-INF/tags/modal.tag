<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ attribute name="id" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="title" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" rtexprvalue="true" type="java.lang.String" %>
<div class="modal fade" id="${id}">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">${title}</h4>
            </div>
            <div class="modal-body">
                <jsp:doBody />
            </div>
            <div class="modal-footer">
                <c:url value="${action}" var="href" />
                <form action="${href}" method="GET">
                    <input id="param" type="hidden" name="id" value="">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="${modal.close}" text="Close"/></button>
                    <button type="submit" class="btn btn-danger"><spring:message code="${modal.ok}" text="Ok"/></button>
                </form>
            </div>
        </div>
    </div>
</div>
