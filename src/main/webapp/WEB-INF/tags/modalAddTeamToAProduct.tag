<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<%@ attribute name="id" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" rtexprvalue="true" type="java.lang.String" %>


<div class="modal-window modal" id="${id}" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-content" >

        <div class="modal-body size0">

            <div class="modal-title">
               <fmt:message key="teams"/>
                <a data-dismiss="modal" class="float_r not" >
                    <i class="icon fa fa-times fa-fw pull-right"></i>
                </a>
            </div>

            <div class="modal-container size0">
                <c:url value="${action}" var="href" />
                <form:form  action="${href}" metod="POST">

                    <input type="hidden" name="pid" value="${active_product.id}">

                    <div class="row form-group">
                        <div class="col-sm-3"> 
                            <fmt:message key="team" var="label"/>
                            <label class="pan-body-title"><spring:message code="${label}" text="${label}" /></label>
                        </div>
                        <div class="col-sm-9"> 
                            <select name="tid" class="">
                                <c:forEach items="${all_teams}" var="t">
                                    <option value="${t.id}" label="${t.name}">${t.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

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

