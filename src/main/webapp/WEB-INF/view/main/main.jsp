<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<ui:html3>

    <c:url value="/js/favotirestreams.js" var="href"/><script src="${href}"></script>

    <c:url value="#" var="action"/>
    <ui:navbar active="wiki" action="${action}" />

    <div class="container-fluid padding_top7"> 
    
        <div class="page">
            <div class="container left-container"> 
                <div class="body">
                    <div class="title"><fmt:message key="scrum.wiki"/></div>
                    <div class="ul">
                        <c:choose>
                            <c:when test="${active_item eq 'scrum'}">
                                <div class="li-active padding-left_1em">
                            </c:when>
                            <c:otherwise>
                                <div class="li padding-left_1em">
                            </c:otherwise>
                        </c:choose>
                            <c:url value="/main_wiki.html?item=scrum" var="href"/>
                            <span class="text-overflow"><a href="${href}"><fmt:message key="scrum"/></a></span>
                        </div>

                        <c:choose>
                            <c:when test="${active_item eq 'scrum.master'}">
                                <div class="li-active padding-left_1em">
                            </c:when>
                            <c:otherwise>
                                <div class="li padding-left_1em">
                            </c:otherwise>
                        </c:choose>
                            <c:url value="/main_wiki.html?item=scrum.master" var="href"/>
                            <span class="text-overflow"><a href="${href}"><fmt:message key="scrum.master"/></a></span>
                        </div>

                        <c:choose>
                            <c:when test="${active_item eq 'scrum.pbacklog'}">
                                <div class="li-active padding-left_1em">
                            </c:when>
                            <c:otherwise>
                                <div class="li padding-left_1em">
                            </c:otherwise>
                        </c:choose>
                            <c:url value="/main_wiki.html?item=scrum.pbacklog" var="href"/>
                            <span class="text-overflow"><a href="${href}"><fmt:message key="scrum.pbacklog"/></a></span>
                        </div>

                        <c:choose>
                            <c:when test="${active_item eq 'scrum.daily'}">
                                <div class="li-active padding-left_1em">
                            </c:when>
                            <c:otherwise>
                                <div class="li padding-left_1em">
                            </c:otherwise>
                        </c:choose>
                            <c:url value="/main_wiki.html?item=scrum.daily" var="href"/>
                            <span class="text-overflow"><a href="${href}"><fmt:message key="scrum.daily"/></a></span>
                        </div>

                        <c:choose>
                            <c:when test="${active_item eq 'scrum.smeeting'}">
                                <div class="li-active padding-left_1em">
                            </c:when>
                            <c:otherwise>
                                <div class="li padding-left_1em">
                            </c:otherwise>
                        </c:choose>
                            <c:url value="/main_wiki.html?item=scrum.smeeting" var="href"/>
                            <span class="text-overflow"><a href="${href}"><fmt:message key="scrum.smeeting"/></a></span>
                        </div>

                        <c:choose>
                            <c:when test="${active_item eq 'scrum.rmeeting'}">
                                <div class="li-active padding-left_1em">
                            </c:when>
                            <c:otherwise>
                                <div class="li padding-left_1em">
                            </c:otherwise>
                        </c:choose>
                            <c:url value="/main_wiki.html?item=scrum.rmeeting" var="href"/>
                            <span class="text-overflow"><a href="${href}"><fmt:message key="scrum.rmeeting"/></a></span>
                        </div>

                        <c:choose>
                            <c:when test="${active_item eq 'scrum.team'}">
                                <div class="li-active padding-left_1em">
                            </c:when>
                            <c:otherwise>
                                <div class="li padding-left_1em">
                            </c:otherwise>
                        </c:choose>
                            <c:url value="/main_wiki.html?item=scrum.team" var="href"/>
                            <span class="text-overflow"><a href="${href}"><fmt:message key="scrum.team"/></a></span>
                        </div>

                        <c:choose>
                            <c:when test="${active_item eq 'scrum.sbacklog'}">
                                <div class="li-active padding-left_1em">
                            </c:when>
                            <c:otherwise>
                                <div class="li padding-left_1em">
                            </c:otherwise>
                        </c:choose>
                            <c:url value="/main_wiki.html?item=scrum.sbacklog" var="href"/>
                            <span class="text-overflow"><a href="${href}"><fmt:message key="scrum.sbacklog"/></a></span>
                        </div>

                        <c:choose>
                            <c:when test="${active_item eq 'scrum.sprint'}">
                                <div class="li-active padding-left_1em">
                            </c:when>
                            <c:otherwise>
                                <div class="li padding-left_1em">
                            </c:otherwise>
                        </c:choose>
                            <c:url value="/main_wiki.html?item=scrum.sprint" var="href"/>
                            <span class="text-overflow"><a href="${href}"><fmt:message key="scrum.sprint"/></a></span>
                        </div>

                    </div>
                </div>
            </div>

             <div class="container right-container"> 
                <div class="body">
                    <c:choose>
                        <c:when test="${not empty active_item}">
                            <div class="title"><fmt:message key="${active_item}"/></div>
                        </c:when>
                        <c:otherwise>
                            <div class="title"><fmt:message key="scrum"/></div>
                        </c:otherwise>
                    </c:choose>
                    
                    <div class="ul">
                        <c:choose>
                            <c:when test="${empty active_item}">
                                <div class="wiki-item">
                                    <fmt:message key="scrum.value"/>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="wiki-item">
                                    <fmt:message key="${active_item}.value"/>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>

        </div>
    </div>

</ui:html3>
