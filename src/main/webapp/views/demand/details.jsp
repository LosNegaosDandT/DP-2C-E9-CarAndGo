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
	<div align="center">
		<h1>
			<jstl:out value="${demand.title}" />
		</h1>
		<h2>
			<jstl:out value="${demand.description}" />
		</h2>
		
	</div>

	<h3>
	<img alt="moment" width="30" height="30" src="http://omniupdate.com/_resources/images/products/valueprops/c-calender.png">
			<jstl:out value="${demand.moment}" />
			<br/>
	<img alt="origin" width="30" height="30" src="http://icon-icons.com/icons2/37/PNG/512/plane_takeoff_3577.png">
			<jstl:out value="${demand.origin.address}, (${demand.origin.longitude}, ${demand.origin.latitude })" />
			<br/>
	<img alt="destinity" width="30" height="30" src="http://icon-icons.com/icons2/37/PNG/512/planelanding_avion_3578.png">
			<jstl:out value="${demand.destination.address}, (${demand.destination.longitude}, ${demand.destination.latitude })" />
			<br/>
	
	</h3>

</div>
	
	<input type="button" class="btn"
		value="<spring:message code="demand.comment"/>"
		onclick="document.location.href='actor/comment/demand.do?demandId=${demand.id}';" />
	<br />
	
<jstl:if test="${demand.comments.size()>0}">
	<h3>
		<spring:message code="demand.comments" />
	</h3>
	<jstl:forEach items="${demand.comments}" var="comment">
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
