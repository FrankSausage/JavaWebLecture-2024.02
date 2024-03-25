<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>240305_시험</title>
</head>
<body style="margin: 50px;">
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>주소</th>
			<th>인덱스</th>
		</tr>
		<c:forEach var="member" items="${mList}" varStatus="loop">
			<tr>
				<td>
					<%-- 아이디 열: 홀수면 글자색을 blue, 짝수면 red --%> <c:if
						test="${member.mid % 2 == 1 }">
						<span style="color: blue">${member.mid}</span>
					</c:if> <c:if test="${member.mid % 2 == 0 }">
						<span style="color: red">${member.mid}</span>
					</c:if>
				</td>
				<td>
					<%-- 이름 열  : 첫번째줄 배경색을 yellow, 마지막 줄을 cyan 나머지는 beige --%> <c:if
						test="${loop.first}">
						<span style="background-color: yellow">${member.name}</span>
					</c:if> <c:if test="${loop.last}">
						<span style="background-color: cyan">${member.name}</span>
					</c:if> <c:if test="${not(loop.first or loop.last)}">
						<span style="background-color: beige">${member.name}</span>
					</c:if>
				</td>
				<td>
					<%-- 나이 열  : 20세 미만이면 글자색을 blue, 이상이면 red --%> <c:if
						test="${member.age lt 20}">
						<span style="color: blue">${member.age}</span>
					</c:if> <c:if test="${member.age ge 20}">
						<span style="color: red">${member.age}</span>
					</c:if>
				</td>
				<td>
					<%-- 주소 열  : 한국이면 배경색을 orange, 미국이면 skyblue, 나머지는 beige --%> <c:if
						test="${member.country eq '한국'}">
						<span style="background-color: orange">${member.country}</span>
					</c:if> <c:if test="${member.country eq '미국'}">
						<span style="background-color: skyblue">${member.country}</span>
					</c:if> <c:if test="${member.country ne '한국' and member.country ne '미국'}">
						<span style="background-color: beige">${member.country}</span>
					</c:if>
				</td>
				<td>${loop.index}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>