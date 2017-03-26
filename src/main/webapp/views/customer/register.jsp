<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<form:form action="${requestURI}" modelAttribute="registerCustomerForm">

	<spring:message code="customer.register" var="register"/>
	<fieldset >
	<legend>${register }</legend>
	<acme:textbox code="customer.username"  path="username"/>
	<acme:password code="customer.password"  path="password"/>
	<acme:password code="customer.password.confirm"  path="passwordConfirm"/>
	
	<acme:textbox code="customer.name"  path="name"/>
	<acme:textbox code="customer.surname"  path="surname"/>
	<acme:textbox code="customer.email"  path="email"/>
	<acme:textbox code="customer.phone"  path="phone"/>
	
	</fieldset>
	
	<input type="submit" name="save"
		value="<spring:message code="customer.save" />" />&nbsp; 
	
	<input type="button" name="cancel"
		value="<spring:message code="customer.cancel" />"
		onclick="document.location.href='welcome/index.do';" />
	<br />
</form:form>
