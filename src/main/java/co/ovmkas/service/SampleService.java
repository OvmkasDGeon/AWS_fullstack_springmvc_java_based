package co.ovmkas.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.ovmkas.mapper.SampleMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
//@Transactional	//트랜잭션 각각 메소드 마다 붙여도 되고 많이 사용 되게 되면 클래스 자체에 붙여도 무방하다
@Transactional
public class SampleService {
	private SampleMapper mapper;
	
	
	public void addData(String data){
		log.info("insert1()");
		mapper.insert1(data);
		log.info("insert2()");
		mapper.insert2(data);
		log.info("end");
	}
	

	public void addData2(String data){
		log.info("insert1()");
		mapper.insert1(data);
		log.info("insert2()");
		mapper.insert2(data);
		log.info("insert2()");
		mapper.insert2(data);
		log.info("insert2()");
		mapper.insert2(data);
		log.info("end");
	}
}
