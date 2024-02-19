<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>User Management</title>
</head>
	<body style="margin: 50px">
		<h1>로그인
			
		</h1>
			
		<hr>
		<form action="/jw/ch09/user/login" method="post">
			<input type="text" name="uid" placeholder="아이디 입력" autofocus="autofocus"><br><br>
			<input type="password" name="pwd" placeholder="비밀번호 입력"><br><br>
			<input type="submit" value="로그인">
		</form>
			<button onclick="location.href='/jw/ch09/user/register'">회원 가입</button>
	</body>
</html>