package cn.itcast.haoke.im.service;

import cn.itcast.haoke.im.dao.impl.MessageDAO;
import cn.itcast.haoke.im.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageDAO messageDAO;
    public List<Message>queryList(Long from, Long to, Integer page, Integer rows){
       List<Message>list= this.messageDAO.findbyfromto(from,to,page,rows);
        for(Message message:list){
            if(message.getStatus().intValue()==1)this.messageDAO.updateMessagestate(message.getId(),2);
        }
        return list;
    }
}
