package co.ovmkas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ovmkas.domain.ReplyVO;
import co.ovmkas.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("replies")
@Log4j
@AllArgsConstructor
public class ReplyController {
	private ReplyService replyService;

	
	
	//replies/list/{bno}
	//replies/list/{bno}/{rno}
	
	@GetMapping({"list/{bno}","list/{bno}/{rno}"})//@PathVariable(required=false) 기본값이 true
	public List<ReplyVO> getList(@PathVariable Long bno, @PathVariable(required=false) Optional<Long> rno){
		log.info(bno);
//		log.info(rno.isPresent() ? rno.get() : 0L);//null여부(boolean형태)
//		log.info(rno.orElse(0L));//null일때 값을 변경해줌
//		if(rno == null){
//			rno = 0L;
//		}
//		return replyService.getList(bno, rno.orElse(0L)); //단순조건일떈 이렇게 써도 무방
		return replyService.getList(bno, rno.orElseGet(()->0L));//복합조건 일땐 else get으로 람다식을 이용해서 써야한다
	}
	
	@PostMapping("new")
	@PreAuthorize("isAuthenticated()")
	public Long create(@RequestBody ReplyVO vo){
		log.info(vo);
		replyService.register(vo);
		return vo.getRno();
	}
	
	//비동기이면 {bno}값이 들어간다
	//아이디를 비동기 처리 하려면 {id}값이 들어가는데 암호화 처리를 하려면 인코딩을 해야하는데 bcy암호화를 해서 가져와서 비교한다
	@GetMapping("{rno}")
	public ReplyVO get(@PathVariable Long rno){
		log.info(rno);
		return replyService.get(rno);
	}
	
	@DeleteMapping("{rno}")
	@PreAuthorize("isAuthenticated() and principal.username eq #vo.replyer")
	public int remove(@PathVariable Long rno,  @RequestBody ReplyVO vo){
		log.info(rno);
		return replyService.remove(rno);
	}
	
	@PutMapping("{rno}")
	@PreAuthorize("isAuthenticated() and principal.username eq #vo.replyer")
	public int modify(@PathVariable Long rno, @RequestBody ReplyVO vo){
		log.info(rno);
		log.info(vo);
		return replyService.modify(vo);
	}
}
