<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<%@ attribute name="id" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="hidden" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="title" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" rtexprvalue="true" type="java.lang.String" %>




    <div class="portfolio-modal modal" id="${id}" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">

            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl"></div>
                </div>
            </div>

            <div class="container-fluid modal-body" style="background-color: #F2F2F2">
                <div class="row">
                    <div class="col-sm-offset-4 col-sm-4">
                        <h1 class="title">${title}</h1>
                    </div>
                </div>
                <div class="modal-container size1">
                    <c:url value="${action}" var="href" />
                    <form action="${href}" method="POST">
                        <input id="${hidden}" type="hidden" name="prod" value="">
                        <div class="row">
                            <div class="col-sm-offset-4 col-sm-6">
                                <ui:input5 name="userName" label="user.name" labeWidth="4"  place="user.name.ph"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-offset-4 col-sm-3" >
                                    <button type="submit" class="btn btn-normal" ><fmt:message key="save"/></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
         </div>
    </div>