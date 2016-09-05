<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<table class="table table-striped">
    <thead>
        <tr>
            <td width="1%">#</td>
            <td width="*"><fmt:message key="organization.name" />:</td>
            <td width="*"><fmt:message key="organization.counts.teams" />:</td>
            <td width="*"><fmt:message key="organization.counts.products" />:</td>
            <c:if test="${mode eq 'my'}">
                <td width="*"><fmt:message key="organization.status.user" />:</td>
            </c:if>
            <td width="*"></td>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${organizations}" var="organization" varStatus="status">
            <tr>
                <td width="1%"><span class="badge">${status.count}</span></td>
                <c:url value="/organization_switch.html?oid=${organization.id}" var="href"/>
                <td width="*" class="table-a"><a href="${href}">${organization.name}</a></td>
                <td width="*"><a href="#">${organization.numOfTeams}</a></td>
                <td width="*"><a href="#">${organization.numOfProjects}</a></td>
                <c:if test="${mode eq 'my'}">
                    <c:choose>
                        <c:when test="${organization.idCreator eq principal}">
                            <td width="*" style=" text-align: justify;"><fmt:message key="organization.status.my" /></td>
                        </c:when>
                        <c:otherwise>
                            <td width="*" style=" text-align: justify;"><fmt:message key="organization.status.member" /></td>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <td width="*" >
                    <a  name="id" value="${organization.id}" data-toggle="modal" data-target="#myModal">
                        <i class="del-table fa fa-trash-o fa-fw pull-right"></i>
                    </a>
                    <a  name="" data-toggle="modal" data-target="#newOrgModal" class="link" param_o_id="${organization.id}" param_o_name="${organization.name}">
                        <i class="edit-table fa fa-pencil fa-fw pull-right"></i>
                    </a>
               </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
