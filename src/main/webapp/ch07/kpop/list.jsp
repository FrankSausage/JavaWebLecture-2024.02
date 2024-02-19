<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Kpop List</title>
	<style>
		th, td { padding: 3px }
	</style>
</head>
<body style="margin: 50px">
	<h1>K-pop 목록
		<button style="margin-left: 100px" onclick="location.href='/jw/ch07/kpop/insertArtist'">아티스트 추가</button>
		<button style="margin-left: 30px" onclick="location.href='/jw/ch07/kpop/insertSong'">노래 추가</button>
	</h1>
	<hr>
	<table border="1">
		<tr><th>아이디</th><th>아티스트</th><th>데뷔일자</th>
			<th>히트송</th><th>히트송 가사</th><th>데이터 삭제</th></tr>
		<c:forEach var="kpop" items="${list}"> 	
			<tr>
				<td>${kpop.aid}</td>
				<td><a href="/jw/ch07/kpop/updateArtist?aid=${kpop.aid}">${kpop.name}</a></td>
				<td>${kpop.debut}</td>
				<td><a href="/jw/ch07/kpop/updateSong?sid=${kpop.sid}">${kpop.title}</a></td>	
				<td>${kpop.lyrics}</td>	
				<td>
					<a href="/jw/ch07/kpop/deleteArtist?aid=${kpop.aid}">아티스트</a>
					<a href="/jw/ch07/kpop/deleteSong?sid=${kpop.sid}">노래</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>

		