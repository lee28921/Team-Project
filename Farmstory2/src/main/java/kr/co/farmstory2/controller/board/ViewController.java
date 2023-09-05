package kr.co.farmstory2.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.dto.FileDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.FileService;

@WebServlet("/board/view.do")
public class ViewController extends HttpServlet{

	private static final long serialVersionUID = -384250853980710797L;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService aService = ArticleService.INSTANCE;
	private FileService fService = FileService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		// 수신 데이터
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		String no = req.getParameter("no");
		
		logger.debug("Article no : "+no);
		
		// 게시글 보기
		ArticleDTO article = aService.selectArticle(no);
		req.setAttribute("article", article);

		logger.debug("article fileCount : "+article.getFile());
		
		FileDTO fileDto = fService.selectFile(no);
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/view.jsp");
		dispatcher.forward(req, resp);
	}
}
