<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert Artist</title>
</head>
<body style="margin: 50px">
	<h1>아티스트 추가</h1>
	<hr>
	<form action="/jw/ch07/kpop/insertArtist" method="post">
		<input type="text" name="name" placeholder="아티스트 이름"><br><br>
		<input type="text" name="debut" placeholder="yyyy-mm-dd"><br><br>
		<input type="text" name="sid" placeholder="노래 ID"><br><br>
		<input type="submit" value="확인">
	</form>
</body>
</html>