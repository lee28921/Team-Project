package kr.co.farmstory2.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.farmstory2.dao.ArticleDAO;
import kr.co.farmstory2.dto.ArticleDTO;

public enum ArticleService {

	INSTANCE;
	private ArticleDAO dao = ArticleDAO.getInstance();
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public int insertArticle(ArticleDTO dto) {
		return dao.insertArticle(dto);
	}
	public ArticleDTO selectArticle(int no) {
		return dao.selectArticle(no);
	}
	public List<ArticleDTO> selectArticles(String cate,int no) {
		return dao.selectArticles(cate,no);
	}
	public void updateArticle(ArticleDTO dto) {
		dao.updateArticle(dto);
	}
	public void deleteArticle(int no) {
		dao.deleteArticle(no);
	}
	public int selectCountTotal(String cate) {
		return dao.selectCountTotal(cate);
	}
	
	
	
	/*
	 *  파일 기능
	 */
	
	// 파일 경로
	public String getFilePath(HttpServletRequest req) {
		
		// 파일 업로드 경로 구하기
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/upload");

		return path;
		
	}
	// 파일명 수정
	public String renameToFile(HttpServletRequest req, String oName) {
		// 파일 경로 구하기
		String path = getFilePath(req);
		
		int i = oName.lastIndexOf(".");
		String ext = oName.substring(i);

		String uuid = UUID.randomUUID().toString();
		String sName = uuid + ext;

		File f1 = new File(path+"/"+oName); // 저장된 파일의 객체
		File f2 = new File(path+"/"+sName); // 가상의 파일 객체

		f1.renameTo(f2);
		
		return sName;
	}
	
	
	public MultipartRequest uploadFile(HttpServletRequest req) {
		// 파일 경로 구하기
		String path = getFilePath(req);
		
		// 최대 업로드 파일 크기
		int maxSize = 1024 * 1024 * 10;

		// 파일 업로드 및 Multipart 객체 생성
		MultipartRequest mr = null;
		
		try {
			
			mr = new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		} catch (IOException e) {
			logger.error("getUploadPath() error : "+e.getMessage());
		}
		
		return mr;
	}
	
	
	/*
	 * 글목록
	 */
	
	// 페이지 번호
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		
		// 현재 페이지 계산
		if(pg != null){
			currentPage = Integer.parseInt(pg);
		}
		
		return currentPage;
	}
	
	// 마지막 페이지 번호
	public int getLastPageNum(int total) {
		int lastPageNum = 0;
		
		// 페이지 번호 계산
		if(total % 10 == 0){
			lastPageNum = (total / 10);
		}else{
			lastPageNum = (total / 10) + 1;
		}
		
		return lastPageNum;
	}
	
	// 페이지 그룹
	public int[] getPageGroupNum(int currentPage, int lastPageNum) {
		int pageGroupCurrent = (int) Math.ceil(currentPage / 10.0);;
		int pageGroupStart = (pageGroupCurrent - 1) * 10 + 1; 
		int pageGroupEnd = pageGroupCurrent * 10;		

		if(pageGroupEnd > lastPageNum){
			pageGroupEnd = lastPageNum;
		}
		
		int[] result = {pageGroupStart, pageGroupEnd};
		
		return result;
	}
	
	// 페이지 시작번호
	public int getPageStartNum(int total, int currentPage) {
		int start = (currentPage - 1) * 10;
		return total - start;
	}
	
	// Limit 시작번호
	public int getStartNum(int currentPage) {
		return (currentPage - 1) * 10;
	}
	
}
