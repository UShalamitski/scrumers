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
            <td width="*"><fmt:message key="team.added" />:</td>
            <td width="*"><fmt:message key="team.modified" />:</td>
            <td width="*"><fmt:message key="team.del" /></td>
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
                <td width="*" style=" text-align: justify;"><fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${team.added}"/></td>
                <td width="*" style=" text-align: justify;"><fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${team.modified}"/></td>
                <td width="*" style=" text-align: justify;">${team.del}</td>

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
