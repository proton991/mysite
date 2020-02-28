package com.fudan.mysite.controller;

import com.fudan.mysite.base.controller.BaseApiController;
import com.fudan.mysite.base.controller.BaseController;
import com.fudan.mysite.base.result.Result;
import com.fudan.mysite.base.result.ResultCode;
import com.fudan.mysite.entity.RBAC.SysRole;
import com.fudan.mysite.entity.RBAC.UserInfo;
import com.fudan.mysite.entity.UserProfile;
import com.fudan.mysite.service.loginService;
import com.fudan.mysite.service.sysRoleService;
import com.fudan.mysite.service.userInfoService;
import com.fudan.mysite.service.userProfileService;
import com.fudan.mysite.util.RedisUtil;
import com.fudan.mysite.util.TokenGenerator;
import com.fudan.mysite.vo.LoginInfo;
import com.fudan.mysite.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;

import javax.annotation.Resource;
import java.util.*;

@RestController
public class LoginController extends BaseApiController {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private TokenGenerator tokenGenerator;
    @Resource
    private userInfoService userInfoService;

    @Resource
    private loginService loginService;

    @Resource
    private sysRoleService sysRoleService;

    @Resource
    private userProfileService userProfileService;

    @RequestMapping(value = "/guest")
    public String viewAsGuest() {
        return "you are viewing as a guest, your privileges are limited!";
    }
//
//    @RequestMapping(value = "/shiroLogin", produces = "application/json; charset=UTF-8")
//    public Result<Object> shiroLogin(@RequestBody UserVO userVO) {
//        System.out.println("Start to login in...");
//        Subject subject = SecurityUtils.getSubject();
////        System.out.println(subject.isAuthenticated());
//        if (redisUtil.get(userVO.getUsername()) != null) {
//            System.out.println("You have already logged in!");
//            return Result.failure(ResultCode.ALREADY_LOGGED_IN);
//        }
//
//        UsernamePasswordToken token = new UsernamePasswordToken(userVO.getUsername(), userVO.getPassword());
//        try {
//            subject.login(token);
//            String redisToken = tokenGenerator.generate(userVO.getUsername());
//            redisUtil.set(userVO.getUsername(), redisToken);
//            redisUtil.set(redisToken, userVO.getUsername());
////            System.out.println(token.toString());
//            LoginInfo loginInfo = loginService.getLoginInfo(userVO.getUsername());
//            System.out.println("redis keys have been set...");
//            System.out.println("login success!");
//            return Result.success(loginInfo);
//        } catch (IncorrectCredentialsException e) {
//            System.out.println(e.getMessage());
//            return Result.failure(ResultCode.WRONG_PASSWORD);
//        } catch (AuthenticationException e) {
//            System.out.println(e.getMessage());
//            return Result.failure(ResultCode.USER_NOT_EXIST);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return Result.failure(ResultCode.LOGIN_FAILED);
//    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public Result<Object> login(@RequestBody UserVO userVO) {
        String username = userVO.getUsername();
        UserInfo userInfo = userInfoService.findUserByName(username);
        if (userInfo != null) {
            ByteSource salt = ByteSource.Util.bytes(userVO.getUsername());
            Object password = new SimpleHash("MD5", userVO.getPassword(), salt, 1);
            if (userInfo.getPassword().equals(password.toString())) {
                String redisToken = tokenGenerator.generate(userVO.getUsername());
                redisUtil.set(userVO.getUsername(), redisToken);
                redisUtil.set(redisToken, userVO.getUsername());
                LoginInfo loginInfo = loginService.getLoginInfo(userVO.getUsername());
                System.out.println("login success!");
            return Result.success(loginInfo);
            }
            else return Result.failure(ResultCode.WRONG_PASSWORD);
        }
        return Result.failure(ResultCode.USER_NOT_EXIST);
    }

    @RequestMapping(value = "/registry", produces = "application/json; charset=UTF-8")
    public Result<Object> registry(@RequestBody UserVO userVO) {
        if (userInfoService.findUserByName(userVO.getUsername()) != null)
            return Result.failure(ResultCode.USER_EXIST);
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userVO.getUsername());
        String uuid = UUID.randomUUID().toString().replace("-", "");
        userInfo.setUid(uuid);
        Set<SysRole> roleList = new HashSet<>();
        roleList.add(sysRoleService.findSysRoleByRole("user"));
        userInfo.setRoleList(roleList);
        Byte state = 1;
        userInfo.setState(state);
        ByteSource salt = ByteSource.Util.bytes(userVO.getUsername());
        Object password = new SimpleHash("MD5", userVO.getPassword(), salt, 1);
        userInfo.setPassword(password.toString());
        UserProfile userProfile = new UserProfile();
        userProfile.setUsername(userVO.getUsername());
        userInfo.setUserProfile(userProfile);
        userProfileService.saveProfile(userProfile);
        userInfoService.saveUserInfo(userInfo);
        return Result.success(ResultCode.REGISTRY_SUCCEED);
    }

    @RequestMapping(value = "/logout")
    public Result<Object> logout(@RequestParam("token")String token) {
//        Subject subject = SecurityUtils.getSubject();
//        String username = subject.getPrincipal().toString();
        String redisUsername = redisUtil.get(token);
        if (redisUsername != null) {
            redisUtil.delete(redisUsername);
            redisUtil.delete(token);
            System.out.println("logout success");
            return Result.success(ResultCode.LOGOUT_SUCCEED);
        }
        return Result.failure(ResultCode.LOGOUT_FAILED);
    }

    @RequestMapping(value = "/unauth")
    public Result<Object> unauth() {

        //前端进行处理，跳转到登陆界面
        return Result.failure(ResultCode.NOT_LOGIN);
    }
}
