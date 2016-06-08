<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Offers</h2>

<c:forEach var="offer" items="${offers}">
    Id ${offer.id}<br />
    Name ${offer.user.name}<br />
    Email ${offer.user.email}<br />
    text ${offer.text}<br />
	<hr>
</c:forEach>