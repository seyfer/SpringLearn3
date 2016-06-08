<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
			<td>Name: <sf:input type="text" name="name" path="name" /> <br>
				<sf:errors path="name" cssClass="error"></sf:errors>
			</td>
		</tr>
		<tr>
			<td>Email: <sf:input type="text" name="email" path="email" /> <br>
				<sf:errors path="email" cssClass="error"></sf:errors>
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