package co.ovmkas.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.ovmkas.domain.MemberVO;
import co.ovmkas.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("member")
@AllArgsConstructor
public class MemberController {
	private MemberService memberService;
	
	
	@GetMapping("login")
	public void login() {
		log.info("login123123");
	}
	/*
	@GetMapping("join")
	public void join (){}
	
	
	@GetMapping("chkId/{id}") @ResponseBody
	public MemberVO chkId(@PathVariable String id){
		return memberService.get(id);
	}
	
	
	@GetMapping("chat")
	public void chat(){
		
	}
	
	@GetMapping("login")
	public void login() {
		log.info("login123123");
	}
	
	@GetMapping("result")
	public void result() {}
	
	@PostMapping("login")
	public String login(MemberVO vo, HttpSession session, RedirectAttributes rttr){
		log.info(vo);
		MemberVO memberVO = memberService.get(vo);
		if(memberVO == null){
			rttr.addFlashAttribute("msg","로그인 실패");
		}else{
			session.setAttribute("member", memberVO);
		}
		return "redirect:/member/login";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session){
		log.info("로그아웃 처리");
		session.invalidate();
		return "redirect:/member/login";
	}
	
	@GetMapping("getList")
	@ResponseBody
	public List<MemberVO> getList(){
		return memberService.getList();
	}
	*/
}
