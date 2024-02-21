package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project.entity.User;
import project.service.UserService;
import project.service.UserServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet({ "/bbs/user/list", "/bbs/user/register", "/bbs/user/login", "/bbs/user/logout", "/bbs/user/update",
		"/bbs/user/delete" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService uSvc = new UserServiceImpl();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uid = null, pwd = null, pwd2 = null, uname = null, email = null, msg = "", url = "", sessUid = "";
		User user = null;
		HttpSession session = request.getSession();
		String requestUri = request.getRequestURI();
		String[] uri = requestUri.split("/");
		String action = uri[uri.length-1];
		String method = request.getMethod();
		RequestDispatcher rd = null;
		
		switch(action) {
		case "list" :
			String page_ = request.getParameter("page");
			int page = (page_ == null || page_.isEmpty()) ? 1 : Integer.parseInt(page_);
			List<User> uList = uSvc.getUserList(page);
			request.setAttribute("uList", uList);
			rd = request.getRequestDispatcher("/WEB-INF/view/user/list.jsp");
			rd.forward(request, response);
			break;
			
		case "register" :
			if (method.equals("GET")) {
				rd = request.getRequestDispatcher("/WEB-INF/view/user/register.jsp");
				rd.forward(request, response);
			} else {
				uid = request.getParameter("uid");
				pwd = request.getParameter("pwd");
				pwd2 = request.getParameter("pwd2");
				uname = request.getParameter("uname");
				email = request.getParameter("email");
				
				if(uSvc.getUserByUid(uid) != null) {
					msg = "이미 존재하는 계정입니다.";
					url = "/jw/bbs/user/register";
				} else if (!pwd.equals(pwd2)) {
					msg = "비밀번호가 일치하지 않습니다.";
					url = "/jw/bbs/user/register";
				} else {
					user = new User(uid,pwd,uname,email, LocalDate.now());
					uSvc.registerUser(user);
					msg = "가입이 완료 되었습니다."  ;
					url = "/jw/bbs/user/list?page=1";
				}
				rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				rd.forward(request, response);
			}
			break;
			
		case "login" :
			if (method.equals("GET")) {
				rd = request.getRequestDispatcher("/WEB-INF/view/user/login.jsp");
				rd.forward(request, response);
			} else {
				uid = request.getParameter("uid");
				pwd = request.getParameter("pwd");
				int result = uSvc.login(uid, pwd);
				if(result == uSvc.USER_NOT_EXIST) {
					msg = "계정이 존재하지 않습니다.";
					url = "/jw/bbs/user/login";
				} else if(result == uSvc.WRONG_PASSWORD) {
					msg = "비밀번호가 올바르지 않습니다.";
					url = "/jw/bbs/user/login";
				} else {
					user = uSvc.getUserByUid(uid);
					session.setAttribute("sessUid", uid);
					session.setAttribute("sessUname", user.getUname());
					msg = user.getUname() + " 님 환영합니다.";
					url = "/jw/bbs/user/list?page=1";
				}
				rd = request.getRequestDispatcher("/WEB-INF/view/common/alertMsg.jsp");
				request.setAttribute("msg", msg);
				request.setAttribute("url", url);
				rd.forward(request, response);
			}
			break;
			
		case "logout" :
			session.invalidate();
			response.sendRedirect("/jw/bbs/user/list?page=1");
			break;
			
		case "update" :
			if (method.equals("GET")) {
				uid = request.getParameter("uid");
				user = uSvc.getUserByUid(uid);
				rd = request.getRequestDispatcher("/WEB-INF/view/user/update.jsp");
				request.setAttribute("user", user);
				rd.forward(request, response);
			} else {
				uid = request.getParameter("uid");
				pwd = request.getParameter("pwd");
				pwd2 = request.getParameter("pwd2");
				String hashedPwd = request.getParameter("hashedpwd");
				uname = request.getParameter("uname");
				email = request.getParameter("email");
				if(pwd != null && pwd.equals(pwd2)) {
					hashedPwd = BCrypt.hashpw(pwd2, BCrypt.gensalt());
				}
				user = new User(uid, hashedPwd, uname, email);
				uSvc.updateUser(user);
				response.sendRedirect("/jw/bbs/user/list?page=1");
			}
			break;
			
		case "delete" :
			uid = request.getParameter("uid");
			uSvc.deleteUser(uid);
			sessUid = (String) session.getAttribute("sessUid");
			if(!sessUid.equals("admin")) {
				session.invalidate();
			}
			response.sendRedirect("/jw/bbs/user/list?page=1");
			break;
		}
	}

}
