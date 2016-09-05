<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>

<table class="table table-striped">
    <thead>
        <tr>
            <td width="1%">#</td>
            <td width="*"><fmt:message key="iteration.name" />:</td>
            <td width="*"><fmt:message key="iteration.descr" />:</td>
            <td width="*"><fmt:message key="iteration.start" />:</td>
            <td width="*"><fmt:message key="iteration.end" />:</td>
            <td width="*"></td>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${iterations}" var="iteration" varStatus="status">
            <tr>
                <td width="1%"><span class="badge">${iteration.iterationNum}</span></td>
                <c:url value="/iteration_switch.html?iid=${iteration.id}" var="href"/>
                <td width="*" style=" text-align: justify;" class="table-a"><a href="${href}">${iteration.name}</a></td>
                <td width="*" style=" text-align: justify;">${iteration.descr}</td>
                <td width="*" style=" text-align: justify;"><fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${iteration.dateStart}"/></td>
                <td width="*" style=" text-align: justify;"><fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${iteration.dateEnd}"/></td>
                <td width="*" >
                    <a  name="id" value="${iteration.id}" data-toggle="modal" data-target="#myModal">
                        <i class="del-table fa fa-trash-o fa-fw pull-right"></i>
                    </a>
                    <a  name="" value="" data-toggle="modal" data-target="#newIterModal" class="link" param_i_id="${iteration.id}" param_i_name="${iteration.name}" 
                                                                param_i_descr="${iteration.descr}" 
                                                                param_datepicker1="<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${iteration.dateStart}"/>" 
                                                                param_datepicker2="<fmt:formatDate type="date" pattern="yyyy-MM-dd" value="${iteration.dateEnd}"/>"/>     
                        <i class="edit-table fa fa-pencil fa-fw pull-right"></i>
                    </a>
               </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
