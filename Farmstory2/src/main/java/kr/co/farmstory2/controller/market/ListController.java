package kr.co.farmstory2.controller.market;

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

import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.ProductService;

@WebServlet("/market/list.do")
public class ListController extends HttpServlet{

	private static final long serialVersionUID = 8543347364327031519L;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductService pService = ProductService.INSTANCE;
	private ArticleService aService = ArticleService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 데이터 수신
		String pg = req.getParameter("pg");
		String type = req.getParameter("type");
		
		logger.debug("type : " + type);
		logger.debug("pg : " + pg);
		
		// 전체보기
		if(type == null) {
			type = "0";
		}
		
		// 페이지 번호
		int currentPage = aService.getCurrentPage(pg);
		
		// 게시글 갯수
		int total = pService.selectCountProductTotal(type);
		
		// 마지막 페이지
		int lastPageNum = aService.getLastPageNum(total);
		
		// 그룹 계산
		int[] result = aService.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = aService.getPageStartNum(total, currentPage);
		
		// Limit 시작번호
		int start = aService.getStartNum(currentPage);
		logger.debug("start : "+start);
		
		// 페이지 그룹화
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("total", total);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		
		// 상품 조회
		List<ProductDTO> products = pService.selectProducts(start,type);
		req.setAttribute("products", products);
		req.setAttribute("type", type);
		
		logger.debug(products.toString());
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/market/list.jsp");
		dispatcher.forward(req, resp);
	}
}
