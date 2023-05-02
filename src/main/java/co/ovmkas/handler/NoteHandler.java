package co.ovmkas.handler;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import co.ovmkas.domain.MemberVO;
import co.ovmkas.domain.NoteVO;
import co.ovmkas.service.NoteService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


//ServerSocket의 역할을 함
//접속자들을 관리해야됨

@Log4j
@EnableWebSocket
//@Component
public class NoteHandler extends TextWebSocketHandler{
	//life cycle
	//접속, 실제할일, 종류, 에러 시 할일
	
	//접속자 관리 객체
	private List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
	
	// A B C
	@Autowired
	private NoteService noteService;
	
	//session 접속자의 정보를 가지고 있음
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
		
		log.warn(sessions.stream().map(s->getIdBySession(s)).collect(Collectors.toList()));
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		String msg = message.getPayload();//실제 메세지
//		Gson gson = new Gson();
//		NoteVO vo= gson.fromJson(msg, NoteVO.class);
//		MemberVO memberVO = (MemberVO)session.getAttributes().get("member");
//		log.warn(msg);
//		log.warn(vo);
//		log.warn(memberVO);
//		for(WebSocketSession s : sessions){
//			s.sendMessage(new TextMessage(session.getId() +" :: " + msg));
//		}
		
		/*0413*/
		//message.getPayload();//js, ws.send() 수신자
		String receiver = message.getPayload();
		String sender = getIdBySession(session);
		List<NoteVO> list0 = noteService.getSendList(sender);
		List<NoteVO> list1 = noteService.getReceiveList(receiver);
		List<NoteVO> list2 = noteService.getReceiveUncheckedList(receiver);
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("sendList", list0);
		map.put("receiveList", list1);
		map.put("receiveUncheckedList", list2);
		map.put("sender", sender);
		Gson gson = new Gson();
		
		log.warn(map);
		for(WebSocketSession s : sessions){
			if(receiver.equals(getIdBySession(s)) || session == s){
				//발송
				s.sendMessage(new TextMessage(gson.toJson(map)));
			}
		}
		// A > B
		
	}

	private String getIdBySession(WebSocketSession session){
		Object obj = session.getAttributes().get("member");
		String id = null;
		if(obj != null && obj instanceof MemberVO){
			id=((MemberVO)obj).getUserid();
		}
		return id;
	}
	
}
