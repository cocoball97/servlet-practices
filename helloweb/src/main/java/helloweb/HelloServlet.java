package helloweb;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// getParameter : 클라이언트가 전송한 HTTP요청의 매개변수를 가져오는데 사용되는 메서드 
		String id = request.getParameter("id");
		String no = request.getParameter("no");
		
		System.out.println(id + ":" + no);
		
		// 인코딩 설정
		response.setContentType("text/html; charset=utf-8");
		
		// 빈 개행 보내 버퍼 비우기(flush)
		PrintWriter out = response.getWriter();
		out.print("<h1>Hello " + id + "</h1>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
