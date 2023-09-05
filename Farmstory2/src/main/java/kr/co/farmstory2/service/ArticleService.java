package kr.co.farmstory2.service;

import java.util.List;

import kr.co.farmstory2.dao.ArticleDAO;
import kr.co.farmstory2.dto.ArticleDTO;

public enum ArticleService {

	INSTANCE;
	private ArticleDAO dao = ArticleDAO.getInstance();
	
	
	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
	}
	public ArticleDTO selectArticle(int no) {
		return dao.selectArticle(no);
	}
	public List<ArticleDTO> selectArticles() {
		return dao.selectArticles();
	}
	public void updateArticle(ArticleDTO dto) {
		dao.updateArticle(dto);
	}
	public void deleteArticle(int no) {
		dao.deleteArticle(no);
	}
}
