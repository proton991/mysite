package com.fudan.mysite.controller;

import com.fudan.mysite.base.controller.BaseController;
import com.fudan.mysite.base.result.Result;
import com.fudan.mysite.base.result.ResultCode;
import com.fudan.mysite.entity.RBAC.SysRole;
import com.fudan.mysite.entity.RBAC.UserInfo;
import com.fudan.mysite.service.loginService;
import com.fudan.mysite.service.sysRoleService;
import com.fudan.mysite.service.userInfoService;
import com.fudan.mysite.vo.LoginInfo;
import com.fudan.mysite.vo.UserVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class LoginController extends BaseController {

    @Resource
    private userInfoService userInfoService;

    @Resource
    private loginService loginService;

    @Resource
    private sysRoleService sysRoleService;

    @RequestMapping(value = "/guest", method = RequestMethod.GET)
    public String viewAsGuest() {
        return "you are viewing as a guest, your privileges are limited!";
    }

    @RequestMapping(value = "/shiroLogin", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Result<Object> shiroLogin(@RequestBody UserVO userVO) {
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.isAuthenticated());
        UsernamePasswordToken token = new UsernamePasswordToken(userVO.getUsername(), userVO.getPassword());
        try {
            subject.login(token);
            LoginInfo loginInfo = loginService.getLoginInfo(userVO.getUsername());
            return Result.success(loginInfo);
        } catch (IncorrectCredentialsException e) {
            System.out.println(e.getMessage());
            return Result.failure(ResultCode.WRONG_PASSWORD);
        } catch (AuthenticationException e) {
            return Result.failure(ResultCode.USER_NOT_EXIST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.failure(ResultCode.LOGIN_FAILED);
    }

    @RequestMapping(value = "/login")
    public Result<Object> login(@RequestBody UserVO userVO) {
        String username = userVO.getUsername();
        UserInfo userInfo = userInfoService.findUserByName(username);
        if (userInfo != null) {
            if (userInfo.getPassword().equals(userVO.getPassword()))
                return Result.success();
            else return Result.failure(ResultCode.WRONG_PASSWORD);
        }
        return Result.failure(ResultCode.USER_NOT_EXIST);
    }

    @RequestMapping(value = "/registry", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public Result<Object> registry(@RequestBody UserVO userVO) {
        if (userInfoService.findUserByName(userVO.getUsername()) != null)
            return Result.failure(ResultCode.USER_EXIST);
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userVO.getUsername());
        String uuid = UUID.randomUUID().toString().replace("-", "");
        userInfo.setUid(uuid);
        List<SysRole> roleList = new ArrayList<>();
        roleList.add(sysRoleService.findSysRoleByRole("user"));
        userInfo.setRoleList(roleList);
        Byte state = 1;
        userInfo.setState(state);
        ByteSource salt = ByteSource.Util.bytes(userVO.getUsername());
        Object password = new SimpleHash("MD5", userVO.getPassword(), salt, 1);
        userInfo.setPassword(password.toString());
        userInfoService.saveUserInfo(userInfo);
        return Result.success(ResultCode.REGISTRY_SUCCEED);
    }

    @RequestMapping(value = "/logout")
    public Result<Object> shiroLogout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.success(ResultCode.LOGOUT_SUCCEED);
    }
    @RequestMapping(value = "/unauth")
    public Result<Object> unauth() {

        //前端进行处理，跳转到登陆界面
        return Result.failure(ResultCode.NOT_LOGIN);
    }
}
