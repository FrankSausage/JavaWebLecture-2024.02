<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>User Management</title>
</head>
<body style="margin: 50px">
	<h1>사용자 수정</h1>
	<hr>
	<form action="/jw/ch09/user/update" method="post">
		<input type="hidden" name="uid" value="${user.uid}"><br><br>
		<input type="hidden" name="hashedPwd" value="${user.pwd}"><br><br>
		<input type="text" name="uid" value="${user.uid}" disabled><br><br>
		<input type="password" name="pwd"  placeholder="패스워드"><br><br>
		<input type="password" name="pwd2" placeholder="패스워드 확인"><br><br>
		<input type="text" name="uname" value="${user.uname}"><br><br>
		<input type="text" name="email" value="${user.email}"><br><br>
		<input type="submit" value="확인">
	</form>
</body>
</html>