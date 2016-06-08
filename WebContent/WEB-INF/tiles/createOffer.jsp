<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


Will create

<sf:form method="post"
	action="${pageContext.request.contextPath}/doCreateOffer"
	commandName="offer">

	<table class="formtable">
<!-- 		<tr> -->
<%-- 			<td>Name: <sf:input type="text" name="name" path="name" /> <br> --%>
<%-- 			<sf:errors path="name" cssClass="error"></sf:errors> --%>
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<%-- 			<td>Email: <sf:input type="text" name="email" path="email" /> <br> --%>
<%-- 			<sf:errors path="email" cssClass="error"></sf:errors> --%>
<!-- 			</td> -->
<!-- 		</tr> -->
		<tr>
			<td>Offer: <sf:textarea rows="3" cols="30" name="text"
					path="text"></sf:textarea> <br>
			<sf:errors path="text" cssClass="error"></sf:errors>
			</td>
		</tr>
		<tr>
			<td><input type="submit"></td>
		</tr>
	</table>

</sf:form>
