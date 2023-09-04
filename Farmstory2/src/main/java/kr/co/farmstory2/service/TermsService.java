package kr.co.farmstory2.service;

import kr.co.farmstory2.dao.TermsDAO;
import kr.co.farmstory2.dto.TermsDTO;

public enum TermsService {
	
	INSTANCE;
	
	private TermsDAO dao = TermsDAO.getInstance();
	
	public TermsDTO selectTerm() {
		return dao.selectTerm();
	}
}
