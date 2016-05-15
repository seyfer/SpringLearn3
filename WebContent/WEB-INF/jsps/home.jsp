<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/static/css/main.css"
	rel="stylesheet" type="text/css">
<title>Home</title>
</head>
<body>
	<h3>HOME</h3>

	<a href="${pageContext.request.contextPath}/offers">Offers</a>
	<a href="${pageContext.request.contextPath}/createOffer">createOffer</a>

	<a href="<c:url value="/admin" />">Admin</a>


	<p>
		<sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal" var="currentPrincipal" />
		Authenticated as: <c:out value="${currentPrincipal.getUsername()}"></c:out>
		</sec:authorize>

		<c:if test="${pageContext.request.userPrincipal.name == null}">
			<a href="<c:url value="/login" />">Login</a>
		</c:if>

		<%-- 		<c:if test="${pageContext.request.userPrincipal.name != null}"> --%>
		<%-- 			<a href="<c:url value="/logout" />">Logout Get</a> --%>
		<%-- 	<a href="${pageContext.request.contextPath}/login?logout">Log Out</a> --%>
		<%-- 		</c:if> --%>

		<c:url value="/logout" var="logoutUrl" />
	<form id="logout" action="${logoutUrl}" method="post">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<a href="javascript:document.getElementById('logout').submit()">Logout</a>
	</c:if>

	<hr>

	Session:
	<%=session.getAttribute("name")%><br> Request:
	<%=request.getAttribute("name")%><br> Request EL: ${name}
	<br> jstl:
	<c:out value="${name}"></c:out>

</body>
</html>