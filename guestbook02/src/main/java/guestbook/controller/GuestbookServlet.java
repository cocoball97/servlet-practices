package guestbook.controller;

import java.io.IOException;
import java.util.List;

import guestbook.dao.GuestbookDao;
import guestbook.vo.GuestbookVo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/gb")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// GET : 파라미터와 URL 브라우저 주소창에 나타남
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// getParameter : 클라이언트가 전송한 매개변수를 가져옴
		String action = request.getParameter("a");
		if("add".equals(action)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String contents = request.getParameter("contents");
			
			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);
			
			new GuestbookDao().insert(vo);
			// 할일을 마치고 해당 경로로 이동  
			// insert 는 jsp 필요없이 redirect
			response.sendRedirect("/guestbook02/gb");
		} else if("deleteform".equals(action)){
			// 경로 객체 생성하고 request, response 객체를 .jsp에 전달 
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/deleteform.jsp");
			rd.forward(request, response);
		} else if("delete".equals(action)){
			Long id = Long.parseLong(request.getParameter("id"));
			String password = request.getParameter("password");
			
			new GuestbookDao().deleteByIdAndPassword(id, password);
			
			response.sendRedirect("/guestbook02/gb");
		} else {
			List<GuestbookVo> list = new GuestbookDao().findAll();
			request.setAttribute("list", list);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
			rd.forward(request, response);
		}
	}
	// POST : 주소창에 파라미터값이 나타나지 않음
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}