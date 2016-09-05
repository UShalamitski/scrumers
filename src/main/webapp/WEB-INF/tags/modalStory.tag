<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<%@ attribute name="id" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="title" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="action" required="true" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="name_id" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="descr_id" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="storyPoints_id" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="estimate_id" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="track_id" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="howToDemo_id" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="status" required="false" rtexprvalue="true" type="java.lang.String" %>

<div class="modal-window modal" id="${id}" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-content" >

        <div class="modal-body size3">

            <div class="modal-title">
                ${title}
                <a data-dismiss="modal" class="float_r" >
                    <i class="icon fa fa-times fa-fw pull-right"></i>
                </a>
            </div>

            <div class="modal-container size3">
                <c:url value="${action}" var="href" />
                <form:form  action="${href}" metod="POST" modelAttribute="storyModel">
                    <form:hidden path="id" name="id" id="s_id"/>
                    <form:hidden path="storyId" id="s_story_id"/>
                    <form:hidden path="statusId" id="s_status"/>

                    <ui:input4 path="name" id="${name_id}" label="story.name" labeWidth="3"  place="story.name" title="story.name.title"/>
                    <ui:textarea2 path="descr" id="${descr_id}" label="story.descr" labeWidth="3"  place="story.descr" title="story.descr.title"/>
                    <ui:input4 path="storyPoints" id="${storyPoints_id}" label="story.points" labeWidth="3"  place="story.storyPoints.title" title="story.storyPoints.title"/>
                    <ui:number path="estimate" id="${estimate_id}" label="story.estimate" labeWidth="3" title="story.estimate.title"/>
                    <ui:input4 path="track" id="${track_id}" label="story.track" labeWidth="3"  place="story.track" title="story.track.title"/>

                    <div class="row form-group">
                        <div class="col-sm-3"> 
                            <fmt:message key="story.assignee" var="label"/>
                            <label class="pan-body-title"><spring:message code="${label}" text="${label}" /></label>
                        </div>
                        <div class="col-sm-9"> 
                            <select id="s_assignee" name="assignee" class="">
                                <option value="0" label="-">-</option>
                                <c:forEach items="${users}" var="user">
                                    <option value="${user.id}" label="${user.fname} ${user.sname}">${user.fname} ${user.sname}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <ui:textarea2 path="howToDemo" id="${howToDemo_id}" label="story.demo" labeWidth="3"  place="story.demo" title="story.demo.title"/>

                    <div class="row">
                        <div class="modal_footer">
                            <input type="submit" class="but" value="<fmt:message key="save"/>" > 
                        </div>
                    </div>
                </form:form>
            </div>

        </div>
    </div>
</div>