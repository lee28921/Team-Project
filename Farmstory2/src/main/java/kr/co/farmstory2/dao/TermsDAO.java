package kr.co.farmstory2.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.TermsDTO;

public class TermsDAO extends DBHelper{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static TermsDAO instance = new TermsDAO();
	public static TermsDAO getInstance() {
		return instance;
	}
	private TermsDAO() {};
	
	public TermsDTO selectTerm() {
		TermsDTO dto = new TermsDTO();
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_TERM);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setTerms(rs.getString(1));
				dto.setPrivacy(rs.getString(2));
			}
			close();
		} catch(Exception e) {
			logger.error("selectTerm() error : "+e.getMessage());
		}
		return dto;
	}
}
