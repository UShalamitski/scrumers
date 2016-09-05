<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<ui:html2>

    <c:url value="/reguser/save.html" var="href" />
    <div class="portfolio-modal">
        <div class="modal-content login_b" >
            <form:form cssClass="form-horizontal" action="${href}" method="POST" modelAttribute="user">
                <div class="container">
                    <div class="col-lg-10 col-lg-offset-1">
                        <div class="modal-body blackbox06">
                            <h1><spring:message code="login.reg" text="Registration"/></h1>

                            <hr>

                            <div class="row" style="color: white;">
                                <div class="floating-label-form-group col-lg-3 col-lg-offset-1">
                                    <h3><spring:message code="login.login" text="Login"/></h3>
                                </div>
                                <div
                                    class="floating-label-form-group col-lg-7">
                                    <h5><ui:input2 path="name" labeWidth="1" place="login.enter" glyph="user"/></h5>
                                </div>
                            </div>


                            <div class="row" style="color: white;">
                                <div class="floating-label-form-group col-lg-3 col-lg-offset-1">
                                    <h3><spring:message code="login.pass" text="Password"/></h3>
                                </div>
                                <div class="floating-label-form-group col-lg-7">
                                    <h5><ui:passwd path="passwd" labeWidth="1" glyph="lock" /></h5>
                                </div>
                            </div>

                            <div class="row" style="color: white;">
                                <div class="floating-label-form-group col-lg-3 col-lg-offset-1">
                                    <h3><spring:message code="user.fname" text="First name"/></h3>
                                </div>
                                <div class="floating-label-form-group col-lg-7">
                                    <h5><ui:input2 path="fname" labeWidth="1" place="user.fname" glyph="lock" /></h5>
                                </div>
                            </div>

                            <div class="row" style="color: white;">
                                <div class="floating-label-form-group col-lg-3 col-lg-offset-1">
                                    <h3><spring:message code="user.sname" text="Surname"/></h3>
                                </div>
                                <div class="floating-label-form-group col-lg-7">
                                    <h5><ui:input2 path="Sname" labeWidth="1" place="user.sname" glyph="lock" /></h5>
                                </div>
                            </div>

                            <hr>

                            <div class="row text-center">
                                <div class="col-lg-offset-4 col-lg-1">
                                    <c:url value="/login.html" var="href"/>
                                    <a class="btn btn-black" href="${href}">
                                        <h5><fmt:message key="error.button"/></h5>
                                    </a>
                                </div>

                                <div class="col-lg-1 col-lg-offset-1">
                                    <button class="btn btn-black" type="submit">
                                        <h5><fmt:message key="save" /></h5>
                                    </button>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
<%--     <ui:about/>
    <ui:footer/> --%>
</ui:html2>