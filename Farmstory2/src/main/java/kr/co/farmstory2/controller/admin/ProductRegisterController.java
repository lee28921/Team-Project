package kr.co.farmstory2.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.farmstory2.dto.ProductDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.ProductService;

@WebServlet("/admin/productRegister.do")
public class ProductRegisterController extends HttpServlet{

	private static final long serialVersionUID = 4988836274699503222L;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService aService = ArticleService.INSTANCE;
	private ProductService pService = ProductService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/productRegister.jsp");
		dispatcher.forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String path = pService.getThumbPath(req);
		
		MultipartRequest mr = pService.uploadThumb(req);
		
		String productName	= mr.getParameter("productName");
		String type			= mr.getParameter("type");
		String price		= mr.getParameter("price");
		String delivery		= mr.getParameter("delivery");
		String stock		= mr.getParameter("stock");
		String thumb1		= mr.getOriginalFileName("thumb1");
		String thumb2		= mr.getOriginalFileName("thumb2");
		String thumb3		= mr.getOriginalFileName("thumb3");
		String seller		= mr.getParameter("seller");
		String etc			= mr.getParameter("etc");
		
		ProductDTO dto = new ProductDTO(path);
		dto.setpName(productName);
		dto.setType(type);
		dto.setPrice(price);
		dto.setDelivery(delivery);
		dto.setStock(stock);
		dto.setThumb1(thumb1);
		dto.setThumb2(thumb2);
		dto.setThumb3(thumb3);
		dto.setSeller(seller);
		dto.setEtc(etc);
		
		pService.insertProduct(dto);
		
		resp.sendRedirect("/Farmstory2/admin/productList.do");
		
	}
	
}
