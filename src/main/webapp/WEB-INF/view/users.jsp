<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<ui:html3>

    <c:url value="/searchUsers" var="href" />
    <ui:navbar active="users" action="${href}" />

    <div class="container-fluid padding_top8em"> 

            <div class="row">
                <div class="pan_head  col-sm-12 col-md-10 col-md-offset-1">
                    <span class="pan-head-title"><fmt:message key="users"/></span>
                    <a data-toggle="modal" data-target="#newOrgModal" class="float_r not" param_o_id="" param_o_name="">
                        <i class="pan_head_icon fa fa-plus fa-fw pull-right"></i>
                    </a>
                </div>
            </div>

            <div class="row">
                <div class="pan_body_table  col-sm-12 col-md-10 col-md-offset-1 ">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <td width="1%">#</td>
                                <td width="*"><fmt:message key="user.name" /></td>
                                <td width="*"><fmt:message key="user.login" /></td>
                                <td width="*"><fmt:message key="user.email" /></td>
                                <td width="*"><fmt:message key="user.phone" /></td>
                                <td width="*"><fmt:message key="time.adding" /></td>
                                <td width="*"><fmt:message key="modification.time" /></td>
                                <td width="*"></td>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${users}" var="user" varStatus="status">
                                <tr>
                                    <td width="1%"><span class="badge">${status.count}</span></td>
                                    <td width="*">${user.fname} ${user.getSname()}</td>
                                    <td width="*">${user.getName()}</td>
                                    <td width="*">${user.email}</td>
                                    <td width="*">${user.phone}</td>
                                    <td><fmt:formatDate type="both" pattern="yyyy-MM-dd  HH:mm:ss" value="${user.added}" /></td>
                                    <td><fmt:formatDate type="both" pattern="yyyy-MM-dd  HH:mm:ss" value="${user.modified}" /></td>
                                    <td width="*" >
                                         <a  name="id" value="${user.id}" data-toggle="modal" data-target="#delModal">
                                            <i class="del-table fa fa-trash-o fa-fw pull-right"></i>
                                        </a>

                                   </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="row">
                <div class="pan_footer  col-sm-12 col-md-10 col-md-offset-1 ">
                    <input type="submit" class="but not"  value="<fmt:message key="organization.new"/>" param_o_id="" param_o_name=""> 
                </div>
            </div>

    </div>

    <ui:modalDelete id="delModal" hidden="param1" action="/deleteuser.html"/>
    <fmt:message key="organization" var="title"/>

<script>
    $('.link').on('click',editModal);
    $('.not').on('click',cleanModal);
</script>

</ui:html3>

