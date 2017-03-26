<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<form:form action="actor/comment/actor.do" modelAttribute="commentForm">

	<form:hidden path="id" />

	<form:label path="title">
		<spring:message code="comment.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br /><br/>
	
	<form:label path="text">
		<spring:message code="comment.text" />:
	</form:label>
	<form:textarea path="text" />
	<form:errors cssClass="error" path="text" />
	<br /><br/>
	
	<form:label path="stars">
		<spring:message code="comment.stars" />:
	</form:label>
	<form:select path="stars">
		<form:option value="0"/>
		<form:option value="1"/>
		<form:option value="2"/>
		<form:option value="3"/>
		<form:option value="4"/>
		<form:option value="5"/>
	</form:select>
	<form:errors cssClass="error" path="stars" />
	<br /><br/>
	
	<input type="submit" class="btn btn-success" name="save"
		value="<spring:message code="template.save" />" /> 
	<input type="button" class="btn btn-warning" name="cancel"
		value="<spring:message code="template.cancel" />"
		onclick="document.location.href='profile/details.do?actorId=${commentForm.id}';" />
	<br />

</form:form>
