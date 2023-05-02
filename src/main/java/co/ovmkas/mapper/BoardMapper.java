package co.ovmkas.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.ovmkas.domain.BoardVO;
import co.ovmkas.domain.Criteria;

public interface BoardMapper {
	//목록 조회  
	List<BoardVO> getList();//BoardMapper.xml에서 설정 해야됨
	List<BoardVO> getListWithPaging(Criteria criteria);

	//글 등록
	void insert(BoardVO  vo);

	//글 등록

	void insertSelectKey(BoardVO  vo);
	
	//글 조회
	BoardVO read(Long bno);
	
	//삭제
	int delete(Long bno);
	
	//수정
	int update(BoardVO  vo);
	
	//갯수
	int getTotalCnt(Criteria cri);
	
	//댓글 갯수 반영
	void updateReplyCnt(@Param("bno")Long bno, @Param("amount")int amount);
}
