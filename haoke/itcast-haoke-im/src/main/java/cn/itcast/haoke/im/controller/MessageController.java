package cn.itcast.haoke.im.controller;

import cn.itcast.haoke.im.pojo.Message;
import cn.itcast.haoke.im.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("message")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @GetMapping
    public List<Message> querylist(@RequestParam("fromId") Long fromId,
                                   @RequestParam("toId") Long toId,
                                   @RequestParam(value = "page",
                                          defaultValue = "1") Integer page,
                                   @RequestParam(value = "rows",
                                          defaultValue = "100") Integer rows){
        System.out.println(fromId);
        System.out.println("==============");
        return this.messageService.queryList(fromId, toId, page, rows);
    }
}
