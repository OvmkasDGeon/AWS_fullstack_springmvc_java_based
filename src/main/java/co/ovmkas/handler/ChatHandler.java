package co.ovmkas.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;

import lombok.extern.log4j.Log4j;


//ServerSocket의 역할을 함
//접속자들을 관리해야됨

@Log4j
@EnableWebSocket
@Component
public class ChatHandler extends TextWebSocketHandler{
	//life cycle
	//접속, 실제할일, 종류, 에러 시 할일
	
	//접속자 관리 객체
	List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
	
	//session 접속자의 정보를 가지고 있음
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
		log.warn("현재 접속자 수:: " + sessions.size());
		log.warn("현재 접속자 정보 ");
		sessions.forEach(log::warn);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.warn(session.getId()+"로그아웃");
		sessions.remove(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();//실제 메세지
		Gson gson = new Gson();
		Map<?, ?> map = gson.fromJson(msg, Map.class);
		log.warn(map.get("msg"));
		log.warn(map.get("id"));
		log.warn(msg);
		log.warn(session.getAttributes().get("member"));
		for(WebSocketSession s : sessions){
			s.sendMessage(new TextMessage(session.getId() +" :: " + msg));
		}
	}

	
	
}
