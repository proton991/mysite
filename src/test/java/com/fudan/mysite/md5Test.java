package com.fudan.mysite;

import com.fudan.mysite.util.RedisUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

public class md5Test {

//    public static void main(String[] args) {
//        ByteSource salt = ByteSource.Util.bytes("admin");
//        Object res = new SimpleHash("MD5", "123456", salt,1);
////        System.out.println(UUID.randomUUID().toString());
////        System.out.println(res.toString());
//        System.out.println(new Date().getTime());
//    }
//    public static void main(String[] args) {
//        RedisUtil redisUtil = new RedisUtil();
//        redisUtil.set("nCoVData", "2");
//        redisUtil.expire("nCoVData");
//    }
}
