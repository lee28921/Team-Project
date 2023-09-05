package kr.co.farmstory2.service;

import java.util.List;

import kr.co.farmstory2.dao.FileDAO;
import kr.co.farmstory2.dto.FileDTO;

public enum FileService {
	
	INSTANCE;
	
	private FileDAO dao = FileDAO.getInstance();
	
	public void insertFile(FileDTO dto) {
		dao.insertFile(dto);
	}
	public FileDTO selectFile(String fno) {
		return dao.selectFile(fno);
	}
	public List<FileDTO> selectFiles() {
		return dao.selectFiles();
	}
	public void updateFile(FileDTO dto) {
		dao.updateFile(dto);
	}
	public void deleteFile(String fno) {
		dao.deleteFile(fno);
	}
}
