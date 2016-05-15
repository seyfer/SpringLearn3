<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Account</title>
<link href="${pageContext.request.contextPath}/static/css/main.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/script/jquery.js"></script>

<script type="text/javascript">
	function checkPasswordsMatch() {
		var password = $("#password").val();
		var confirmpassword = $("#confirmpassword").val();

		if (password.length < 3 || confirmpassword.length < 3) {
			return;
		}

		if (password == confirmpassword) {
			$("#matchpass").text(
					"<fmt:message key='MatchedPasswords.user.password' />")
			$("#matchpass").addClass("valid").removeClass("error");
		} else {
			$("#matchpass").text(
					"<fmt:message key='UnmatchedPasswords.user.password' />")
			$("#matchpass").addClass("error").removeClass("valid");
		}

		return (password == confirmpassword);
	}

	$(document).ready(function() {
		$("#password").keyup(checkPasswordsMatch);
		$("#confirmpassword").keyup(checkPasswordsMatch);

		$("#details").submit(function() {
			return checkPasswordsMatch();
		});
	});
</script>

</head>
<body>

	Will create

	<sf:form method="post" id="details"
		action="${pageContext.request.contextPath}/doCreateAccount"
		commandName="user">

		<table class="formtable">
			<tr>
				<td>Username: <sf:input type="text" name="username"
						path="username" /> <br> <sf:errors path="username"
						cssClass="error"></sf:errors>
				</td>
			</tr>
			<tr>
				<td>Email: <sf:input type="text" name="email" path="email" />
					<br> <sf:errors path="email" cssClass="error"></sf:errors>
				</td>
			</tr>
			<tr>
				<td>Password: <sf:input type="password" name="password"
						id="password" path="password" /> <br> <sf:errors
						path="password" cssClass="error"></sf:errors>
				</td>
			</tr>
			<tr>
				<td>Confirm Password: <input type="password"
					id="confirmpassword" name="confirmpassword" /> <br>
					<div id="matchpass"></div>
				</td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>

	</sf:form>

</body>
</html>