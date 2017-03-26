<%--
 * action-2.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="customer/request/edit.do" modelAttribute="request">
	

	
	<form:hidden path="id"/>
	<form:hidden path="version" />
	<form:hidden path="customer" />
	<form:hidden path="banned" />
	<form:hidden path="comments" />
	<form:hidden path="applications" />
	
	<form:label path="title">
		<spring:message code="demand.title" />:
	</form:label><br/>
	<form:input path="title" size="60" autofocus="true"/><br/>
	<form:errors cssClass="error" path="title" />
	<br />
	
	<form:label path="description">
		<spring:message code="demand.description" />:
	</form:label><br/>
	<form:input path="description" size="60" /><br/>
	<form:errors cssClass="error" path="description" />
	<br />
	
	<form:label path="moment">
		<spring:message code="demand.moment" />:
	</form:label><br/>
	<form:input path="moment" size="60" /><br/>
	<form:errors cssClass="error" path="moment" />
	<br />
	
		<form:label path="origin.address">
		<spring:message code="offer.origin.address" />:
	</form:label><br/>
	<form:input path="origin.address" size="60" /><br/>
	<form:errors cssClass="error" path="origin.address" />
	<br />
	
	<form:label path="origin.latitude">
		<spring:message code="offer.origin.latitude" />:
	</form:label><br/>
	<form:input path="origin.latitude" size="60" /><br/>
	<form:errors cssClass="error" path="origin.latitude" />
	<br />
	
	<form:label path="origin.longitude">
		<spring:message code="offer.origin.longitude" />:
	</form:label><br/>
	<form:input path="origin.longitude" size="60" /><br/>
	<form:errors cssClass="error" path="origin.longitude" />
	<br />
	
	<form:label path="destination.address">
		<spring:message code="offer.destination.address" />:
	</form:label><br/>
	<form:input path="destination.address" size="60" /><br/>
	<form:errors cssClass="error" path="destination.address" />
	<br />
	
	<form:label path="destination.latitude">
		<spring:message code="offer.destination.latitude" />:
	</form:label><br/>
	<form:input path="destination.latitude" size="60" /><br/>
	<form:errors cssClass="error" path="destination.latitude" />
	<br />
	
	<form:label path="destination.longitude">
		<spring:message code="offer.destination.longitude" />:
	</form:label><br/>
	<form:input path="destination.longitude" size="60" /><br/>
	<form:errors cssClass="error" path="destination.longitude" />
	<br />
	
	
	
<br/>
	
	<div style="display:inline-block;width:100%;">
		<div style="border:none; display:inline-block;width: 32%;text-align: left;">
			<input type="button" class="btn btn-warning" name="cancel" value='<spring:message code="template.cancel"/>' onclick="document.location.href='admin/attribute/list.do';">
		</div>
<%-- 		<jstl:if test="${attribute.id != 0}"> --%>
<%-- 			<jstl:if test="${attribute.values.isEmpty()}"> --%>
<!-- 				<div style="border:none; display:inline-block;width: 32%;text-align: center;"> -->
<%-- 					<input type="submit" class="btn btn-danger" name="delete" value='<spring:message code="template.delete"/>'> --%>
<!-- 				</div> -->
<%-- 			</jstl:if> --%>
<%-- 			<jstl:if test="${!attribute.values.isEmpty()}"> --%>
<%-- 				<spring:message code="attribute.not.deleteable" var="err"/> --%>
<%-- 			</jstl:if> --%>
<%-- 		</jstl:if> --%>
		<div style="border:none; display:inline-block;width: 32%;text-align: right;">
			<input type="submit" class="btn btn-success" name="save" value='<spring:message code="template.save"/>'>
		</div>
	</div>	
</form:form>
<jstl:if test="${err!=null }">
	<br/>
	<font size="2" >
		<span style="font-weight: bolder;">** <jstl:out value="${err}"/> **</span>
	</font>
</jstl:if>


