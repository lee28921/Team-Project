package kr.co.farmstory2.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.farmstory2.db.DBHelper;
import kr.co.farmstory2.db.SQL;
import kr.co.farmstory2.dto.FileDTO;

public class FileDAO extends DBHelper{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static FileDAO instance = new FileDAO();
	public static FileDAO getInstance() {
		return instance;
	}
	private FileDAO() {}
	
	public void insertFile(FileDTO dto) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.INSERT_FILE);
			psmt.setInt(1, dto.getAno());
			psmt.setString(2, dto.getOfile());
			psmt.setString(3, dto.getSfile());
			
			psmt.executeUpdate();
			close();
			
		} catch (Exception e) {
			logger.error("insertFile() error : "+e.getMessage());
		}
		
	}
	public FileDTO selectFile() {
		return null;
	}
	public List<FileDTO> selectFiles() {
		return null;
	}
	public void updateFile(FileDTO dto) {}
	public void deleteFile(String fno) {}
}
