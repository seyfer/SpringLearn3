<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<sql:query var="rs" dataSource="jdbc/springtutorial">
select id, email from offers
</sql:query>

<html>
<head>
<title>DB Test</title>
</head>
<body>

	<h2>Results</h2>

	<c:forEach var="row" items="${rs.rows}">
    Id ${row.id}<br />
    email ${row.email}<br />
	</c:forEach>

</body>
</html>