package co.ovmkas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.ovmkas.domain.ReplyVO;

public interface ReplyMapper {
	int insert(ReplyVO vo);
	
	ReplyVO read(Long rno);
	
	List<ReplyVO> getList(@Param("bno") Long bno, @Param("rno")Long rno );
	
	//비동기시에는 반환값으로 확인한다
	int update(ReplyVO vo);
	
	int delete(Long rno);
	
	
	int deleteByBno(Long bno);
}
