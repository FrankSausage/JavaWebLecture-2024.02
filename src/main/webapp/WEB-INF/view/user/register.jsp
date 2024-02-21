<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<%@ include file="../common/_head.jspf" %>
	<style>
    	td { text-align: center; }
	</style>	
</head>
<body class="bg-light">
  <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
    <div class="container-fluid">
      <img src="/jw/img/ck-logo.png" height="60">
      <div class="p-2 bg-dard justify-content-center">
        <img src="https://picsum.photos/1500/180" width="100%">
      </div>
    </div>
  </nav>
  <div class="container" style="margin-top: 220px;">
    <div class="row">
      <div class="col-4"></div>
      <div class="col-4">
        <div class="card">
          <div class="card-body">
            <div class="card-title"><h3><strong>사용자 가입</strong></h3></div>
            <hr>
            <form action="/jw/bbs/user/register" method="post">
              <table class="table table-borderless">
                <tr>
                  <td style="width: 40%;"><label class="col-form-label">사용자 ID</label></td>
                  <td style="width: 60%;"><input type="text" name="uid" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">패스워드</label></td>
                  <td><input type="password" name="pwd" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">패스워드 확인</label></td>
                  <td><input type="password" name="pwd2" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">사용자 이름</label></td>
                  <td><input type="text" name="uname" class="form-control"></td>
                </tr>
                <tr>
                  <td><label class="col-form-label">이메일</label></td>
                  <td><input type="text" name="email" class="form-control"></td>
                </tr>
                <tr>
                  <td colspan="2">
                    <button class="btn btn-primary" type="submit">확인</button>
                    <button class="btn btn-secondary" type="reset">취소</button>
                  </td>
                </tr>
              </table>
            </form>
            <p class="mt-3">
              <span class="me-3">이미 사용자 계정이 있으신가요?</span>
              <a href="/jw/bbs/user/login">로그인</a>
            </p>
          </div>
        </div>
      </div>
      <div class="col-4"></div>
    </div>
  </div>
  
  <%@ include file="../common/_bottom.jspf" %>
</body>
</html>