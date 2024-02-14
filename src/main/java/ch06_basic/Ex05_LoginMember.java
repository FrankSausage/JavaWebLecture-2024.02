package ch06_basic;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ch06/login")
public class Ex05_LoginMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/ch06/loginForm.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		
		String result = "";
		String url = "";
		RequestDispatcher rd = null;
		if(!uid.equals("james")){
			result = "존재하지 않는 아이디 입니다.";
			url = "/jw/ch06/login";
		}
		else if(!pwd.equals("1234")) {
			result = "잘못된 비밀번호 입니다.";
			url = "/jw/ch06/login";
		}
		else {
			result = "제임스님 환영합니다.";
			url = "/jw/ch06/loginResult.jsp";
		}
		rd = request.getRequestDispatcher("/ch06/alertMsg.jsp");
		request.setAttribute("result", result);
		request.setAttribute("url", url);
		rd.forward(request, response);
		
	}	

}
