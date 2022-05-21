package cn.itcast.haoke.im.dao;

import cn.itcast.haoke.im.dao.impl.MessageDAO;
import cn.itcast.haoke.im.pojo.Message;
import cn.itcast.haoke.im.pojo.User;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.AccessType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class test {
@Autowired
    private MessageDAO messageDAO;
    @Test
    public void testsave(){
        Message message=Message.builder().id(ObjectId.get()).msg("hello").sendDate(new Date()).status(1).from(new User(1001l,"zhang")).to(new User(1002l,"li")).build();
        this.messageDAO.save(message);
    }
}
