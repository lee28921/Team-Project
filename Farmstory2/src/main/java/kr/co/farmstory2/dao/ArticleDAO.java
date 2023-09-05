package kr.co.farmstory2.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.ArticleDTO;

public class ArticleDAO extends DBHelper{
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static ArticleDAO instance = new ArticleDAO();
	public static ArticleDAO getInstance() {
		return instance;
	}
	private ArticleDAO() {}
	
	
	public int insertArticle(ArticleDTO dto) {
		int no = 0;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, dto.getCate());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setInt(4, dto.getFile());
			psmt.setString(5, dto.getWriter());
			psmt.setString(6, dto.getRegip());
			
			psmt.executeUpdate();
			rs = stmt.executeQuery(SQL.SELECT_MAX_NO); // 해당 게시글의 글번호 조회
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
			
			close();
			
		} catch(Exception e) {
			logger.error("insertArticle() error : "+e.getMessage());
		}
		
		return no;
		
	}
	public ArticleDTO selectArticle(int no) {
		return null;
	}
	public List<ArticleDTO> selectArticles() {
		return null;
	}
	public void updateArticle(ArticleDTO dto) {}
	public void deleteArticle(int no) {}
}
