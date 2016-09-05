<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<ui:html2>
<c:url value="/login.html" var="href" />
   <div class="portfolio-modal">
        <div class="modal-content login_b">
            <form class="form-signin" action="${href}">
                <div class="container">
                    <div class="col-lg-10 col-lg-offset-1">
                        <div class="modal-body blackbox06">
                            <h1><fmt:message key="error.access"/></h1>
                            <hr>
                            <div class="row text-center">
                                <div class="col-lg-offset-1 col-lg-10">
                                    <h4><fmt:message key="error.text"/></h4>
                                </div>
                            </div>
                            <hr>
                            <div class="row  text-center">
                                <div class="col-lg-offset-5 col-lg-2">
                                    <button class="btn btn-black" type="submit">
                                        <h5><fmt:message key="error.button"/></h5>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <ui:about/>
    <ui:footer/>
</ui:html2>