package cn.itcast.haoke.im.websocket;

import cn.itcast.haoke.im.dao.impl.MessageDAO;
import cn.itcast.haoke.im.pojo.Message;
import cn.itcast.haoke.im.pojo.UserData;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;
@Component
public class MessageHandler extends TextWebSocketHandler {
    @Autowired
    private MessageDAO messageDAO;
    private static final ObjectMapper mapper=new ObjectMapper();
    private static final Map<Long, WebSocketSession> sessions=new HashMap<>();
    public void afterConnectionEstablished(WebSocketSession session)throws Exception{
        Long uid=(Long) session.getAttributes().get("uid");
        sessions.put(uid,session);
    }
    protected  void handleTextMessage(WebSocketSession session, TextMessage textMessage)throws Exception{
        Long uid=(Long)session.getAttributes().get("uid");
        JsonNode jsonNode=mapper.readTree(textMessage.getPayload());
        Long to=jsonNode.get("toId").asLong();
        String msg=jsonNode.get("msg").asText();
        Message message=Message.builder().from(UserData.USER_MAP.get(uid)).to(UserData.USER_MAP.get(to)).msg(msg).build();
        message=this.messageDAO.save(message);
        WebSocketSession tosession=sessions.get(to);
        if(tosession!=null&&tosession.isOpen()){
            tosession.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
            this.messageDAO.updateMessagestate(message.getId(),2);
        }
    }
}
