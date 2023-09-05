package kr.co.farmstory2.controller.board;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.farmstory2.dto.ArticleDTO;
import kr.co.farmstory2.dto.FileDTO;
import kr.co.farmstory2.dto.UserDTO;
import kr.co.farmstory2.service.ArticleService;
import kr.co.farmstory2.service.FileService;

@WebServlet("/board/write.do")
public class WriteController extends HttpServlet{

	private static final long serialVersionUID = 4770924791750877308L;
	
	private ArticleService aService = ArticleService.INSTANCE;
	private FileService fService = FileService.INSTANCE;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		UserDTO sessUser = (UserDTO) session.getAttribute("sessUser");
		
		String group = req.getParameter("group");
		String cate = req.getParameter("cate");
		
		
		if(sessUser != null) {
			
			req.setAttribute("group", group);
			req.setAttribute("cate", cate);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/board/write.jsp");
			dispatcher.forward(req, resp);
		} else {
			resp.sendRedirect("/Farmstory2/user/login.do?success=101");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파일 업로드 경로 구하기
		ServletContext ctx = req.getServletContext();
		String path = ctx.getRealPath("/upload");

		// 최대 업로드 파일 크기
		int maxSize = 1024 * 1024 * 10;

		// 파일 업로드 및 Multipart 객체 생성
		MultipartRequest mr = new MultipartRequest(req, path, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		// 데이터수신
		String group = mr.getParameter("group");
		String cate = mr.getParameter("cate");
		String writer = mr.getParameter("writer");
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String oName	= mr.getOriginalFileName("file");
		String regip = req.getRemoteAddr();
		
		// ArticleDTO 생성
		ArticleDTO dto = new ArticleDTO();
		dto.setCate(cate);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setFile(oName);
		dto.setWriter(writer);
		dto.setRegip(regip);
		
		// 게시글 작성
		int no = aService.insertArticle(dto);
		
		
		// 파일명 수정
		if(oName != null) {

			int i = oName.lastIndexOf(".");
			String ext = oName.substring(i);

			String uuid = UUID.randomUUID().toString();
			String sName = uuid + ext;

			File f1 = new File(path+"/"+oName); // 저장된 파일의 객체
			File f2 = new File(path+"/"+sName); // 가상의 파일 객체

			f1.renameTo(f2);
			
			// 파일 테이블 Insert
			FileDTO fileDto = new FileDTO();
			fileDto.setAno(no);
			fileDto.setOfile(oName);
			fileDto.setSfile(sName);

			fService.insertFile(fileDto);

		}
		
		
		resp.sendRedirect("/Farmstory2/board/list.do?group="+group+"&cate="+cate);
	
	}
}
