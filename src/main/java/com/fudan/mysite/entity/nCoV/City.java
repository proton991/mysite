package com.fudan.mysite.entity.nCoV;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
//
//@Entity
//public class City {
//    @Id
//    @Column(insertable=false,updatable=false, name = "city_name")
//    private String cityName;
//    @Column
//    private Integer confirmedCount;
//    @Column
//    private Integer suspectedCount;
//    @Column
//    private Integer curedCount;
//    @Column
//    private Integer deadCount;
//    @Column
//    private Integer locationId;
//
//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "province_sname")
//    private Province province;
//
//    public String getCityName() {
//        return cityName;
//    }
//
//    public void setCityName(String cityName) {
//        this.cityName = cityName;
//    }
//
//    public Integer getConfirmedCount() {
//        return confirmedCount;
//    }
//
//    public void setConfirmedCount(Integer confirmedCount) {
//        this.confirmedCount = confirmedCount;
//    }
//
//    public Integer getSuspectedCount() {
//        return suspectedCount;
//    }
//
//    public void setSuspectedCount(Integer suspectedCount) {
//        this.suspectedCount = suspectedCount;
//    }
//
//    public Integer getCuredCount() {
//        return curedCount;
//    }
//
//    public void setCuredCount(Integer curedCount) {
//        this.curedCount = curedCount;
//    }
//
//    public Integer getDeadCount() {
//        return deadCount;
//    }
//
//    public void setDeadCount(Integer deadCount) {
//        this.deadCount = deadCount;
//    }
//
//    public Integer getLocationId() {
//        return locationId;
//    }
//
//    public void setLocationId(Integer locationId) {
//        this.locationId = locationId;
//    }
//
//
//    public Province getProvince() {
//        return province;
//    }
//
//    public void setProvince(Province province) {
//        this.province = province;
//    }
//
//}
