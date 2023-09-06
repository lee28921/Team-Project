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
	public FileDTO selectFile(String fno) {
		FileDTO dto = null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.SELECT_FILE);
			psmt.setString(1, fno);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto = new FileDTO();
				dto.setFno(rs.getInt(1));
				dto.setAno(rs.getInt(2));
				dto.setOfile(rs.getString(3));
				dto.setSfile(rs.getString(4));
				dto.setDownload(rs.getInt(5));
				dto.setRdate(rs.getString(6));
				
			}
			close();
			
		} catch(Exception e) {
			logger.error("selectFile() error : "+e.getMessage());
		}
		
		return dto;
	}
	public List<FileDTO> selectFiles() {
		return null;
	}
	public void updateFile(FileDTO dto) {
		logger.debug("DAO file : "+dto.toString());
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.UPDATE_FILE);
			psmt.setString(1, dto.getOfile());
			psmt.setString(2, dto.getSfile());
			psmt.setInt(3, dto.getFno());
			
			psmt.executeUpdate();
			
			close();
			
		} catch(Exception e) {
			logger.error("updateFile() error : "+e.getMessage());
		}
	}
	public void deleteFile(String fno) {
		
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(SQL.DELETE_FILE);
			psmt.setString(1, fno);
			
			psmt.executeUpdate();
			
			close();
			
		} catch(Exception e) {
			logger.error("deleteFile() error : "+e.getMessage());
		}
		
	}
}
