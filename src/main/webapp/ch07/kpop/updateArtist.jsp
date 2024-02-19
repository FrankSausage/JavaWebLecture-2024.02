<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Update Artist</title>
</head>
<body style="margin: 50px">
	<h1>아티스트 수정</h1>
	<hr>
	<form action="/jw/ch07/kpop/updateArtist" method="post">
		<input type="hidden" name="aid" value="${arti.aid}"><br><br>
		<input type="text" value="${arti.aid}" disabled><br><br>
		<input type="text" name="name" value="${arti.name}"><br><br>
		<input type="text" name="debut" value="${arti.debut}"><br><br>
		<input type="text" name="sid" value="${arti.hitSongId}"><br><br>
		<input type="submit" value="확인">
	</form>
</body>
</html>