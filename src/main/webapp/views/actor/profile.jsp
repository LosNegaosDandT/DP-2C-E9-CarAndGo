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



<div align="left">
	<div align="left">
		<spring:message code="actor.username" var="username" />
		<h1>
			<jstl:out value="@${actor.userAccount.username}" />
		</h1>
	</div>

	<h3>
	<img alt="profile" width="30" height="30" src="https://helloclass.com/hcfeed_assets/images/profile.png">
			<jstl:out value="${actor.name}" />, <jstl:out value="${actor.surname}" />
			<br/>
	<img alt="profile" width="30" height="30" src="https://cdn2.iconfinder.com/data/icons/metro-uinvert-dock/256/Mail.png">
			<jstl:out value="${actor.email}" />
			<br/>
	<img alt="profile" width="30" height="30" src="http://www.clipartbest.com/cliparts/Kij/LaL/KijLaLbkT.png">
			<jstl:out value="${actor.phone}" />
			<br/>
	</h3>

</div>
	
	<input type="button" class="btn"
		value="<spring:message code="actor.comment"/>"
		onclick="document.location.href='actor/comment/actor.do?actorId=${actor.id}';" />
	<br />
	
<jstl:if test="${actor.comments.size()>0}">
	<spring:message code="comment.stars" var="stars" />
	<spring:message code="comment.title" var="title" />
	<h3>
		<spring:message code="actor.comments" />
	</h3>
	<jstl:forEach items="${actor.comments}" var="comment">
		<fieldset>
			<jstl:out value="${stars}:" />
			<jstl:if test="${comment.stars>0}">
				<img class="star" src="images/star.png" />
			</jstl:if>
			<jstl:if test="${!(comment.stars>0)}">
				<img class="star" src="images/nostar.png" />
			</jstl:if>
			<jstl:if test="${comment.stars>1}">
				<img class="star" src="images/star.png" />
			</jstl:if>
			<jstl:if test="${!(comment.stars>1)}">
				<img class="star" src="images/nostar.png" />
			</jstl:if>
			<jstl:if test="${comment.stars>2}">
				<img class="star" src="images/star.png" />
			</jstl:if>
			<jstl:if test="${!(comment.stars>2)}">
				<img class="star" src="images/nostar.png" />
			</jstl:if>
			<jstl:if test="${comment.stars>3}">
				<img class="star" src="images/star.png" />
			</jstl:if>
			<jstl:if test="${!(comment.stars>3)}">
				<img class="star" src="images/nostar.png" />
			</jstl:if>
			<jstl:if test="${comment.stars>4}">
				<img class="star" src="images/star.png" />
			</jstl:if>
			<jstl:if test="${!(comment.stars>4)}">
				<img class="star" src="images/nostar.png" />
			</jstl:if>
			<br />
			<h2>
			<jstl:out value="${comment.title}" />
			</h2>
			<br />
			<h4>
			<jstl:out value="${comment.text}" />
			</h4>
			<br />
		</fieldset>
	</jstl:forEach>
</jstl:if>
