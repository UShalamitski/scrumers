<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<div class="panel">
    <div class="panel-heading">
        <h3 class="panel-title"><fmt:message key="stories" /></h3>
    </div>
    <div class="panel-body">
        <table class="table table-striped">
            <tr>
                <td width="1%">#</td>
                <td width="*"><fmt:message key="story.id" />:</td>
                <td width="*"><fmt:message key="story.name" />:</td>
                <td width="*"><fmt:message key="story.descr" />:</td>
                <td width="*"><fmt:message key="story.points" />:</td>
                <td width="*"><fmt:message key="story.estimate" />:</td>
                <td width="*"><fmt:message key="story.demo" />:</td>
                <td width="*"><fmt:message key="story.track" />:</td>
                <td width="*">X</td>

            </tr>
            <tbody>
                <c:forEach items="${stories}" var="story" varStatus="status">
                    <tr>
                        <td width="1%"><span class="badge">${status.count}</span></td>
                        <td width="*">${story.storyId}</td>
                        <td width="*">${story.name}</td>
                        <td width="*" style="text-align: justify;">${story.descr}</td>
                        <td width="*">${story.storyPoints}</td>
                        <td width="*">${story.estimate}</td>
                        <td width="*">${story.howToDemo}</td>
                        <td width="*">${story.track}</td>
                        <td width="*" ><a class="btn btn-danger btn-xs delete" name="id" value="${story.id}" data-toggle="modal" data-target="#myModal">X</a></td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>