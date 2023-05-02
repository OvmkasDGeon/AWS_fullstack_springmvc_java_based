package co.ovmkas.service;

import java.util.List;

import co.ovmkas.domain.AttachFileDTO;
import co.ovmkas.domain.BoardVO;
import co.ovmkas.domain.Criteria;

public interface BoardService {
	void register(BoardVO vo);
	
	BoardVO get(Long bno);
	
	boolean modify(BoardVO vo);
	
	boolean remove(Long bno);
	
	List<BoardVO> getList(Criteria criteria);
	
	int getTotalCnt(Criteria cri);
	
	String deleteFile(AttachFileDTO dto);
}
