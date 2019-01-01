package com.game.cp.controller;

import com.game.cp.model.ResultModel;
import com.game.cp.model.entity.ApkUpdateEntity;
import com.game.cp.services.service.ApkUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取配置的controller
 */
@RestController
public class ConfigController {
    @Autowired
    private ApkUpdateService apkUpdateService;

    @RequestMapping(value ="/updateApkInfo",method = RequestMethod.GET)
    public ResultModel updateApkInfo( @RequestParam int type)  {
        ApkUpdateEntity updateEntity = apkUpdateService.findByDeviceType(type);
        return ResultModel.createResult(ResultModel.SUCCESS,"",updateEntity);
    }

    @RequestMapping(value ="/error",method = RequestMethod.GET)
    public ResultModel updateApkInfo(@RequestParam int code, @RequestParam String msg)  {
        return ResultModel.createResult(code,msg);
    }

}
