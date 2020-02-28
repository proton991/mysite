package com.fudan.mysite.controller;

import com.fudan.mysite.base.controller.BaseApiController;
import com.fudan.mysite.base.result.Result;
import com.fudan.mysite.entity.nCoV.Province;
import com.fudan.mysite.service.nCoVService;
import com.fudan.mysite.vo.ProvinceVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class homeController extends BaseApiController {

    @Resource
    private nCoVService nCoVService;

    @RequestMapping(value = "/nCoVData", method = RequestMethod.GET)
    public Result<Object> nCoVData() {
        List<Province> provinces = nCoVService.loadData();
        List<ProvinceVO> provinceVOS = new ArrayList<>();
        for (Province province : provinces) {
            ProvinceVO provinceVO = new ProvinceVO();
            provinceVO.setName(province.getProvinceShortName());
            provinceVO.setValue(province.getConfirmedCount());
            provinceVOS.add(provinceVO);
        }
        return Result.success(provinceVOS);
    }
}
