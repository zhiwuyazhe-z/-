package cn.itcast.haoke.dubbo.api.service;

import cn.itcast.haoke.dubbo.api.vo.Pagination;
import cn.itcast.haoke.dubbo.api.vo.TableResult;

import cn.itcast.haoke.dubbo.server.pojo.HouseResources;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*@Service
@org.apache.dubbo.config.annotation.Service*/
public class HouseResourcesService {


   /* private ApiHouseResourcesService apiHouseResourcesService;

    public boolean save(HouseResources houseResources) {
        System.out.println(apiHouseResourcesService);
        int result = this.apiHouseResourcesService.saveHouseResources(houseResources);
        return result == 1;
    }*/
//    public TableResult<HouseResources> queryList(HouseResources houseResources, Integer currentPage, Integer pageSize) {
//        PageInfo<HouseResources> pageInfo = this.apiHouseResourcesService.queryHouseResourcesList(currentPage, pageSize, houseResources);
//        return new TableResult<>(pageInfo.getRecords(), new Pagination(currentPage, pageSize, pageInfo.getTotal()));
//    }
}