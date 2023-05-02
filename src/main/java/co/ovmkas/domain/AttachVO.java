package co.ovmkas.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true) //조상 클래스의 toString까지 같이 나온다
@Alias("attach")
public class AttachVO  extends AttachFileDTO{
	private Long bno;
}
