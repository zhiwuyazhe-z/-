package cn.itcast.haoke.dubbo.api.graphql;

import cn.itcast.haoke.dubbo.api.mapper.housemapper;
import cn.itcast.haoke.dubbo.server.pojo.HouseResources;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HouseResourcesDataFetcher implements MyDataFetcher{
    @Autowired
    private cn.itcast.haoke.dubbo.api.mapper.housemapper housemapper;
    @Override
    public String fieldName() {
        return "HouseResources";
    }

    @Override
    public Object dataFetcher(DataFetchingEnvironment environment) {
        Long id=environment.getArgument("id");
        return housemapper.selectById(id);
    }
}
