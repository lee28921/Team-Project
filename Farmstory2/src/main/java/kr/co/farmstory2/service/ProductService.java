package kr.co.farmstory2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.dao.ProductDAO;
import kr.co.farmstory2.dto.ProductDTO;

public enum ProductService {
	
	INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private ProductDAO dao = ProductDAO.getInstance();
	
	public void insertProduct(ProductDTO dto) {
		dao.insertProduct(dto);
	}
	
}
