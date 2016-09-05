<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

   <div class="portfolio-modal">
        <div class="modal-content login_b">

            <form class="form-signin" action="j_spring_security_check" method="POST">
            
                <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                    <div class="form-group">
                        <p class="form-control-static text-danger">
                            <fmt:message key="login.error">
                            <fmt:param value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                            </fmt:message>
                        </p>
                    </div>
                </c:if>
                <div class="container">
                    <div class="col-lg-10 col-lg-offset-1">
                        <div class="modal-body blackbox06 animated fadeInUp">
                            <h1><spring:message code="login.Login" text="Log In"/></h1>

                            <hr>

                            <div class="row" style="color: white;">
                                <div class="floating-label-form-group col-lg-3 col-lg-offset-1">
                                    <h3><spring:message code="login.login" text="Login"/></h3>
                                </div>
                                <div
                                    class="floating-label-form-group col-lg-7">
                                    <h5><ui:input name="j_username" label="user.name" labeWidth="1" glyph="user" value="${param.j_username}" /></h5>
                                </div>
                            </div>

                            <div class="row" style="color: white;">
                                <div class="floating-label-form-group col-lg-3 col-lg-offset-1">
                                    <h3><spring:message code="login.pass" text="Password"/></h3>
                                </div>
                                <div class="floating-label-form-group col-lg-7">
                                    <h5><ui:input name="j_password" label="psswd" labeWidth="1" passwd="true" glyph="lock" /></h5>
                                </div>
                            </div>

                            <hr>

                            <div class="row text-center">
                                <div class="col-lg-offset-4 col-lg-1">
                                    <button class="btn btn-black" name="reg" value="1" type="submit">
                                        <h5><spring:message code="login.Login" text="Log on"/></h5>
                                    </button>
                                </div>

                                <div class="col-lg-offset-1 col-lg-1">
                                  <c:url value="/reguser/user.html" var="href"/>
                                  <a class="btn btn-black" href="${href}">
                                        <h5><fmt:message key="login.reg"/></h5>
                                  </a>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </form>
            <c:remove var="SPRING_SECURITY_LAST_EXCEPTION"/>
        </div>
    </div>

