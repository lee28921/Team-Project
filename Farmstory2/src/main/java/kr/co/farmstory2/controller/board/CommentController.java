package kr.co.farmstory2.controller.board;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.service.ArticleService;

@WebServlet("/board/comment.do")
public class CommentController extends HttpServlet{

	private static final long serialVersionUID = 3096232538471515350L;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String type = req.getParameter("type");
		String no = req.getParameter("no");
		String content = req.getParameter("content");

		int result = 0;
		int result2 = 0;
		
		switch(type) {
			case "REMOVE":
				
				result = service.deleteComment(no);
				result2 = service.updateCountComment(no);
				
				break;
			case "MODIFY":
				
				result = service.updateComment(no, content);
				break;
		}
		
		logger.debug("type : "+type);
		logger.debug("result : "+result);
		logger.debug("result2 : "+result2);

		// Json 출력
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		json.addProperty("result2", result2);
		resp.getWriter().print(json);
	
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String parent = req.getParameter("parent");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		String regip = req.getRemoteAddr();

		logger.debug("parent : "+parent);
		logger.debug("content : "+content);
		logger.debug("writer : "+writer);
		logger.debug("regip : "+regip);
		
		// 댓글 저장
		ArticleDTO dto = new ArticleDTO();
		dto.setContent(content);
		dto.setParent(parent);
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		// 댓글 갯수
		
		int result = service.insertComment(dto);
		int result2 = service.updateCountComment(parent);
		
		// Json 출력 (AJAX 요청)
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		json.addProperty("result2", result2);
		resp.getWriter().print(json);
	}
}
