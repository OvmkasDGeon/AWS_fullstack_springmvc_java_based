package co.ovmkas.controller;


import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.ovmkas.domain.AttachVO;
import co.ovmkas.domain.BoardVO;
import co.ovmkas.domain.Criteria;
import co.ovmkas.domain.PageDto;
import co.ovmkas.domain.SampleVO;
import co.ovmkas.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("board/*")
@Data
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("list")
	public void list(Model model, Criteria cri){
		log.info("list()");
		model.addAttribute("list",boardService.getList(cri));
		model.addAttribute("page", new PageDto(boardService.getTotalCnt(cri), cri));
	}
	@GetMapping("register")
	@PreAuthorize("isAuthenticated()")//이 페이지를 오려면 로그인 상태여야 한다 servletConfig.java에서 클래스레벨에 설정해줘야한다 
	public void register(){
		
	}
	
	@PostMapping("register")
	@PreAuthorize("isAuthenticated()")
	public String register(BoardVO vo, RedirectAttributes rttr, Criteria cri){
		log.info("register");
		log.info(vo);
		boardService.register(vo);
		rttr.addFlashAttribute("result", vo.getBno());
		rttr.addAttribute("pageNum", 1);
		rttr.addAttribute("amount",cri.getAmount());
		return"redirect:/board/list";
	}
	@PostMapping("modify")
	@PreAuthorize("isAuthenticated() and principal.username eq #vo.writer")//밑에 클래스 파라미터에 있는 vo이름을 따라간다
	public String modify(BoardVO vo, RedirectAttributes rttr, Criteria cri){
		//게시글 수정
		//원본 리스트
		List<AttachVO> originList = boardService.get(vo.getBno()).getAttachs();
		
		//수정될 리스트
		List<AttachVO> list = vo.getAttachs();
		
		log.info("modify");
		log.info(vo);
		log.info(cri);
		if(boardService.modify(vo)){
			rttr.addFlashAttribute("result", "success");
			/*originList.stream().filter(v->{
				boolean flag = false;
				for(AttachVO vo2 :list){
					flag = vo2.getUuid().equals(v.getUuid());
				}
				return !flag;
				}).forEach(boardService::deleteFile);*/
		}
		return"redirect:/board/list" +cri.getFullQueryString();
	}
	@PostMapping("remove")
	@PreAuthorize("isAuthenticated() and principal.username eq #writer")
	public String remove(String writer, Long bno, RedirectAttributes rttr , Criteria cri){
		log.info("remove");
		log.info(bno);
		log.info(cri);
		List<AttachVO> list = boardService.get(bno).getAttachs();
		if(boardService.remove(bno)){
			for(AttachVO vo : list){
				boardService.deleteFile(vo);
			}
			rttr.addFlashAttribute("result", "success");
		}
		return"redirect:/board/list"+cri.getFullQueryString();
	}
	
	@GetMapping({"get","modify"})
	public void get(Model model, Long bno, @ModelAttribute("cri")Criteria cri){
		log.info("get() or modify()");
		log.info(bno);
		model.addAttribute("cri", cri);
		model.addAttribute("board",boardService.get(bno));
	}
	@GetMapping("{bno}")
	public String getByPath(Model model, @PathVariable Long bno){
		log.info("get() or modify()");
		log.info(bno);
		model.addAttribute("board",boardService.get(bno));
		return "board/get";
	}
	
	@GetMapping("getSample")
	@ResponseBody// 비동기처리 하기 위한 작업(응답에 대한 처리)
	public SampleVO getSample(){
		return new SampleVO(112, "스타"," 로드");
	}
}
