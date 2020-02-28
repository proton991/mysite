package com.fudan.mysite.entity.nCoV;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Province {
    @Column
    private String provinceName;
    @Id
    @Column(name = "province_sname")
    private String provinceShortName;
    @Column
    private Integer confirmedCount;
    @Column
    private Integer suspectedCount;
    @Column
    private Integer curedCount;
    @Column
    private Integer deadCount;
    @Column
    private Integer locationId;


    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceShortName() {
        return provinceShortName;
    }

    public void setProvinceShortName(String provinceShortName) {
        this.provinceShortName = provinceShortName;
    }

    public Integer getConfirmedCount() {
        return confirmedCount;
    }

    public void setConfirmedCount(Integer confirmedCount) {
        this.confirmedCount = confirmedCount;
    }

    public Integer getSuspectedCount() {
        return suspectedCount;
    }

    public void setSuspectedCount(Integer suspectedCount) {
        this.suspectedCount = suspectedCount;
    }

    public Integer getCuredCount() {
        return curedCount;
    }

    public void setCuredCount(Integer curedCount) {
        this.curedCount = curedCount;
    }

    public Integer getDeadCount() {
        return deadCount;
    }

    public void setDeadCount(Integer deadCount) {
        this.deadCount = deadCount;
    }


    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }
}
