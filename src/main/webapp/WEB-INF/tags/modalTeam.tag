<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<%@ attribute name="id" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="title" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="team" required="false" rtexprvalue="true" type="com.scrumers.model.Team" %>
<%@ attribute name="input_id" required="false" rtexprvalue="true" type="java.lang.String" %>


<div class="modal-window modal" id="${id}" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-content" >

        <div class="modal-body size0">

            <div class="modal-title">
                ${title}
                <a data-dismiss="modal" class="float_r not" >
                    <i class="icon fa fa-times fa-fw pull-right"></i>
                </a>
            </div>

            <div class="modal-container size0">
                <c:url value="${action}" var="href" />
                <form:form  action="${href}" metod="POST" modelAttribute="teamModel">
                    <input name="id" id="t_id" type="hidden" /> 
                    <jsp:doBody/>
                    <ui:input4 path="name" id="${input_id}" label="team.name" labeWidth="3"  place="team.name.ph" title="team.name"/>
                    <div class="row">
                        <div class="modal_footer">
                            <input type="submit" class="but" value="<fmt:message key="save"/>" > 
                        </div>
                    </div>
                </form:form>
            </div>

        </div>
    </div>
</div>