<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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