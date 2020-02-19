package com.fudan.mysite.controller;

import com.fudan.mysite.base.controller.BaseController;
import com.fudan.mysite.service.userInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private userInfoService userInfoService;

    @RequestMapping(value = "/admin/addUsers", method = RequestMethod.POST)
    public String addUser(){
        return "addUser";
    }
}
