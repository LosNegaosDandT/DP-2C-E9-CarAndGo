<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<spring:message code="dashboard.query1" var="q1"/>
<spring:message code="dashboard.query2" var="q2"/>
<spring:message code="dashboard.query3" var="q3"/>
<spring:message code="dashboard.query4" var="q4"/>
<spring:message code="dashboard.query5" var="q5"/>

<jstl:out value="${q1}:"/><br/><b>
<jstl:out value="${query1}"/></b><br/><br/>
<jstl:out value="${q2}:"/><br/><b>
<jstl:out value="${query2_1} - ${query2_2}"/></b><br/><br/>
<jstl:out value="${q3}:"/><br/><b>
<jstl:out value="${query3}"/></b><br/><br/>
<jstl:out value="${q4}:"/><br/><b>
<jstl:out value="${query4_1.userAccount.username} (${query4_2})"/></b><br/><br/>
<jstl:out value="${q5}:"/><br/><b>
<jstl:out value="${query5_1.userAccount.username} (${query5_2})"/></b><br/><br/>