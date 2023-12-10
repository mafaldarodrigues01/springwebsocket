package web.controller;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import web.model.Action;
import web.model.Message;
import web.model.UserWeb;
import web.service.MemberStore;

@Controller
public class ChatController {

	private final MemberStore memberStore;
	private final SimpMessagingTemplate simpMessagingTemplate;
	
	public ChatController(MemberStore memberStore, SimpMessagingTemplate simpMessagingTemplate) {
		this.memberStore = memberStore;
		this.simpMessagingTemplate = simpMessagingTemplate;
	}
	
	@MessageMapping("/userWeb")
	public void getusers(UserWeb userWeb, SimpMessageHeaderAccessor headerAccessor) throws Exception {
		UserWeb newUserWeb = new UserWeb(userWeb.id(), null, userWeb.username());
		headerAccessor.getSessionAttributes().put("userWeb", newUserWeb);
		memberStore.addMember(newUserWeb);
		sendMembersList();
		Message newMessage = new Message(new UserWeb(null, null, userWeb.username()), null, null, Action.JOINED, Instant.now());
        simpMessagingTemplate.convertAndSend("/topic/messages", newMessage);

	}
		
	@EventListener
	public void handleSessionConnectEvent(SessionConnectEvent event) {
		System.out.println("Session Connect Event");
	}
	
	@EventListener
	public void handleSessionDisconnectEvent(SessionDisconnectEvent event) {
		System.out.println("Session Disconnect Event");
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
        if (sessionAttributes == null) {
            return;
        }
        UserWeb userWeb = (UserWeb) sessionAttributes.get("userWeb");
        if (userWeb == null) {
            return;
        }	
        memberStore.removeMember(userWeb);
        sendMembersList();
        
        Message message = new Message(new UserWeb(null, null, userWeb.username()), null, "", Action.LEFT, Instant.now());
        simpMessagingTemplate.convertAndSend("/topic/messages", message);
 
	}
	
	@MessageMapping("/message")
	public void getMessage(Message message) throws Exception {
		Message newMessage = new Message(new UserWeb(null, message.userWeb().serialId(), message.userWeb().username()), message.receiverId(), message.comment(), message.action(), Instant.now());
        simpMessagingTemplate.convertAndSend("/topic/messages", newMessage);
	}
	
	@MessageMapping("/privatemessage")
	public void getPrivateMessage(Message message) throws Exception {
		Message newMessage = new Message(
				new UserWeb(null, message.userWeb().serialId(), message.userWeb().username()), message.receiverId(), message.comment(), message.action(), Instant.now());
        simpMessagingTemplate.convertAndSendToUser(memberStore.getMember(message.receiverId()).id(), "/topic/privatemessages", newMessage);

	}

	private void sendMembersList() {
		List<UserWeb> memberList = memberStore.getMembersList();
		memberList.forEach(
				sendUserWeb ->
						simpMessagingTemplate.convertAndSendToUser(
								sendUserWeb.id(),
								"/topic/users",
								memberStore.filterMemberListByUser(memberList, sendUserWeb)));
	}
}
