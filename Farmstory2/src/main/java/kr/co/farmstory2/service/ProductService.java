package kr.co.farmstory2.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.farmstory2.dao.ProductDAO;
import kr.co.farmstory2.dto.ProductDTO;

public enum ProductService {
	
	INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductDAO dao = ProductDAO.getInstance();
	
	public void insertProduct(ProductDTO dto) {
		dao.insertProduct(dto);
	}
	public ProductDTO selectProduct(int pNo) {
		return dao.selectProduct(pNo);
	}
	public List<ProductDTO> selectProducts(int start) {
		return dao.selectProducts(start);
	}
	public void updateProduct(ProductDTO dto) {
		dao.updateProduct(dto);
	}
	public void deleteProduct(int pNo) {
		dao.deleteProduct(pNo);
	}
	
	// 추가
	public int selectCountProductTotal() {
		return dao.selectCountProductTotal();
	}
	
	/*
	 * 파일
	 */
	
	// 파일 경로
	public String getThumbPath(HttpServletRequest req) {
		
		// 파일 업로드 경로 구하기
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/thumb");

		return path;
		
	}
	
	// 업로드 파일
	public MultipartRequest uploadThumb(HttpServletRequest req) {
		// 파일 경로 구하기
		String path = getThumbPath(req);
		
		// 최대 업로드 파일 크기
		int maxSize = 1024 * 1024 * 10;

		// 파일 업로드 및 Multipart 객체 생성
		MultipartRequest mr = null;
		
		try {
			
			mr = new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		} catch (IOException e) {
			logger.error("getUploadThumb() error : "+e.getMessage());
		}
		
		return mr;
	}
}
