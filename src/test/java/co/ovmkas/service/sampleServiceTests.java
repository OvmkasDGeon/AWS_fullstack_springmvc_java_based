package co.ovmkas.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class sampleServiceTests {
	@Autowired
	private SampleService service;
	
	@Test
	public void testExist(){
		assertNotNull(service);
	}
	
	@Test
	public void testAddData() throws Exception{
		String data = " 키보드를 더 많이 쳐야 하기 때문이다. (농담이 아니다! 그나마 키보드면 차라리 낫다. 천공카드를 썼을 때는 대략...) 예를 들어, 사칙연산을 수행할 때도 보통 프로그래밍 언어는 그냥 z=x+y;(BASIC에서는 세미콜론을 안 붙임[1]) 이런 식으로 쓰지만 코볼에서는 아래와 같이 써야 한다";
		byte[] bs = data.getBytes("utf-8");
		log.info(bs.length);
		byte[] bs2 = new byte[50];
		System.arraycopy(bs, 0, bs, 0, 50);
		log.info(bs2.length);
		String str = new String(bs2, "utf-8");
		log.info(str);
//		data= "gdgdgdgd";
//		log.info(data.length());
//		service.addData(data);
	}
	
}
