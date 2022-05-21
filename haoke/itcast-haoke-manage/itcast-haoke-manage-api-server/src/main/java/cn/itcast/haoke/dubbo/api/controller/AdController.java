package cn.itcast.haoke.dubbo.api.controller;

import cn.itcast.haoke.dubbo.api.vo.PageInfo;
import cn.itcast.haoke.dubbo.api.vo.WebResult;
import cn.itcast.haoke.dubbo.server.pojo.Ad;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("ad")
@RestController
@CrossOrigin
public class AdController {
    @Autowired
    private cn.itcast.haoke.dubbo.api.mapper.admapper admapper;
    @GetMapping
    public WebResult queryIndexAd(){
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.orderByDesc("updated");
        queryWrapper.eq("type",1);
        IPage<Ad> ipage=admapper.selectPage(new Page<Ad>(1,3),queryWrapper);
        PageInfo<Ad> adPageInfo = new PageInfo<>(Long.valueOf(ipage.getTotal()).intValue(), 1,
                3, ipage.getRecords());
        List<Ad> records = adPageInfo.getRecords();

        List<Object> result = new ArrayList<>();
        for (Ad ad : records) {
            Map<String,Object> map = new HashMap<>();
            map.put("original", ad.getUrl());
            result.add(map);
        }

        return WebResult.ok(result);
}

}
