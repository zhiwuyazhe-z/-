package cn.itcast.haoke.dubbo.api.controller;
import cn.itcast.haoke.dubbo.api.mapper.housemapper;
import cn.itcast.haoke.dubbo.api.vo.PageInfo;
import cn.itcast.haoke.dubbo.api.vo.Pagination;
import cn.itcast.haoke.dubbo.api.vo.TableResult;
import cn.itcast.haoke.dubbo.server.pojo.BasePojo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.itcast.haoke.dubbo.api.service.HouseResourcesService;
import cn.itcast.haoke.dubbo.server.pojo.HouseResources;
import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.coyote.Response;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Controller
@RequestMapping("house/resources")
public class HouseResourcesController {
    @Autowired
    private cn.itcast.haoke.dubbo.api.mapper.housemapper housemapper;
/*    InputStream in;

    {
        try {
            in = org.apache.ibatis.io.Resources.getResourceAsStream("sqlconfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
    SqlSession session=factory.openSession();
    cn.itcast.haoke.dubbo.api.abc.iuserdao iuserdao=session.getMapper(cn.itcast.haoke.dubbo.api.abc.iuserdao.class);*/
    @PostMapping
    @ResponseBody
    public ResponseEntity<Void> save(@RequestBody HouseResources houseResources){
        System.out.println("==========================");
        System.out.println(houseResources.toString());
        housemapper.insert(houseResources);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<TableResult> list(HouseResources houseResources, @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        QueryWrapper queryWrapper = new QueryWrapper();
        // 根据数据的更新时间做倒序排序
        queryWrapper.orderByDesc("updated");
        System.out.println(housemapper);

        IPage iPage =housemapper.selectPage(new Page<>(currentPage, pageSize), queryWrapper);
        PageInfo<HouseResources> pageInfo = new PageInfo<HouseResources>(Long.valueOf(iPage.getTotal()).intValue(), currentPage, pageSize, iPage.getRecords());
        TableResult<HouseResources> tableResult=new TableResult<>(pageInfo.getRecords(), new Pagination(currentPage, pageSize, pageInfo.getTotal()));
        return ResponseEntity.ok(tableResult);

    }
    @PutMapping
    @ResponseBody
    public ResponseEntity<Void> update(@RequestBody HouseResources houseResources) {
        if(this.housemapper.updateById(houseResources)==1){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}


