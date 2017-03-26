<%--
 * action-1.jsp
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


<input type="text" id="textSearch" value="" autofocus>
<input type="button" id="buttonSearch"
	value="<spring:message code="demand.search"/>" />

<script type="text/javascript">
	$(document).ready(function() {
		$("#buttonSearch").click(function() {
			if ($("#textSearch").val()!="")
				window.location.replace('actor/request/list.do?search='+ $("#textSearch").val());
		});
		$("#textSearch").on('keyup',function(e) {
			if (e.keyCode === 13 && $("#textSearch").val()!="")
				window.location.replace('actor/request/list.do?search='+ $("#textSearch").val());
			e.preventDefault();
		});
	});
</script>

<display:table name="requests" id="row"
	requestURI="actor/request/list.do" pagesize="5" class="displaytag">


	<spring:message code="demand.title" var="title"></spring:message>
	<display:column property="title" title="${title}"></display:column>

	<spring:message code="demand.description" var="description"></spring:message>
	<display:column property="description" title="${description}"></display:column>

	<spring:message code="demand.moment" var="moment"></spring:message>
	<display:column property="moment" title="${moment}" format="{0,date,dd/MM/yyyy HH:mm}"></display:column>

	<spring:message code="demand.origin" var="origin"></spring:message>
	<display:column  title="${origin}"> <jstl:out value="${row.origin.address} (${row.origin.latitude},${row.origin.longitude})"></jstl:out> </display:column>
	
	<spring:message code="demand.destination" var="destination"></spring:message>
	<display:column  title="${destination}"> <jstl:out value="${row.destination.address} (${row.destination.latitude},${row.destination.longitude})"></jstl:out> </display:column>

	<spring:message code="demand.customer" var="customer"/>
	<display:column  title="${customer}">
		<a href="actor/profile/customer.do?actorId=${row.customer.id}"><jstl:out value="${row.customer.userAccount.username}"></jstl:out></a>
	</display:column>
	
	<display:column  >
	<input type="button" class="btn"
		value="<spring:message code="demand.details"/>"
		onclick="document.location.href='actor/demand/details/request.do?demandId=${row.id}';" />
	</display:column>
	
	<security:authorize access="hasRole('ADMIN')">
	
	
	<spring:message code="demand.ban" var="ban" />
	<display:column title="${ban}" >
		<jstl:if test="${row.banned eq true}">
			<font color="red"><b>BANNED</b></font>
		</jstl:if>
		<jstl:if test="${row.banned eq false}">
			<input type="button" class="btn btn-primary" value="${ban}"	
				onclick="javascript: window.location.href = './admin/request/ban.do?requestId=${row.id}';" />
<%-- 			<a href="./admin/offer/ban.do?offerId=${row.id}"><img style="width: 100px;" src="https://2.bp.blogspot.com/-oS007seA69Q/WCWnTwM1UWI/AAAAAAAACEk/uoa5TWKh1X4YjxBedHeX7DQ9OXiT1NMPACLcB/s1600/iltri.gif"></a> --%>
		</jstl:if>		
	</display:column>
	
	
	
	</security:authorize>
	
	<security:authorize access="hasRole('CUSTOMER')">
	
	<spring:message code="demand.apply" var="apply" />
	<display:column title="${apply}" >
		<jstl:if test="${row.banned eq true}">
			<font color="red"><b>BANNED</b></font>
		</jstl:if>
		<jstl:if test="${row.banned eq false}">
			<input type="button" class="btn btn-primary" value="${apply}"	
					onclick="javascript: window.location.href = './customer/request/apply.do?requestId=${row.id}';" />
		</jstl:if>	
	</display:column>
	</security:authorize>
	
</display:table>



