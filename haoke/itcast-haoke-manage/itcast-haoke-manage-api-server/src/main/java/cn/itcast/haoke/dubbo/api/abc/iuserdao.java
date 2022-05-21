package cn.itcast.haoke.dubbo.api.abc;

import cn.itcast.haoke.dubbo.server.pojo.HouseResources;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface iuserdao {
    @Select("select * from tb_house_resources")
    List<HouseResources> findall();
    @Insert("insert into tb_house_resources(id,title,estate_id,building_num,building_unit,building_floor_num,rent,rent_method,payment_method,house_type,covered_area,use_area,floor,orientation,decoration,facilities,pic,house_desc,contact,mobile,time,property_cost,created,updated)values(#{id},#{title},#{estate_id},#{buildingNum},#{buildingUnit},#{buildingFloorNum},#{rent},#{rentMethod},#{paymentMethod},#{houseType},#{coveredArea},#{useArea},#{floor},#{orientation},#{decoration},#{facilities},#{pic},#{houseDesc},#{contact},#{mobile},#{time},#{propertyCost},#{created},#{updated})")
    void save(HouseResources houseResources);

}
