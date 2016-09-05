<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<table class="table table-striped">
    <thead>
        <tr>
            <td width="1%">#</td>
            <td width="*"><fmt:message key="team.name" />:</td>
            <td width="*"><fmt:message key="team.numOfUsers" />:</td>
            <td width="*"><fmt:message key="team.teamRole" />:</td>
            <td width="*"><fmt:message key="team.status" />:</td>
            <td width="*"></td>
        </tr>
    </thead>

    <tbody>
        <c:forEach items="${teams}" var="team" varStatus="status">
            <tr>

                <td width="1%"><span class="badge">${status.count}</span></td>
                <c:url value="/team.html?id=${team.id}" var="href"/>
                <td width="*" class="table-a"><a href="${href}">${team.name}</a></td>
                <td width="*" style=" text-align: justify;">${team.numOfUsers}</td>

                <c:choose>
                    <c:when test="${team.teamRole eq 1}">
                        <td width="*" style=" text-align: justify;"><fmt:message key="team.role.master" /></td>
                    </c:when>
                    <c:when test="${team.teamRole eq 2}">
                        <td width="*" style=" text-align: justify;"><fmt:message key="team.role.developer" /></td>
                    </c:when>
                    <c:otherwise>
                        <td width="*" style=" text-align: justify;"><fmt:message key="team.role.unknown" /></td>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${team.idCreator eq principal}">
                        <td width="*" style=" text-align: justify;"><fmt:message key="team.status.my" /></</td>
                    </c:when>
                    <c:otherwise>
                        <td width="*" style=" text-align: justify;"><fmt:message key="team.status.member" /></</td>
                    </c:otherwise>
                </c:choose>
                
                <td width="*" >
                    <a  name="id"  value="${team.id}" data-toggle="modal" data-target="#myModal">
                        <i class="del-table fa fa-trash-o fa-fw pull-right"></i>
                    </a>
                    <a  data-toggle="modal" data-target="#newTeamModal" class="link1" param_t_id="${team.id}" param_t_name="${team.name}">
                        <i class="edit-table fa fa-pencil fa-fw pull-right"></i>
                    </a>
               </td>

            </tr>

        </c:forEach>
    </tbody>
</table>
