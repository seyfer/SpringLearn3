<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Account</title>
<link href="${pageContext.request.contextPath}/static/css/main.css"
	rel="stylesheet" type="text/css">
</head>
<body>

	Will create

	<sf:form method="post"
		action="${pageContext.request.contextPath}/doCreateAccount"
		commandName="user">

		<table class="formtable">
			<tr>
				<td>Username: <sf:input type="text" name="username" path="username" />
					<br> <sf:errors path="username" cssClass="error"></sf:errors>
				</td>
			</tr>
			<tr>
				<td>Email: <sf:input type="text" name="email" path="email" />
					<br> <sf:errors path="email" cssClass="error"></sf:errors>
				</td>
			</tr>
			<tr>
				<td>Password: <sf:input type="password" name="password"
						path="password" /> <br> <sf:errors path="password"
						cssClass="error"></sf:errors>
				</td>
			</tr>
			<tr>
				<td>Confirm Password: <sf:input type="password"
						name="confirmpassword" path="password" /> <br>
				</td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>

	</sf:form>

</body>
</html>