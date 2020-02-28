package com.fudan.mysite.vo;

import com.fudan.mysite.entity.nCoV.Province;

import java.util.List;

public class nCoVhttpRequest {
    private Integer code;
    private String msg;
    private List<Province> newslist;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Province> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<Province> newslist) {
        this.newslist = newslist;
    }
}
