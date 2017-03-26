<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
<a href="">
	<img style="width: 80%" src="http://i.imgur.com/b5kV30U.png" alt="Acme-CnG Co., Inc." />
</a>
</div>





<!--//BLOQUE COOKIES-->
<div id="barraaceptacion">
	<div class="inner">
		<spring:message code="aviso.cookies" />
		<a href="javascript:void(0);" class="ok" onclick="PonerCookie();"><b>OK</b></a>
		| <a href="law/politicaCookies.do" target="_blank" class="info"><spring:message
				code="aviso.cookies.information" /></a>
	</div>
</div>

<script>
	function getCookie(c_name) {
		var c_value = document.cookie;
		var c_start = c_value.indexOf(" " + c_name + "=");
		if (c_start == -1) {
			c_start = c_value.indexOf(c_name + "=");
		}
		if (c_start == -1) {
			c_value = null;
		} else {
			c_start = c_value.indexOf("=", c_start) + 1;
			var c_end = c_value.indexOf(";", c_start);
			if (c_end == -1) {
				c_end = c_value.length;
			}
			c_value = unescape(c_value.substring(c_start, c_end));
		}
		return c_value;
	}

	function setCookie(c_name, value, exdays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + exdays);
		var c_value = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toUTCString());
		document.cookie = c_name + "=" + c_value;
	}
	if (getCookie('tiendaaviso') != "1") {
		document.getElementById("barraaceptacion").style.display = "block";
	} else {
		document.getElementById("barraaceptacion").style.display = "none";
	}

	function PonerCookie() {
		setCookie('tiendaaviso', '1', 365);
		document.getElementById("barraaceptacion").style.display = "none";
	}
</script>
<!--//FIN BLOQUE COOKIES-->


<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="admin/dashboard/display.do"><spring:message code="master.page.dashboard" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.offers" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/offer/list.do"><spring:message code="master.page.offer.list" /></a></li>					
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.requests" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/request/list.do"><spring:message code="master.page.request.list" /></a></li>					
				</ul>
			</li>
			
			<li><a class="fNiv"><spring:message	code="master.page.admin.banner" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="admin/banner/edit.do"><spring:message code="master.page.admin.banner.edit" /></a></li>	
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv"><spring:message	code="master.page.customer" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/application/list.do"><spring:message code="master.page.customer.application.list" /></a></li>
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.offers" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/offer/create.do"><spring:message code="master.page.offer.create" /></a></li>
					<li><a href="actor/offer/list.do"><spring:message code="master.page.offer.list" /></a></li>					
				</ul>
			</li>
			<li><a class="fNiv"><spring:message	code="master.page.requests" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="customer/request/create.do"><spring:message code="master.page.request.create" /></a></li>
					<li><a href="actor/request/list.do"><spring:message code="master.page.request.list" /></a></li>					
				</ul>
			</li>
			
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="customer/register.do"><spring:message code="master.page.register" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div id="languajes">
		<b> <a href="?language=en" class="languajes"><img
				src="images/uk.png" class="flag" /></a> | <a href="?language=es"
			class="languajes"><img class="flag" src="images/spain.png" /></a> <!-- 	<font size="4"><a href="?language=en" class="languajes">en</a> | <a href="?language=es" class="languajes">es</a></font> -->
		</b>
</div>




