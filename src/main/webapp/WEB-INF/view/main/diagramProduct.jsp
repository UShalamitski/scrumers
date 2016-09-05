<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<ui:html3>

    <c:url value="/js/favotirestreams.js" var="href"/><script src="${href}"></script>

    <c:url value="#" var="action"/>
    <ui:navbar active="p_diagram" action="${action}" />

    <div class="container-fluid main padding_top8em">
        <div class="product-main">
            <div class="product-title">
                <div class="right-head-container">
                    <div class="first-row"> <fmt:message key="product.diagram"/></div>
                    <div class="second-row"> <fmt:message key="product"/>: &nbsp ${name}</div>
                </div>
            </div>
    
            <div class="product-box cl1">
                <div class="product-box-first-row">
                    ToDo
                </div>
                <div class="product-box-second-row">
                    ${st1}
                </div>
            </div>
            <div class="product-box cl2">
                <div class="product-box-first-row">
                    Doing
                </div>
                <div class="product-box-second-row">
                    ${st2}
                </div>
            </div>
            <div class="product-box cl3">
                <div class="product-box-first-row">
                    Reviewing
                </div>
                <div class="product-box-second-row">
                    ${st3}
                </div>
            </div>
            <div class="product-box cl4">
                <div class="product-box-first-row">
                    Done
                </div>
                <div class="product-box-second-row">
                    ${st4}
                </div>
            </div>
        </div>
        
        <div id="chart1" class="plot"></div>
        
    </div>


    <script>
        $(document).ready(prodDiagram);
    </script>
</ui:html3>
