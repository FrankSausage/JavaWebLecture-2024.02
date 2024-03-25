package exam;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/exam/member")
public class memberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member m1 = new Member(1, "제임스", 27, "한국");
		Member m2 = new Member(2, "마리아", 17, "미국");
		Member m3 = new Member(3, "브라이언", 37, "미국");
		Member m4 = new Member(4, "사라", 47, "영국");
		Member m5 = new Member(5, "밥", 57, "독일");
		List<Member> mList = new ArrayList<Member>();
		
		mList.add(m1);
		mList.add(m2);
		mList.add(m3);
		mList.add(m4);
		mList.add(m5);
		
		RequestDispatcher rd = request.getRequestDispatcher("/exam/member.jsp");
		request.setAttribute("mList", mList);
		rd.forward(request, response);
	}

}
