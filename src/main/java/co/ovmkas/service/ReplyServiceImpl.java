package co.ovmkas.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ovmkas.domain.ReplyVO;
import co.ovmkas.mapper.BoardMapper;
import co.ovmkas.mapper.ReplyMapper;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	private BoardMapper boardMapper;
	private ReplyMapper replymapper;

	@Override
	@Transactional
	public int register(ReplyVO vo) {
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		return replymapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		return replymapper.read(rno);
	}

	@Override
	@Transactional
	public int remove(Long rno) {
		boardMapper.updateReplyCnt(get(rno).getBno(), -1);
		return replymapper.delete(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		return replymapper.update(vo);
	}

	@Override
	public List<ReplyVO> getList(Long bno, Long rno) {
		return replymapper.getList(bno, rno);
	}
}
