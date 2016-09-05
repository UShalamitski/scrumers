<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<ui:html>

    <div id="wrapper" class="page pad_top11">
        <div class="container">

            <ui:navbar active="opt"/>
            <c:url value="/saveOption.html" var="href" />
            <form:form cssClass="form-horizontal" action="${href}" method="POST" modelAttribute="option">
                <form:hidden path="key" />
                <div class="panel panel-default">
                    <div class="panel-heading" style="background: #3e444c;">
                        <h4><fmt:message key="options.options" /></h4>
                    </div>
                    <div class="panel-body">
                        <ui:input2 path="value" label="${option.key}1" labeWidth="2" place="${option.key}"/>
                    </div>
                    <div class="panel-footer align_r">
                        <button class="btn btn-primary" type="submit">
                            <fmt:message key="save" />
                        </button>
                    </div>
                </div>
            </form:form>

        </div>
    </div>

    <ui:footer/>

</ui:html>
