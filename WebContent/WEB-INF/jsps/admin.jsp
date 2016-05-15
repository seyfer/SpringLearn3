<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/static/css/main.css"
	rel="stylesheet" type="text/css">
<title>Admin</title>
</head>
<body>
	Only for authorized!

	<table class="formtable">

		<thead>
			<tr>
				<th>Username</th>
				<th>Email</th>
				<th>Authority</th>
				<th>Enabled</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<th><c:out value="${user.username}"></c:out></th>
					<th><c:out value="${user.email}"></c:out></th>
					<th><c:out value="${user.authority}"></c:out></th>
					<th><c:out value="${user.enabled}"></c:out></th>
				</tr>
			</c:forEach>
		</tbody>

	</table>

</body>
</html>