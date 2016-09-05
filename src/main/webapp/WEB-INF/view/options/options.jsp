<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<ui:html>
    <div id="wrapper" class="page pad_top7">
        <div class="container">

            <ui:navbar active="opt"/>

            <div class="panel panel-default">
                <div class="panel-heading" style="background: #3e444c;">
                    <h4><fmt:message key="options.options" /></h4>
                </div>
                <div class="panel-body">
                    <table class="table table-striped">
                        <tr>
                            <td width="1%">#</td>
                            <td width="20%"><fmt:message key="options.key" />:</td>
                            <td width="*"><fmt:message key="options.value" />:</td>
                            <td width="*"><fmt:message key="options.descr" />:</td>
                        </tr>
                        <tbody>
                            <c:forEach items="${options}" var="opt" varStatus="status">
                                <tr>
                                    <td width="1%"><span class="badge">${status.count}</span></td>
                                    <td width="20%"> <label><c:url value="/option.html?key=${opt.key}" var="href" /> <a href="${href}">${opt.key}</a></label></td>
                                    <td>${opt.value}</td>
                                    <td><spring:message code="${opt.key}" text="-----------"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>

    <ui:footer/>

</ui:html>
