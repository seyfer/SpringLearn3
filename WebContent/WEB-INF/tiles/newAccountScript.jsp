<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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