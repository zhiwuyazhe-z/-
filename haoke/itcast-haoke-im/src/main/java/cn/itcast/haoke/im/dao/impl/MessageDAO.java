package cn.itcast.haoke.im.dao.impl;

import cn.itcast.haoke.im.dao.imsg;
import cn.itcast.haoke.im.pojo.Message;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class MessageDAO implements imsg {
    @Autowired
    private MongoTemplate mongoTemplate;
    public List<Message> findbyfromto(Long from, Long to, Integer page, Integer row){
        Criteria fromlist=Criteria.where("from.id").is(from).and("to.id").is(to);
        Criteria tolist=Criteria.where("from.id").is(to).and("to.id").is(from);
        Criteria criteria=new Criteria().orOperator(fromlist,tolist);
        PageRequest pageRequest=PageRequest.of(page-1,row,Sort.by(Sort.Direction.ASC,"send_date"));
        Query q=new Query(criteria).with(pageRequest);
        return this.mongoTemplate.find(q,Message.class);
    }
    public Message findmsgbyid(String id){
        return this.mongoTemplate.findById(new ObjectId(id),Message.class);
    }
    public UpdateResult updateMessagestate(ObjectId id,Integer status){
        Query query=Query.query(Criteria.where("id").is(id));
        Update update=Update.update("status",status);
        if(status.intValue()==1){
            update.set("sent_date",new Date());
        }
        else{
            update.set("read_date",new Date());
        }
        return this.mongoTemplate.updateFirst(query,update,Message.class);
    }
    public Message save(Message message){
        message.setId(ObjectId.get());
        message.setSendDate(new Date());
        message.setStatus(1);
        System.out.println(mongoTemplate);
        return this.mongoTemplate.save(message);
    }
    public DeleteResult deleteResult(String id){
        Query query=Query.query(Criteria.where("id").is(id));
        return this.mongoTemplate.remove(query,Message.class);
    }
}
