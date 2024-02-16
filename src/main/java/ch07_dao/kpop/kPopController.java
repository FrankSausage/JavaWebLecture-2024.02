package ch07_dao.kpop;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet({"/ch07/kpop/list","/ch07/kpop/insertArtist","/ch07/kpop/insertSong",
	"/ch07/kpop/updateArtist","/ch07/kpop/updateSong",
	"/ch07/kpop/deleteArtist","/ch07/kpop/deleteSong"})
public class kPopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	KpopDao kDao = new KpopDaoImpl();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Artist arti = null;
		Song song = null;
		int aid = 0, sid = 0;
		String name = null, debut = null, title = null, lyrics = null ;
		String requestUri = request.getRequestURI();
		String[] uri = requestUri.split("/");
		String action = uri[uri.length-1];
		String method = request.getMethod();
		RequestDispatcher rd = null;
		
		switch(action) {
		case "list":
			List<Kpop> list = kDao.getKpopList();
			rd = request.getRequestDispatcher("/ch07/kpop/list.jsp");
			request.setAttribute("list", list);
			rd.forward(request, response);
			
			break;
		case "insertArtist":
			if(method.equals("GET")) {
				rd = request.getRequestDispatcher("/ch07/kpop/insertArtist.jsp");
				rd.forward(request, response);
			} else {
				name = request.getParameter("name");
				debut = request.getParameter("debut");
				sid = Integer.parseInt(request.getParameter("sid")) ;
				arti = new Artist(name,LocalDate.parse(debut), sid);
				kDao.insertArtist(arti);
				
				response.sendRedirect("/jw/ch07/kpop/list");
			}
			break;
		case "insertSong":
			if(method.equals("GET")) {
				rd = request.getRequestDispatcher("/ch07/kpop/insertSong.jsp");
				rd.forward(request, response);
			} else {
				title = request.getParameter("title");
				lyrics = request.getParameter("lyrics");
								
				song = new Song(title, lyrics);
				kDao.insertSong(song);
				
				response.sendRedirect("/jw/ch07/kpop/list");
			}
			break;
		case "updateArtist":
			if(method.equals("GET")) {
				rd = request.getRequestDispatcher("/ch07/kpop/updateArtist.jsp");
				rd.forward(request, response);
			} else {
				aid = Integer.parseInt(request.getParameter("aid"));
				name = request.getParameter("name");
				debut = request.getParameter("debut");
				arti = new Artist(name, LocalDate.parse(debut), aid);
				kDao.insertArtist(arti);
				
				
				response.sendRedirect("/jw/ch07/kpop/list");
			}
			break;
		case "updateSong":
			if(method.equals("GET")) {
				rd = request.getRequestDispatcher("/ch07/kpop/updateSong.jsp");
				rd.forward(request, response);
			} else {
				
				
				response.sendRedirect("/jw/ch07/kpop/list");
			}
			break;
		case "deleteArtist":
			aid = Integer.parseInt(request.getParameter("aid"));
			kDao.deleteArtist(aid);
			
			response.sendRedirect("/jw/ch07/kpop/list");
			break;
		case "deleteSong":
			sid = Integer.parseInt(request.getParameter("sid"));
			kDao.deleteArtist(sid);
			
			response.sendRedirect("/jw/ch07/kpop/list");
			break;
			
		}
	}

}
