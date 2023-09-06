package kr.co.farmstory2.controller.board;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.dto.FileDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.FileService;

@WebServlet("/board/modify.do")
public class ModifyController extends HttpServlet{

	Logger logger = LoggerFactory.getLogger(this.getClass());
	private ArticleService aService = ArticleService.INSTANCE;
	private FileService fService = FileService.INSTANCE;
	
	private static final long serialVersionUID = -727468049103988187L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파일 경로
		String path = aService.getFilePath(req);
		
		logger.debug("path : "+path);
        
		// 수신 데이터
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		String no = req.getParameter("no");
		
		// 게시글 조회
		ArticleDTO article = aService.selectArticle(no);
		req.setAttribute("article", article);
		
		logger.debug("article : "+article.toString());
		
		req.setAttribute("group", group);
		req.setAttribute("cate", cate);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/board/modify.jsp");
		dispatcher.forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("ModifyController doPost...1");
		
		// 파일 업로드
		MultipartRequest mr = aService.uploadFile(req);
		
		String no		= mr.getParameter("no");
		String group	= mr.getParameter("group");
		String cate 	= mr.getParameter("cate");
		String writer	= mr.getParameter("writer");
		String title	= mr.getParameter("title");
		String content 	= mr.getParameter("content");
		String oName	= mr.getOriginalFileName("file");
		String fno		= mr.getParameter("fno");
		String ofile	= mr.getParameter("ofile");
		FileDTO fileDto = null;
		
		logger.debug("cate : "+cate);
		logger.debug("title : "+title);
		logger.debug("content : "+content);
		logger.debug("writer : "+writer);
		logger.debug("oName : "+oName);
		logger.debug("fno : "+fno);
		logger.debug("ofile : "+ofile);
		
		ArticleDTO dto = aService.selectArticle(no);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile(oName);
		dto.setWriter(writer);
		
		// 파일이 없는 게시글에서 파일 추가시
		if(!ofile.isEmpty() && oName != null){
				dto.setFile(1);
		}
		aService.updateArticle(dto);
		
		
		// 게시글 제목, 내용만 수정시
		 if(ofile.isEmpty() && oName == null){
		
		
		}
		 else if(ofile.isEmpty()) {
			// 파일 추가
			String sName = aService.renameToFile(req, oName);
			
			// 파일 테이블 Insert
			fileDto = new FileDTO();
			fileDto.setAno(no);
			fileDto.setOfile(oName);
			fileDto.setSfile(sName);
			
			fService.insertFile(fileDto);
			
		}

		else {
			if(oName == null) { // 파일 유지
				oName = ofile;
			}
			else {
			// 파일 조회
			fileDto = fService.selectFile(fno);
			
			// 파일 변경
			String sName = aService.renameToFile(req, oName);
			
			// 파일 업데이트
			fileDto.setOfile(oName);
			fileDto.setSfile(sName);
			fileDto.setFno(fno);
			
			logger.debug("Update oName : "+oName);
			logger.debug("Update sName : "+sName);
			logger.debug("Update fno : "+fno);
			
			logger.debug("Update fileDto : "+fileDto.toString());
			
			fService.updateFile(fileDto);
			}
		}
		resp.sendRedirect("/Farmstory2/board/view.do?group="+group+"&cate="+cate+"&no="+no);
		
		
	}
}
