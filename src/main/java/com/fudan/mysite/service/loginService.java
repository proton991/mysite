package com.fudan.mysite.service;

import com.fudan.mysite.vo.LoginInfo;

public interface loginService {
    LoginInfo getLoginInfo(String username);
}
