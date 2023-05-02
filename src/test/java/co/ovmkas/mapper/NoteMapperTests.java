package co.ovmkas.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.ovmkas.domain.MemberVO;
import co.ovmkas.domain.NoteVO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class NoteMapperTests {
	
	@Autowired
	private NoteMapper mapper;
	@Autowired
	private MemberMapper memberMapeer;
	/*
	@Test
	public void testInsert2(){
		List<MemberVO> members = memberMapeer.getList();
		int i=1;
		for(MemberVO vo : members){
			for(MemberVO vo2 : members){
			NoteVO noteVO = new NoteVO();
			noteVO.setSender(vo.getId());
			noteVO.setReceiver(vo2.getId());
			noteVO.setMessage("mapper 테스트 발송" + i++);
			mapper.insert(noteVO);
			}
		}
	}*/
	@Test
	public void testInsert(){
		NoteVO noteVO = new NoteVO();
		noteVO.setSender("id1");
		noteVO.setReceiver("id2");
		noteVO.setMessage("mapper 테스트발송");
		mapper.insert(noteVO);
	}
	
	@Test
	public void testSelectOne(){
		mapper.selectOne(2L);
	}
	
	@Test
	public void testUpdate(){
		mapper.update(44L);
	}
	
	@Test
	public void testDelete(){
		mapper.delete(2L);
	}
	
	@Test
	public void testSendList(){
		mapper.sendList("id1").forEach(log::info);
	}
	
	@Test
	public void testReceiveList(){
		mapper.receiveList("id3").forEach(log::info);
	}
	
	@Test
	public void testReceiveCheckedList(){
		mapper.receiveUncheckedList("id3").forEach(log::info);
	}
}
