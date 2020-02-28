package com.fudan.mysite.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fudan.mysite.vo.nCoVhttpRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class RedisUtilTest {

//    @Resource
//    RedisUtil redisUtil;
//    @Test
//    public void setTest() {
////        redisUtil = new RedisUtil();
//        redisUtil.set("ss", "ss");
//    }
    public static void main(String[] args) {
//        String httpUrl = "http://api.tianapi.com/txapi/ncovcity/index";
//        String httpArg = "key=c426847334e46d79c0394e5f4c6b8c5c";
//        String jsonResult = request(httpUrl, httpArg);
//        JSONObject.parseObject(jsonResult);
//        System.out.println(jsonResult.indexOf("["));
//        String objListStr = jsonResult.substring(jsonResult.indexOf("["));

//        System.out.println(objListStr);
    }


    /**
     * @param httpUrl
     *            :请求接口
     * @param httpArg
     *            :参数
     * @return 返回结果
     */
//    public static String request(String httpUrl, String httpArg) {
//        BufferedReader reader = null;
//        String result = null;
//        StringBuffer sbf = new StringBuffer();
//        httpUrl = httpUrl + "?" + httpArg;
//
//        try {
//            URL url = new URL(httpUrl);
//            HttpURLConnection connection = (HttpURLConnection) url
//                    .openConnection();
//            connection.setRequestMethod("GET");
//            InputStream is = connection.getInputStream();
//            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//            String strRead = null;
//            while ((strRead = reader.readLine()) != null) {
//                System.out.println(strRead);
//                sbf.append(strRead);
//                sbf.append("\r\n");
//            }
//            reader.close();
//            result = sbf.toString();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        nCoVhttpRequest request = JSON.parseObject(result, nCoVhttpRequest.class);
//        System.out.println();
//        return result;
//    }
}