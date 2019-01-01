package com.game.cp.controller;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.game.cp.manager.limitRequestCount.RequestLimit;
import com.game.cp.model.ResultModel;
import com.game.cp.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


/**
 * Created by sjj on 2015/10/24 0024.
 */
@RestController
public class UserController {
//    @Autowired
//	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // 自动装配
    @Autowired
    private UserService userService;


    //注册
    @RequestLimit(count = 5,time = 60000,interval = 5000)
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ResultModel register(@RequestParam String account, @RequestParam String password,@RequestParam String inviteCode) {

        return userService.register(account,password,inviteCode);
    }




    //登录
//    @Cacheable(cacheNames = "test")
    @JsonSerialize
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResultModel login(@RequestParam String account, @RequestParam String password) {
//		logger.info("{} 登入系统成功!", "aaaa");
//        throw new RuntimeException("故意登录报错");
        return userService.login(account,password);
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public ResultModel getUserInfo(@RequestHeader String token){
        return userService.getUserInfo(token);
    }


    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.GET)
    public ResultModel updatePortrait(@RequestHeader String token, @RequestParam String userName, @RequestParam String portrait) throws Exception {

        return userService.updateUserInfo(token,userName,portrait);
    }
    /**
     * 设置提现密码
     * @param token
     * @param password
     */
    @RequestMapping(value = "/setWithdrawPassword", method = RequestMethod.GET)
    public ResultModel setWithdrawPassword(@RequestHeader String token, @RequestParam String password){
        return userService.setWithdrawPasswordByToken(token,password);
    }



    /**
     * 设置提现密码
     * @param token
     */
    @RequestMapping(value = "/updateWithdrawPassword", method = RequestMethod.GET)
    public ResultModel updateWithdrawPassword(@RequestHeader String token, @RequestParam String oldPassword, @RequestParam String newPassword){
       return userService.updateWithdrawPasswordByToken(token,oldPassword,newPassword);
    }
//============================= 以下测试用
    /**
     * 测试用
     * @param count
     * @return
     */
    @RequestLimit(count = 5,time = 60000,interval = 5000)
    @RequestMapping(value = "/registerTestUsers", method = RequestMethod.GET)
    public ResultModel registerTestUsers(@RequestParam int count) {
        return ResultModel.createResult(ResultModel.FAILE,"目前已停用此接口");
//        if(count>2000){
//            return ResultModel.createResult(ResultModel.FAILE,"最多只能创建2000测试账号");
//        }
//        return userService.registerTestUsers(count);
    }

    /**
     * 获取测试用户
     * @param count
     * @return
     */
    @RequestMapping(value = "/getTestUsers", method = RequestMethod.GET)
    public ResultModel getTestUsers(@RequestParam int count) {
        return ResultModel.createResult(ResultModel.FAILE,"目前已停用此接口");

//        if(count>2000){
//            return ResultModel.createResult(ResultModel.FAILE,"最多只能获取2000测试账号");
//        }
//        return userService.getTestUsers(count);
    }

}