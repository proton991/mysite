package com.fudan.mysite.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

class RedisUtilTest {

    @Resource
    RedisUtil redisUtil;
    @Test
    public void setTest() {
//        redisUtil = new RedisUtil();
        redisUtil.set("ss", "ss");
    }
}