<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ attribute name="id" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="hidden" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="hidden2" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="hidden3" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="title" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" rtexprvalue="true" type="java.lang.String" %>




    <div class="portfolio-modal modal" id="${id}" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">

            <div class="container-fluid modal-body">
                <div class="row modal-title2">
                    <div class="col-sm-offset-3 col-sm-4">
                        <h1><fmt:message key="delete.warning"/></h1>
                    </div>
                    <a data-dismiss="modal" class="float_r not" >
                        <i class="icon fa fa-times fa-fw pull-right"></i>
                    </a>
                </div>
                <div class="row">
                    <div class="col-sm-offset-3 col-sm-7">
                        <h3><fmt:message key="delete.confirmation"/></h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-offset-3 col-sm-3" >
                        <c:url value="${action}" var="href" />
                        <form action="${href}" method="POST">
                            <input id="${hidden}" type="hidden" name="id" value="">
                            <jsp:doBody/>
                            <c:if test="${not empty hidden2}">
                                <input id="${hidden2}" type="hidden" name="id2" value="">
                            </c:if>
                            <c:if test="${not empty hidden3}">
                                <input id="${hidden3}" type="hidden" name="id3" value="">
                            </c:if>
                            <button type="submit" class="btn btn-red" ><fmt:message key="yes"/></button>
                            <button type="button" class="btn btn-red" data-dismiss="modal"><fmt:message key="no"/></button>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>