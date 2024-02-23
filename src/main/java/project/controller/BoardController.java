package project.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import project.entity.Board;
import project.entity.Reply;
import project.service.BoardService;
import project.service.BoardServiceImpl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/bbs/board/list","/bbs/board/insert","/bbs/board/update","/bbs/board/delete","/bbs/board/detail"})
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService bSvc = new BoardServiceImpl();
		String title = null, content = null, sessUid = null, field = null, query = null, uid = null;
		int bid = 0, page = 0;
		Board board = null;
		HttpSession session = request.getSession();
		String requestUri = request.getRequestURI();
		String[] uri = requestUri.split("/");
		String action = uri[uri.length-1];
		String method = request.getMethod();
		RequestDispatcher rd = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		switch(action) {
			case "list":		// /jw/bbs/board/list?p=1&f=title&q=검색
				String page_ = request.getParameter("p");
				field = request.getParameter("f");
				query = request.getParameter("q");
				page = (page_ == null || page_.isEmpty()) ? 1 : Integer.parseInt(page_);
				field = (field == null || field.isEmpty()) ? "title" : field;
				query = (query == null || query.isEmpty()) ? "" : query;
				List<Board> bList = bSvc.getBoardList(page, field, query);
				
				// for pagination
				int totalBoards = bSvc.getBoardCount(field, query);
				int totalPages = (int) Math.ceil(totalBoards * 1.0 / bSvc.COUNT_PER_PAGE);
				List<String> pageList = new ArrayList<String>();
				for (int i = 1; i <= totalPages; i++) {
					pageList.add(String.valueOf(i));
				}

				session.setAttribute("currentBoardPage", page);
				session.setAttribute("field", field);
				session.setAttribute("query", query);
				request.setAttribute("bList", bList);
				request.setAttribute("pageList",pageList);
				
				rd = request.getRequestDispatcher("/WEB-INF/view/board/list.jsp");
				rd.forward(request, response);
				break;
				
			case "insert":
				sessUid = (String) session.getAttribute("sessUid");
				if(sessUid == null || sessUid.isEmpty()) {
					response.sendRedirect("/jw/bbs/user/login");
					break;
				}
				if (method.equals("GET")) {
					rd = request.getRequestDispatcher("/WEB-INF/view/board/insert.jsp");
					rd.forward(request, response);
				} else {
					title = request.getParameter("title");
					content = request.getParameter("content");
					board = new Board(title, content, sessUid);
					bSvc.insertBoard(board);
					
					response.sendRedirect("/jw/bbs/board/list?p=1");
				}
				break;
				
			case "update":
				if(method.equals("GET")) {
					bid = Integer.parseInt(request.getParameter("bid"));
					board = bSvc.getBoard(bid);
					request.setAttribute("board", board);
					
					rd = request.getRequestDispatcher("/WEB-INF/view/board/update.jsp");
					rd.forward(request, response);
				} else {
					bid = Integer.parseInt(request.getParameter("bid"));
					uid = request.getParameter("uid");
					title = request.getParameter("title");
					content = request.getParameter("content");
					board = new Board(bid, title, content);
					bSvc.updateBoard(board);
					
					response.sendRedirect("/jw/bbs/board/detail?bid=" + bid + "&uid=" + uid);
				}
				break;
				
			case "detail":
				uid = request.getParameter("uid");
				sessUid = (String) session.getAttribute("sessUid");
				bid = Integer.parseInt(request.getParameter("bid"));
				if(!uid.equals(sessUid)) {
					bSvc.increaseViewCount(bid);
				}
				board = bSvc.getBoard(bid);
				List<Reply> replyList = null;

				request.setAttribute("board", board);
				request.setAttribute("replyList", replyList);
				
				rd = request.getRequestDispatcher("/WEB-INF/view/board/detail.jsp");
				rd.forward(request, response);
				break;
				
			case "delete":
				bid = Integer.parseInt(request.getParameter("bid")); 
				bSvc.deleteBoard(bid);
				page = (Integer) session.getAttribute("currentBoardPage");
				field = (String) session.getAttribute("field");
				String query_ = (String) session.getAttribute("query");
				query = URLEncoder.encode(query_, "utf-8") ;
 				response.sendRedirect("/jw/bbs/board/list?p=" + page + "&f=" + field + "&q=" + query);
				break;
		}
	}

}
