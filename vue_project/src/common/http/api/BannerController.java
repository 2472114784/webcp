package com.game.cp.controller;

import com.game.cp.manager.limitRequestCount.RequestLimit;
import com.game.cp.model.ResultModel;
import com.game.cp.model.entity.BannerEntity;
import com.game.cp.services.service.BannerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @ApiOperation(value="获取首页banners", notes="获取所有banner")
    @RequestLimit()
    @RequestMapping(value = "/banner", method = RequestMethod.GET)
    public ResultModel banner() {
        List<BannerEntity> banners = bannerService.findAll();
        return ResultModel.createResult(ResultModel.SUCCESS, "", banners);
    }
    @ApiOperation(value="更新banner", notes="")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "id", value = "banner的id", required = true, dataType = "int"),
            @ApiImplicitParam(name = "bannerTitle", value = "banner的名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "bannerContent", value = "banner的内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "bannerUrl", value = "banner的封面", required = true, dataType = "String"),
            @ApiImplicitParam(name = "bannerType", value = "banner的类型", required = true, dataType = "int"),


    })
    @RequestMapping(value = "/updateBanner", method = RequestMethod.GET)
    public ResultModel updateBanner(@RequestParam long id, @RequestParam String bannerTitle, @RequestParam String bannerContent, @RequestParam String bannerUrl, @RequestParam int bannerType) {
        BannerEntity banner = bannerService.update(id, bannerTitle, bannerContent, bannerUrl, bannerType);
        if(banner==null){
            return ResultModel.createResult(ResultModel.FAILE, "更新失败");
        }
        return ResultModel.createResult(ResultModel.SUCCESS, "更新成功");
    }
    @ApiOperation(value="添加banner", notes="")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "bannerTitle", value = "banner的名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "bannerContent", value = "banner的内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "bannerUrl", value = "banner的封面", required = true, dataType = "String"),
            @ApiImplicitParam(name = "bannerType", value = "banner的类型", required = true, dataType = "int"),


    })
    @RequestMapping(value = "/addBanner", method = RequestMethod.GET)
    public ResultModel addBanner( @RequestParam String bannerTitle, @RequestParam String bannerContent, @RequestParam String bannerUrl, @RequestParam int bannerType) {
        BannerEntity banner = bannerService.addBanner(bannerTitle, bannerContent, bannerUrl, bannerType);
        if (banner==null){
            return ResultModel.createResult(ResultModel.FAILE, "添加失败");
        }
        return ResultModel.createResult(ResultModel.SUCCESS, "添加成功");
    }
}
