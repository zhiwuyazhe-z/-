package cn.itcast.haoke.dubbo.api.graphql;

import cn.itcast.haoke.dubbo.api.vo.PageInfo;
import cn.itcast.haoke.dubbo.api.vo.Pagination;
import cn.itcast.haoke.dubbo.api.vo.TableResult;
import cn.itcast.haoke.dubbo.server.pojo.HouseResources;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HouseResourcesListDataFetcher implements MyDataFetcher{
    @Autowired
    private cn.itcast.haoke.dubbo.api.mapper.housemapper housemapper;
    @Override
    public String fieldName() {
        return "HouseResourcesList";
    }

    @Override
    public Object dataFetcher(DataFetchingEnvironment environment) {
        Long id=environment.getArgument("id");
        Integer pagesize=environment.getArgument("pageSize");

        if(null==pagesize)pagesize=5;
        Integer page=environment.getArgument("page");
        if(null==page)page=1;
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.orderByDesc("updated");
        System.out.println(pagesize);
        System.out.println(id);
        IPage iPage=housemapper.selectPage(new Page<>(page,pagesize),queryWrapper);
        PageInfo<HouseResources> pageInfo=new PageInfo<HouseResources>(Long.valueOf(iPage.getTotal()).intValue(), page, pagesize, iPage.getRecords());
        return new TableResult<>(pageInfo.getRecords(), new Pagination(page, pagesize, pageInfo.getTotal()));
    }
}
