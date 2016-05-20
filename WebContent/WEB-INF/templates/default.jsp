<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
<link href="${pageContext.request.contextPath}/static/css/main.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/script/jquery.js"></script>

<tiles:insertAttribute name="includes"></tiles:insertAttribute>

</head>
<body>

	<div class="header">
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</div>

	<div class="content">
		<tiles:insertAttribute name="content"></tiles:insertAttribute>
	</div>

	<hr>

	<div class="footer">
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>


</body>
</html>