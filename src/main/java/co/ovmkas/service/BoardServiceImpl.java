package co.ovmkas.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ovmkas.controller.UploadController;
import co.ovmkas.domain.AttachFileDTO;
import co.ovmkas.domain.AttachVO;
import co.ovmkas.domain.BoardVO;
import co.ovmkas.domain.Criteria;
import co.ovmkas.mapper.AttachMapper;
import co.ovmkas.mapper.BoardMapper;
import co.ovmkas.mapper.ReplyMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@ToString
@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
	
//	@Setter 클래스 생성자로 인해 블럭
	private final BoardMapper boardMapper;
	private final AttachMapper attachMapper;
	private ReplyMapper replymapper;
	
	
	@Override
	@Transactional
	public void register(BoardVO vo) {
		boardMapper.insertSelectKey(vo);
		Long bno = vo.getBno();
	    List<AttachVO> list = vo.getAttachs();
	    int idx = 0;
	    /*if(list == null || list.size()==0){
	    	return;
	    }*/
	    for(AttachVO attach : list){
	    	attach.setBno(bno);
	    	attach.setOdr(idx++);
	    	attachMapper.insert(attach);
	    }
	}

	@Override
	public BoardVO get(Long bno) {
		return boardMapper.read(bno);
	}

	@Override
	@Transactional
	public boolean modify(BoardVO vo) {
		attachMapper.deleteAll(vo.getBno());
		int idx=0;
		List<AttachVO> list = vo.getAttachs();
		for(AttachVO attach : list){
			attach.setBno(vo.getBno());
			attach.setOdr(idx++);
			attachMapper.insert(attach);
		}
		return boardMapper.update(vo) > 0;
	}

	@Override
	@Transactional
	public boolean remove(Long bno) {
		replymapper.deleteByBno(bno);
		attachMapper.deleteAll(bno);
		return boardMapper.delete(bno) > 0;
	}

	@Override
	public List<BoardVO> getList(Criteria criteria) {
		return boardMapper.getListWithPaging(criteria);
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		// TODO Auto-generated method stub
		return boardMapper.getTotalCnt(cri);
	}

	@Override
	public String deleteFile(AttachFileDTO dto) {
			log.info(dto);
			String s = UploadController.getPATH() + "/" + dto.getPath() +"/" + dto.getUuid() + "_" +dto.getName();
			File file = new File(s);
			file.delete();
			if(dto.isImage()){
				s = UploadController.getPATH() + "/" + dto.getPath() +"/s_" + dto.getUuid() + "_" +dto.getName();
				file = new File(s);
				file.delete();
			}
			return dto.getUuid();
	}
}
