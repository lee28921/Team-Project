package kr.co.farmstory2.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.ProductDTO;

public class ProductDAO extends DBHelper{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static ProductDAO instance = new ProductDAO();
	public static ProductDAO getInstance() {
		return instance;
	}
	private ProductDAO() {}
	
	
	// 기본 CRUD
	public void insertProduct(ProductDTO dto) {
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_PRODUCT);
			psmt.setInt(1,dto.getType());
			psmt.setString(2,dto.getpName());
			psmt.setInt(3, dto.getPrice());
			psmt.setInt(4, dto.getDelivery());
			psmt.setInt(5, dto.getStock());
			psmt.setString(6,dto.getThumb1());
			psmt.setString(7,dto.getThumb2());
			psmt.setString(8,dto.getThumb3());
			psmt.setString(9,dto.getSeller());
			psmt.setString(10,dto.getEtc());
			
			psmt.executeUpdate();
			
			close();
			
		} catch (Exception e) {
			logger.error("insertProduct() error : "+e.getMessage());
		}
	}
}
