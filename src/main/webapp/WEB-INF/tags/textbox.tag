<%--
 * textbox.tag
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ tag language="java" body-content="empty" %>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<%-- Attributes --%> 
 
<%@ attribute name="path" required="true" %>
<%@ attribute name="code" required="true" %>

<%@ attribute name="readonly" required="false" %>
<%@ attribute name="pattern" required="false" %>
<%@ attribute name="autofocus" required="false" %>
<%@ attribute name="id" required="false" %>

<jstl:if test="${readonly == null}">
	<jstl:set var="readonly" value="false" />
</jstl:if>

<jstl:if test="${pattern == null}">
	<jstl:set var="pattern" value="" />
</jstl:if>

<jstl:if test="${autofocus == null}">
	<jstl:set var="autofocus" value="false" />
</jstl:if>

<jstl:if test="${id == null}">
	<jstl:set var="id" value="" />
</jstl:if>

<%-- Definition --%>

	<p><form:label path="${path}">
		<spring:message code="${code}" /> <jstl:out value="${pattern}"></jstl:out>:
	</form:label><br/>
	<form:input id="${id}" autofocus="${autofocus}" path="${path}" readonly="${readonly}" size="60"/><br/>
	<form:errors path="${path}" cssClass="error" /></p>
