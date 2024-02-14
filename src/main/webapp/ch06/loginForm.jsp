<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
</head>
<body style="margin: 50px">
	<h1>로그인</h1>
	<hr>
	<form action="/jw/ch06/login" method="post">
		<input type="text" name="uid" placeholder="아이디 입력"><br>
		<input type="password" name="pwd" placeholder="비밀번호 입력"><br><br>
		<input type="submit" value="로그인">
	</form> 
</body>
</html>