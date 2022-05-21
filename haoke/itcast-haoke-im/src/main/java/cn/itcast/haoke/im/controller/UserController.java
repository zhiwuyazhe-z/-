package cn.itcast.haoke.im.controller;

import cn.itcast.haoke.im.pojo.Message;
import cn.itcast.haoke.im.pojo.User;
import cn.itcast.haoke.im.pojo.UserData;
import cn.itcast.haoke.im.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("user")
@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private MessageService messageService;
    @GetMapping
    public List<Map<String,Object>> queryUserList(@RequestParam("fromId") Long from){
        List<Map<String,Object>>res=new ArrayList<>();
        for(Map.Entry<Long, User>userEntry: UserData.USER_MAP.entrySet()){
            Map<String,Object>temp=new HashMap<>();
            temp.put("id",userEntry.getValue().getId());
            temp.put("avatar","http://");
            temp.put("from_user",from);
            temp.put("info_type",null);
            temp.put("username",userEntry.getValue().getUsername());
            List<Message>messages=this.messageService.queryList(from,userEntry.getValue().getId(),1,1);
            if(messages!=null&&!messages.isEmpty()){
                Message message=messages.get(0);
                temp.put("chat_msg",message.getMsg());
                temp.put("chat_time",message.getSendDate().getTime());
            }
            res.add(temp);
        }
        return res;
    }
}
