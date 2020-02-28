package com.fudan.mysite.service.impl;

import com.alibaba.fastjson.JSON;
import com.fudan.mysite.dao.nCoV.ProvinceDao;
import com.fudan.mysite.entity.nCoV.Province;
import com.fudan.mysite.service.nCoVService;
import com.fudan.mysite.util.RedisUtil;
import com.fudan.mysite.util.nCoVUtil;
import com.fudan.mysite.vo.nCoVhttpRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class nCoVServiceImpl implements nCoVService {
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private ProvinceDao provinceDao;
    @Override
    public List<Province> loadData() {
        List<Province> provinces = null;

        if (redisUtil.get("nCoVData") != null) {
            provinces = provinceDao.findAll();
        }

        else {
            String httpUrl = "http://api.tianapi.com/txapi/ncovcity/index";
            String httpArg = "key=c426847334e46d79c0394e5f4c6b8c5c";
            String result = nCoVUtil.request(httpUrl, httpArg);
            nCoVhttpRequest request = JSON.parseObject(result, nCoVhttpRequest.class);
            provinces = request.getNewslist();
            Set<Province> provinceSet = new HashSet<>(provinces);
            redisUtil.set("nCoVData", "1");
            redisUtil.expire("nCoVData");
            provinceDao.saveAll(provinceSet);
        }

        return provinces;
    }

}
