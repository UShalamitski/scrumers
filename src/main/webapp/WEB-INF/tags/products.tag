<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>


<table class="table table-striped">
    <thead>
        <tr>
            <td width="1%">#</td>
            <td width="*"><fmt:message key="product.name" />:</td>
            <td width="*"><fmt:message key="product.descr" />:</td>
            <td width="*"><fmt:message key="product.owner" />:</td>
            <td width="*"><fmt:message key="product.status.project" />:</td>
            <td width="*"><fmt:message key="product.status.user" />:</td>
            <td width="*"></td>
        </tr>
    </thead>

    <tbody>
        <c:forEach items="${products}" var="product" varStatus="status">
            <tr>

                <td width="1%"><span class="badge">${status.count}</span></td>
                <c:url value="/product_switch.html?pid=${product.id}" var="href"/>
                <td class="table-a" width="*"><a href="${href}">${product.name}</a></td>
                <td width="*" style=" text-align: justify;">${product.descr}</td>

                <c:choose>
                    <c:when test="${product.owner eq 0}">
                        <td width="*" class="table-a" style=" text-align: justify;">
                            <a name="prod" value="${product.id}" data-toggle="modal" data-target="#cahngeOnwerProdModal">
                                <fmt:message key="product.owner.empty" />
                            </a>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td width="*" class="table-a" style=" text-align: justify;">
                            <a  name="prod" value="${product.id}" data-toggle="modal" data-target="#cahngeOnwerProdModal">
                                ${product.ownerName}
                            </a>
                        </td>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${product.done eq 'false'}">
                        <td width="*" style=" text-align: justify;"><fmt:message key="product.status.inprogress" /></td>
                    </c:when>
                    <c:otherwise>
                        <td width="*" style=" text-align: justify;"><fmt:message key="product.status.done" /></td>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${product.idCreator eq principal or sessionScope['mode'] eq 'all'}">
                        <td width="*" style=" text-align: justify;"><fmt:message key="product.status.my" /></td>
                       <td width="*" >
                            <a  name="id" value="${product.id}" data-toggle="modal" data-target="#myModal">
                                <i class="del-table fa fa-trash-o fa-fw pull-right"></i>
                            </a>
                            <a data-toggle="modal" data-target="#newProdModal" class="link" param_p_id="${product.id}" param_p_name="${product.name}" param_p_descr="${product.descr}">
                                <i class="edit-table  fa fa-pencil fa-fw pull-right"></i>
                            </a>
                       </td>
                    </c:when>
                    <c:otherwise>
                        <td width="*" style=" text-align: justify;"><fmt:message key="product.status.member" /></td>
                        <td width="*" >
                            <a  name="id" value="" data-toggle="" data-target="">
                                <i class="del-table-disabled fa fa-trash-o fa-fw pull-right"></i>
                            </a>
                            <a  name="" value="" data-toggle="modal" data-target="">
                                <i class="edit-table fa fa-pencil fa-fw pull-right"></i>
                            </a>
                       </td>
                    </c:otherwise>
                </c:choose>

            </tr>
        </c:forEach>
    </tbody>
</table>

