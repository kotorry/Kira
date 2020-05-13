package xyz.hydrion.care.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import xyz.hydrion.care.domain.form.DevForm;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.List;

public class ElderDev {
    Integer id;
    @JsonProperty("wearer_name")
    String wearerName;
    @JsonProperty("create_time")
    Timestamp createTime;
    @JsonProperty("province_name")
    String provinceName;
    @JsonProperty("city_name")
    String cityName;
    @JsonProperty("district_name")
    String districtName;
    @JsonProperty("extra_name")
    String extraName;



    public ElderDev(){

    }

    public ElderDev(DevForm form) {
        this.id = form.getDevId();
        this.provinceName = form.getProvinceName();
        this.cityName = form.getCityName();
        this.districtName = form.getDistrictName();
        this.extraName = form.getExtraName();
        this.wearerName = form.getWearerName();
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public  String getProvinceName() { return provinceName;}
    public  void setProvinceName(String provinceName) { this.provinceName = provinceName;}

    public String getCityName() { return cityName;}
    public void setCityName(String cityName) { this.cityName = cityName;}

    public String getDistrictName() { return districtName;}
    public void setDistrictName(String districtName) { this.districtName = districtName;}

    public String getExtraName() { return extraName;}
    public void setExtraName(String extraName) { this.extraName = extraName;}


    public String getWearerName() {
        return wearerName;
    }

    public void setWearerName(String wearerName) {
        this.wearerName = wearerName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
