package cn.itcast.haoke.dubbo.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.experimental.Accessors;

/**
 * <p>
 * 房源表
 * </p>
 *
 * @author itcast
 */

@Accessors(chain = true)
@TableName("tb_house_resources")
public class HouseResources extends BasePojo implements java.io.Serializable{

    private static final long serialVersionUID = 779152022777511825L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Override
    public String toString() {
        return "HouseResources{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", estate_id=" + estate_id +
                ", buildingNum='" + buildingNum + '\'' +
                ", buildingUnit='" + buildingUnit + '\'' +
                ", buildingFloorNum='" + buildingFloorNum + '\'' +
                ", rent=" + rent +
                ", rentMethod=" + rentMethod +
                ", paymentMethod=" + paymentMethod +
                ", houseType='" + houseType + '\'' +
                ", coveredArea='" + coveredArea + '\'' +
                ", useArea='" + useArea + '\'' +
                ", floor='" + floor + '\'' +
                ", orientation='" + orientation + '\'' +
                ", decoration=" + decoration +
                ", facilities='" + facilities + '\'' +
                ", pic='" + pic + '\'' +
                ", houseDesc='" + houseDesc + '\'' +
                ", contact='" + contact + '\'' +
                ", mobile='" + mobile + '\'' +
                ", time=" + time +
                ", propertyCost='" + propertyCost + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getEstate_id() {
        return estate_id;
    }

    public void setEstate_id(Long estate_id) {
        this.estate_id = estate_id;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    public String getBuildingUnit() {
        return buildingUnit;
    }

    public void setBuildingUnit(String buildingUnit) {
        this.buildingUnit = buildingUnit;
    }

    public String getBuildingFloorNum() {
        return buildingFloorNum;
    }

    public void setBuildingFloorNum(String buildingFloorNum) {
        this.buildingFloorNum = buildingFloorNum;
    }

    public Integer getRent() {
        return rent;
    }

    public void setRent(Integer rent) {
        this.rent = rent;
    }

    public Integer getRentMethod() {
        return rentMethod;
    }

    public void setRentMethod(Integer rentMethod) {
        this.rentMethod = rentMethod;
    }

    public Integer getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(Integer paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getCoveredArea() {
        return coveredArea;
    }

    public void setCoveredArea(String coveredArea) {
        this.coveredArea = coveredArea;
    }

    public String getUseArea() {
        return useArea;
    }

    public void setUseArea(String useArea) {
        this.useArea = useArea;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public Integer getDecoration() {
        return decoration;
    }

    public void setDecoration(Integer decoration) {
        this.decoration = decoration;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getHouseDesc() {
        return houseDesc;
    }

    public void setHouseDesc(String houseDesc) {
        this.houseDesc = houseDesc;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getPropertyCost() {
        return propertyCost;
    }

    public void setPropertyCost(String propertyCost) {
        this.propertyCost = propertyCost;
    }

    /**
     * 房源标题
     */
    private String title;

    /**
     * 楼盘id
     */
    private Long estate_id;

    /**
     * 楼号（栋）
     */
    private String buildingNum;

    /**
     * 单元号
     */
    private String buildingUnit;

    /**
     * 门牌号
     */
    private String buildingFloorNum;

    /**
     * 租金
     */
    private Integer rent;

    /**
     * 租赁方式，1-整租，2-合租
     */
    private Integer rentMethod;

    /**
     * 支付方式，1-付一押一，2-付三押一，3-付六押一，4-年付押一，5-其它
     */
    private Integer paymentMethod;

    /**
     * 户型，如：2室1厅1卫
     */
    private String houseType;

    /**
     * 建筑面积
     */
    private String coveredArea;

    /**
     * 使用面积
     */
    private String useArea;

    /**
     * 楼层，如：8/26
     */
    private String floor;

    /**
     * 朝向：东、南、西、北
     */
    private String orientation;

    /**
     * 装修，1-精装，2-简装，3-毛坯
     */
    private Integer decoration;

    /**
     * 配套设施， 如：1,2,3
     */
    private String facilities;

    /**
     * 图片，最多5张
     */
    private String pic;

    /**
     * 描述
     */
    private String houseDesc;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 看房时间，1-上午，2-中午，3-下午，4-晚上，5-全天
     */
    private Integer time;

    /**
     * 物业费
     */
    private String propertyCost;


}
