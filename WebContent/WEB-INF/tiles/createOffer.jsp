<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	function onDeleteClick(event) {

		var doDelete = confirm("Are you sure???");

		if (doDelete == false) {
			event.preventDefault();
		}
	}

	function onReady() {
		$('#delete').click(onDeleteClick);
	}

	$(document).ready(onReady);
</script>

Will create or update

<sf:form method="post"
	action="${pageContext.request.contextPath}/doCreateOffer"
	commandName="offer">

	<sf:input type="hidden" path="id" />

	<table class="formtable">
		<tr>
			<td>Offer: <sf:textarea rows="3" cols="30" name="text"
					class="control" path="text"></sf:textarea> <br> <sf:errors
					path="text" cssClass="error"></sf:errors>
			</td>
		</tr>
		<tr>
			<td><input type="submit" value="submit" class="control"></td>

			<c:if test="${offer.id !=0}">
				<td><input type="submit" value="delete" name="delete"
					id="delete" class="delete control"></td>
			</c:if>
		</tr>
	</table>

</sf:form>
