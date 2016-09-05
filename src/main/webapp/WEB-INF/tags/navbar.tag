<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ attribute name="active" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="action" required="false" rtexprvalue="true" type="java.lang.String" %>

<c:if test="${not empty sessionScope['lang']}">
    <fmt:setLocale value="${sessionScope['lang']}" scope="session"/>
</c:if>

<nav class="navbar navbar-default navbar-fixed-top">
<div class="container">
  <div class="container-fluid">

    <div class="navbar-header">
      <c:url value="/main.html" var="href"/><a href="${href}" style="text-decoration:none;">
        <span class="navbar-brand">
          <%--c:url value="/images/z.png" var="href1"/><img alt="Brand " src="${href1}"--%>
        </span>
        <span class="navbar-brand">SCRUMers${sessionScope['lang']}</span>
      </a>
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
            <c:choose>
                <c:when test="${not empty sessionScope['org']}">
                    <li class="dropdown${active eq 'flows' ? ' active' : ''}">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> ${sessionScope['org']} <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li class="${active eq 'products' ? 'active' : ''}"><c:url value="/product_my.html" var="href"/><a href="${href}"><fmt:message key="projects.my"/> <span class="sr-only">(current)</span></a></li>
                            <li class="${active eq 'teams' ? 'active' : ''}"><c:url value="/team_my.html" var="href"/><a href="${href}"><fmt:message key="team.my"/></a></li>
                            <li class="${active eq 'organizations' ? 'active' : ''}"><c:url value="/organization_my.html" var="href"/><a href="${href}"><fmt:message key="organizations"/></a></li>
                        </ul>
                    </li>

                    <c:if test="${not empty sessionScope['prod']}">
                        <li class="dropdown${active eq 'flows' ? ' active' : ''}">
                          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> ${sessionScope['prod']} <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li class="${active eq 'backlog' ? 'active' : ''}"><c:url value="/story_backlog.html" var="href"/><a href="${href}"><fmt:message key="product.backlog"/></a></li>
                                <li class="${active eq 'iterations' ? 'active' : ''}"><c:url value="/iterations.html" var="href"/><a href="${href}"><fmt:message key="iterations"/></a></li>
<%--                                     <li class="${active eq 'p_diagram' ? 'active' : ''}"><c:url value="/productDiagram.html" var="href"/><a href="${href}"><fmt:message key="product.diagram"/></a></li> --%>
                            </ul>
                        </li>

                        <c:if test="${not empty sessionScope['iter']}">
                            <li class="dropdown${active eq 'flows' ? ' active' : ''}">
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> ${sessionScope['iter']} <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li class="${active eq 'storyList' ? 'active' : ''}"><c:url value="/iteration_storyList.html" var="href"/><a href="${href}"><fmt:message key="iteration.storyList"/></a></li>
                                    <li class="${active eq 'planing' ? 'active' : ''}"><c:url value="/iteration_planing.html" var="href"/><a href="${href}"><fmt:message key="iteration.planing"/></a></li>
                                    <li class="${active eq 'board' ? 'active' : ''}"><c:url value="/scrum_board.html" var="href"/><a href="${href}"><fmt:message key="iteration.board"/></a></li>
                                    <li class="${active eq 'burndown' ? 'active' : ''}"><c:url value="/iteration_Burndown.html" var="href"/><a href="${href}"><fmt:message key="diagram.burndown"/></a></li>
                                </ul>
                            </li>
                        </c:if>

                    </c:if>
                    <sec:authorize access="hasRole('admin')" >
                        <li class="dropdown${active eq 'flows' ? ' active' : ''}">
                          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> <fmt:message key="administration" /> <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li class="${active eq 'productsAll' ? 'active' : ''}"><c:url value="/product_all.html" var="href"/><a href="${href}"><fmt:message key="projects"/> <span class="sr-only">(current)</span></a></li>
                                <li class="${active eq 'teamsAll' ? 'active' : ''}"><c:url value="/team_all.html" var="href"/><a href="${href}"><fmt:message key="teams"/></a></li>
                                <li class="${active eq 'organizationsAll' ? 'active' : ''}"><c:url value="/organization_all.html" var="href"/><a href="${href}"><fmt:message key="organizationsAll"/></a></li>
                                <li class="${active eq 'users' ? 'active' : ''}"><c:url value="/users.html" var="href"/><a href="${href}"><fmt:message key="users"/></a></li>
                            </ul>
                        </li>
                    </sec:authorize>
                </c:when>
                <c:otherwise>
                    <li class="${active eq 'genres' ? 'active' : ''}"><c:url value="/organization_new.html" var="href"/><a href="${href}"><fmt:message key="organization.new"/></a></li>
                </c:otherwise>
            </c:choose>
      </ul>


      <ul class="nav navbar-nav navbar-right">
        <li>
            <c:url value="/main_wiki.html" var="href"/>
            <a href="${href}" role="button" aria-expanded="false" style="text-decoration:none;" class="${active eq 'wiki' ? 'active' : ''}"> <i class="fa fa-info-circle fa-fw"></i> wiki </span></a>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> <i class="fa fa-user fa-fw"></i> ${pageContext.request.userPrincipal.name} <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <c:url value="/info.html" var="href"/> <li><a href="${href}"><fmt:message key="user.profile"/></a></li>
                <c:url value="/changePassword.html" var="href"/> <li><a href="${href}"><fmt:message key="user.changePassword"/></a></li>
                <c:url value="/logout" var="href"/><li><a href="${href}"><fmt:message key="exit"/></a></li>
            </ul>
        </li>
        <li>
            <c:url value="/main.html?lang=ru" var="href"/>
            <a href="${href}" id="lang-left">RU</a>
        </li>
        <li>
            <c:url value="/main.html?lang=en" var="href"/>
            <a href="${href}" id="lang-left">EN</a>
        </li>

      </ul>

    </div>
  </div>
</div>
</nav>
