package co.ovmkas.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ovmkas.domain.SampleVO;
import lombok.extern.log4j.Log4j;

/**
 * @author DG
 * controller ?
 * 요청과 응답처리 제어
 * request : 1. URL
 */
@RestController
@RequestMapping("sample")
@Log4j
public class SampleController {
	@GetMapping(value="getText", produces="text/plain; charset=utf-8")
	public String getText(){
		return "안녕하세요";
	}
	
	@GetMapping("getSample")
	public SampleVO getSample(){
		return new SampleVO(112, "스타"," 로드");
	}
	@GetMapping("getList")
	public List<SampleVO> getList(){
		return IntStream.rangeClosed(1, 10).mapToObj(i->new SampleVO(i, "first " + i, "last " + i)).collect(Collectors.toList());
		// intstream은 무한으로 하게 되는데 범위를 지정(1,10)해주면 하나를 입력받으면 하나를 반환한다 map To OBJECT로 반환 하고 다시 collect로 list형태로 변환
		// int값으로 입력값이 고정된다.
	}
	
	@GetMapping("getMap")
	public Map<String, SampleVO> getMap(){
		Map<String, SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(111,"그루트", "주니어"));
		return map;
	}
	
	//비동기로 처리 할때 알아 볼 것
	@GetMapping(value="check", params={"height","weight"})
	public ResponseEntity<SampleVO> check(double height, double weight){
		SampleVO vo = new SampleVO(0, String.valueOf(height), String.valueOf(weight));
		ResponseEntity<SampleVO> result = null;
		
		if(height <150 ){
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		}
		else{
//			result = ResponseEntity.status(HttpStatus.OK).body(vo);아래와 같음
			result = ResponseEntity.ok(vo);
		}
		return result;
	}
	@GetMapping("product/{cat}/{pid}")
	public String[] getPath (@PathVariable String cat, @PathVariable String pid){
		return new String[] {"category : " +  cat, "productid : " + pid};
	}
	
	@GetMapping("product/{cat2}/{pid2}/{test}")//넘겨주는 PathVariable이 같으면 안된다(파라미터 갯수가 다르면 상관 없음)
	public String[] getPath2 (@PathVariable String cat2, @PathVariable String pid2){
		return new String[] {"category : " +  cat2, "productid : " + pid2};
	}
	
	//
	@GetMapping("sample")
	public SampleVO convert(@RequestBody SampleVO sampleVO){
		log.warn(sampleVO);
		return sampleVO;
	}
	
	@PostMapping("sample1")
	public SampleVO convert2(@RequestBody SampleVO sampleVO){
		log.warn(sampleVO);
		return sampleVO;
	}
}
