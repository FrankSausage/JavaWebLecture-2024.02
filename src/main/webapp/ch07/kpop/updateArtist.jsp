<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Update City</title>
</head>
<body style="margin: 50px">
	<h1>도시 수정</h1>
	<hr>
	<form action="/jw/ch07/kcity/update" method="post">
		<input type="hidden" name="id" value="${city.id}"><br><br>
		<input type="text" name="id" value="${city.id}" disabled><br><br>
		<input type="text" name="name" value="${city.name}"><br><br>
		<input type="text" name="countryCode" value="${city.countryCode}"><br><br>
		<!-- <input type="text" name="district" value="${city.district}"><br><br> -->
		<select>
			<c:forEach var="dist" items="${district}">
				<option value="${dist}"${dist eq city.district ? "selected" : ""}>${dist}</option>
			</c:forEach>
		</select><br><br>
		<input type="text" name="population" value="${city.population}"><br><br>
		<input type="submit" value="확인">
	</form>
</body>
</html>