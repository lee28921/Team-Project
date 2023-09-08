package kr.co.farmstory2.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.service.ArticleService;

@WebServlet(value = {"","/index.do"})
public class IndexController extends HttpServlet{

	private static final long serialVersionUID = -5141473195026626452L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService service = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<ArticleDTO> latests1 = service.selectLatests("story", 5);
		List<ArticleDTO> latests2 = service.selectLatests("grow", 5);
		List<ArticleDTO> latests3 = service.selectLatests("school", 5);

		List<ArticleDTO> tabLatests1 = service.selectLatests("notice", 3);
		List<ArticleDTO> tabLatests2 = service.selectLatests("qna", 3);
		List<ArticleDTO> tabLatests3 = service.selectLatests("faq", 3);
		
		logger.debug("latests1 : "+latests1.toString());
		logger.debug("latests2 : "+latests2.toString());
		logger.debug("latests3 : "+latests3.toString());
		logger.debug("tabLatests1 : "+tabLatests1.toString());
		logger.debug("tabLatests2 : "+tabLatests2.toString());
		logger.debug("tabLatests3 : "+tabLatests3.toString());
		
		
		req.setAttribute("latests1", latests1);
		req.setAttribute("latests2", latests2);
		req.setAttribute("latests3", latests3);
		req.setAttribute("tabLatests1", tabLatests1);
		req.setAttribute("tabLatests2", tabLatests2);
		req.setAttribute("tabLatests3", tabLatests3);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
	
	}
}
