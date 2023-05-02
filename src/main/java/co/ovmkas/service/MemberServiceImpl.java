package co.ovmkas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.ovmkas.domain.MemberVO;
import co.ovmkas.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{
	/*
	private MemberMapper mapper;
	
	@Override
	public List<MemberVO> getList() {
		return mapper.getList();
	}

	@Override
	public MemberVO get(String id) {
		return mapper.read(id);
	}

	@Override
	public MemberVO get(MemberVO vo) {
		return mapper.login(vo);
	}
	*/
}
