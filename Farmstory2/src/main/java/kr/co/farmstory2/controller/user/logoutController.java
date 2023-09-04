package kr.co.farmstory2.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/user/logout.do")
public class logoutController extends HttpServlet{

	private static final long serialVersionUID = 6609007663079133770L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		session.invalidate(); // 세션 비우기
		
		resp.sendRedirect("/Farmstory2");
	
	}
}
