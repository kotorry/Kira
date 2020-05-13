package xyz.hydrion.care.domain.form;

import javax.validation.constraints.*;

public class DevForm {


    Integer devId;
    String wearerName;

    String provinceName;
    String cityName;
    String districtName;
    @NotEmpty(message = "具体地址不能为空")
    String extraName;

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public String getWearerName() {
        return wearerName;
    }

    public void setWearerName(String wearerName) {
        this.wearerName = wearerName;
    }

    public String getProvinceName(){
        return provinceName;
    }
    public void setProvinceName(String provinceName) {this.provinceName = provinceName;}

    public String getCityName(){
        return cityName;
    }
    public void setCityName(String cityName) {this.cityName = cityName;}

    public String getDistrictName(){
        return districtName;
    }
    public void setDistrictName(String districtName) {this.districtName = districtName;}

    public String getExtraName(){
        return extraName;
    }
    private void setExtraName(String extraName) {this.extraName = extraName;}

}
