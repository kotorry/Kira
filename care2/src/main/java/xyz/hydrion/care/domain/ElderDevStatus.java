package xyz.hydrion.care.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class ElderDevStatus {
    Integer id;
    @JsonProperty("dev_id")
    Integer devId;
    @JsonProperty("update_time")
    Timestamp updateTime;
    @JsonProperty("dev_status")
    Integer devStatus;
    @JsonProperty("wearer_status")
    Integer wearerStatus;
    @JsonProperty("heart_rate")
    Float heartRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(Integer devStatus) {
        this.devStatus = devStatus;
    }

    public Integer getWearerStatus() {
        return wearerStatus;
    }

    public void setWearerStatus(Integer wearerStatus) {
        this.wearerStatus = wearerStatus;
    }

    public Float getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Float heartRate) {
        this.heartRate = heartRate;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }
}
