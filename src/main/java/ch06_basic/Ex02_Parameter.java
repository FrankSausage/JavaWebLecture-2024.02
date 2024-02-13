package ch06_basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// localhost:8080/jw/ch06/params?id=101&title=title
// localhost:8080/jw/ch06/params?id=&title=				입력값은 ""
// localhost:8080/jw/ch06/params						입력값은 null
@WebServlet("/ch06/params")
public class Ex02_Parameter extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_ = request.getParameter("id");
//		int id = Integer.parseInt(id_);		// id값이 안들어오면 예외 발생
		int id = 0;			// 입력이 안됐을 때사용하는 기본 값
		if (id_ != null && !id_.isEmpty()) {
			id = Integer.parseInt(id_);
		}
		
		String title = request.getParameter("title");
		System.out.println("GET id: " + id + ", title: " + title);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_ = request.getParameter("id");
		int id = 0;			// 입력이 안됐을 때사용하는 기본 값
		if (id_ != null && !id_.isEmpty()) {
			id = Integer.parseInt(id_);
		}
		
		String title = request.getParameter("title");
		System.out.println("POST id: " + id + ", title: " + title);
	}

}
