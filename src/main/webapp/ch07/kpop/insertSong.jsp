<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert Song</title>
</head>
<body style="margin: 50px">
	<h1>노래 추가</h1>
	<hr>
	<form action="/jw/ch07/kpop/insertSong" method="post">
		<input type="text" name="title" placeholder="노래 제목"><br><br>
		<input type="text" name="lyrics" placeholder="가사"><br><br>
		<input type="submit" value="확인">
	</form>
</body>
</html>