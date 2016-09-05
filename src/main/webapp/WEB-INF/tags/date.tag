<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ui"%>
<%@ attribute name="sname"      required="false"  rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="ename"      required="false"  rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="svalue"      required="false"  rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="evalue"      required="false"  rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="label"     required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="labeWidth" required="false"  rtexprvalue="true" type="java.lang.Integer" %>
<%@ attribute name="splace"     required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="eplace"     required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="sdate_id" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="edate_id" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="path1" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="path2" required="false" rtexprvalue="true" type="java.lang.String" %>

<spring:bind path="${path1}"> <c:if test="${status.error}" var="haserror1"/></spring:bind>
<spring:bind path="${path2}"> <c:if test="${status.error}" var="haserror2"/></spring:bind>

<div class="row form-group ">
    <div class="col-sm-4"> 
        <label class="pan-body-title"><spring:message code="${label}" text="${label}" /></label>
    </div> 
    <div class="col-sm-4"> 
        <div class="${haserror1?' has-error':''}">
            <input id="datepicker1" type="date" name="${sname}"  value="${date1}" />
            <form:errors path="${path1}" cssClass="col-sm-10 text-error" element="div"/>
        </div>
    </div> 
    <div class="col-sm-4"> 
        <div class="${haserror2?' has-error':''}">
            <input id="datepicker2" type="date" name="${ename}" class="float_r" value="${date2}" />
            <form:errors path="${path2}" cssClass="col-sm-10  text-error" style="margin-left: 2.5%;"  element="div"/>
        </div>
    </div> 
</div>

<script>
    $(function() {
        $( "#datepicker1" ).datepicker({
            firstDay: 1,
            dateFormat: "yy-mm-dd"
            });
        });

    $(function() {
        $( "#datepicker2" ).datepicker({
            firstDay: 1,
            dateFormat: "yy-mm-dd"
            });
        });
  </script>

