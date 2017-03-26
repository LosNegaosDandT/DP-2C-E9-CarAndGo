<%--
 * action-2.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<security:authorize access="hasRole('CUSTOMER')">

<display:table name="application" id="row"
	requestURI="customer/application/list.do" pagesize="5" class="displaytag">

	<spring:message code="application.demand" var="demand"></spring:message>
	<display:column property="demand.title" title="${demand}"></display:column>

	<spring:message code="application.customer" var="customer"/>
	<display:column  title="${customer}">
		<a href="actor/profile/customer.do?actorId=${row.demand.customer.id}"><jstl:out value="${row.demand.customer.userAccount.username}"></jstl:out></a>
	</display:column>
	
	

	
	
	<display:column title="${state}">
		<b>
			<jstl:if test="${row.state=='ACCEPTED'}">
				<font color="green"><spring:message code="application.accepted"/></font>
			</jstl:if>
			<jstl:if test="${row.state=='PENDING'}">
				<input type="button" class="btndanger" value='<spring:message code="application.denying"/>' onclick="javascript: window.location.href = 'customer/application/deny.do?applicationId=${row.id}';">
				<input type="button" class="btnsuccess" value='<spring:message code="application.accepting"/>' onclick="javascript: window.location.href = 'customer/application/accept.do?applicationId=${row.id}';">
			</jstl:if>
			<jstl:if test="${row.state=='DENIED'}">
				<font color="red"><spring:message code="application.denied"/></font>
			</jstl:if>
		</b>
	</display:column>
	
</display:table>

</security:authorize>


