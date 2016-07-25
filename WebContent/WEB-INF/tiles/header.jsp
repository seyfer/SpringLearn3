<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<a class="title" href="${pageContext.request.contextPath}/">HOME</a>

<%-- <a class="headerItem" href="${pageContext.request.contextPath}/offers">Offers</a> --%>

<div class="login">

	<c:if test="${pageContext.request.userPrincipal.name == null}">
		<a class="login" href="<c:url value="/login" />">Login</a>
	</c:if>

	<%-- 		<c:if test="${pageContext.request.userPrincipal.name != null}"> --%>
	<%-- 			<a href="<c:url value="/logout" />">Logout Get</a> --%>
	<%-- 	<a href="${pageContext.request.contextPath}/login?logout">Log Out</a> --%>
	<%-- 		</c:if> --%>

	<div class="logout">
		<c:url value="/logout" var="logoutUrl" />
		<form id="logout" action="${logoutUrl}" method="post">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<div class="authenticatedAs">
				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal" var="currentPrincipal" />
					Authenticated as:
					<c:out value="${currentPrincipal.getUsername()}"></c:out>
				</sec:authorize>

				<a class="login"
					href="javascript:document.getElementById('logout').submit()">Logout</a>
			</div>
		</c:if>
	</div>
</div>
