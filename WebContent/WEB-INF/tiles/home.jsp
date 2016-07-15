<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>

	<c:forEach var="offer" items="${offers}">
    Id ${offer.id} - Username: ${offer.user.username}
    <a
			href="<c:url value='/message?username=${offer.user.username}'></c:url>">
			Send message </a>
		<br />
    Name ${offer.user.name}<br />
    Email ${offer.user.email}<br />
    text ${offer.text}<br />
		<hr>
	</c:forEach>

	<hr>
	<c:choose>
		<c:when test="${hasOffer}">
			<a href="${pageContext.request.contextPath}/offers">Offers</a>
		</c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/createOffer">createOffer</a>
		</c:otherwise>
	</c:choose>

</body>
</html>

