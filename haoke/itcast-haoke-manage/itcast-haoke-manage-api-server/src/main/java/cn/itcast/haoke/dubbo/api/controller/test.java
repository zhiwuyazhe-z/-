package cn.itcast.haoke.dubbo.api.controller;

import cn.itcast.haoke.dubbo.api.mapper.housemapper;
import cn.itcast.haoke.dubbo.server.pojo.HouseResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("house/test")
public class test {
    @Autowired
    public cn.itcast.haoke.dubbo.api.mapper.housemapper housemapper;
    @GetMapping
    public void t(@RequestParam(name = "id")Long id){
        System.out.println(id);
        HouseResources houseResources=housemapper.selectById(id);
        System.out.println(houseResources);
    }
}
