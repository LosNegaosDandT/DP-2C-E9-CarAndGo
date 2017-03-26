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


<form:form action="admin/banner/edit.do" modelAttribute="banner">


	<form:hidden path="id" />
	<form:hidden path="version" />

	<form:label path="image">
		<spring:message code="banner.image" />:
	</form:label><br/>
	<form:input path="image" size="60" autofocus="true"/><br/>
	<form:errors cssClass="error" path="image" />
	<br />
	<br/>

	
	<input type="button" class="btn btn-warning" name="cancel" value='<spring:message code="template.cancel"/>' onclick="document.location.href='customer/demand/editBanner.do';">
	<input type="submit" class="btn btn-success" name="save" value='<spring:message code="template.save"/>'>
	
	
</form:form>


