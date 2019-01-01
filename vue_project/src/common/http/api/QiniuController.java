package com.game.cp.controller;

import com.game.cp.config.QiniuConfig;
import com.game.cp.model.ResultModel;
import com.game.cp.model.entity.UserEntity;
import com.game.cp.services.service.UserService;
import com.game.cp.utils.StringUtils;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class QiniuController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getQiniuToken", method = RequestMethod.GET)
    public ResultModel getQiniuToken(@RequestHeader String token, @RequestParam String accessKey){
        if(StringUtils.isEmpty(token,accessKey)){
            return ResultModel.createResult(ResultModel.FAILE,"参数错误");
        }
        UserEntity user = userService.findUserByToken(token);
        if(user==null){
            return ResultModel.createResult(ResultModel.FAILE,"登录凭证失效");
        }
        Auth auth = Auth.create(accessKey, QiniuConfig.BUCKET_KEY);
        String qiniuToken = auth.uploadToken(QiniuConfig.BUCKET_SPACE_NAME,null,QiniuConfig.TOKEN_EFFECTIVE_TIME,null);
        user.setQiniuToken(qiniuToken);
        userService.updateUser(user);
        return ResultModel.createResult(ResultModel.SUCCESS,"",user);
    }
}
