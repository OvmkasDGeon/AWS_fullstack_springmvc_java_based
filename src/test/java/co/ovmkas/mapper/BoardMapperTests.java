package co.ovmkas.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.ovmkas.domain.BoardVO;
import co.ovmkas.domain.Criteria;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testGetList(){
		boardMapper.getList().forEach(log::info);
	}
	
	@Test
	public void testGetListWithPaging(){
		boardMapper.getListWithPaging(new Criteria(1, 10, "TW","1")).forEach(log::info);
	}
	
	@Test
	public void testGetTotalCnt(){
		log.info(boardMapper.getTotalCnt(new Criteria()));
		log.info(boardMapper.getTotalCnt(new Criteria(1, 10)));
		log.info(boardMapper.getTotalCnt(new Criteria(1, 10, "tw","1")));
	}
	
	
//	@Test
//	public void testGetListWithPaging(){
//		boardMapper.getListWithPaging(new Criteria(3, 10)).forEach(log::info);
//	}
	
	@Test
	public void testInsert(){
		BoardVO vo = new BoardVO();
		vo.setTitle("테스트코드 작성insert() 제목");
		vo.setContent("테스트코드 작성insert() 내용");
		vo.setWriter("작성자");
		boardMapper.insert(vo);
		log.info(vo);
	}
	@Test
	public void testInsertSelectKey(){
		BoardVO vo = new BoardVO();
		vo.setTitle("테스트코드 작성insertSelectKey() 제목");
		vo.setContent("테스트코드 작성insertSelectKey() 내용");
		vo.setWriter("작성자");
		boardMapper.insertSelectKey(vo);
		log.info(vo);
	}
	
	@Test
	public void testRead(){
		Long bno = 524548L;
		log.warn(boardMapper.read(bno));
	}
	
	@Test
	public void testDelete(){
		Long bno = 11L;
		log.info(boardMapper.read(bno));
		log.info(boardMapper.delete(bno));
		log.info(boardMapper.read(bno));
	}
	
	@Test
	public void testUpdate(){
		BoardVO vo = boardMapper.read(25L);
		vo.setTitle("수정된 제목");
		vo.setContent("수정된 내용");
		vo.setWriter("user01");
		log.info(vo);
		boardMapper.update(vo);
		
		BoardVO vo2 = boardMapper.read(25L);
		log.info(vo2);
		
		log.info(vo.equals(vo2));
		
		
	}
	
}
