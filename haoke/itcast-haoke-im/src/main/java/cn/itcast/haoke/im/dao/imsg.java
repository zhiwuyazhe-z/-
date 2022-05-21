package cn.itcast.haoke.im.dao;

import cn.itcast.haoke.im.pojo.Message;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;

import java.util.List;

public interface imsg {
    public List<Message> findbyfromto(Long from, Long to, Integer page, Integer row);
    public Message findmsgbyid(String id);
    public UpdateResult updateMessagestate(ObjectId id, Integer status);
    public Message save(Message message);
    public DeleteResult deleteResult(String id);
}
