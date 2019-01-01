package com.game.cp.controller;

import com.game.cp.manager.lottery.LotteryInterface;
import com.game.cp.manager.lottery.LotteryManager;
import com.game.cp.model.ResultModel;
import com.game.cp.model.entity.openResult.entity.OpenResultEntity;
import com.game.cp.services.service.OpenLotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LotteryResultController {
    @Autowired
    private OpenLotteryService openLotteryService;
    @Autowired
    private LotteryManager lotteryManager;


    @RequestMapping(value = "/getListLotteryResultByLotteryId", method = RequestMethod.GET)
    public ResultModel getListLotteryResultByLotteryId(@RequestParam long lotteryId, @RequestParam int page) {
        Page<OpenResultEntity> pageList = openLotteryService.findAll(lotteryId, page);
        return ResultModel.createResult(ResultModel.SUCCESS, "ok",pageList);
    }


    @RequestMapping(value = "/openLotteryByNo", method = RequestMethod.GET)
    public ResultModel openLotteryByNo(@RequestParam long lotteryId,@RequestParam long openNo, @RequestParam String openResult) {
        OpenResultEntity openResultEntity = new OpenResultEntity();
        openResultEntity.setOpenNo(openNo);
        openResultEntity.setOpenResult(openResult);
        openResultEntity.setLotteryId(lotteryId);
        LotteryInterface lotteryModel = this.lotteryManager.getLotteryManager(lotteryId);
        if(lotteryModel!=null) {
            lotteryModel.openLottery(openResultEntity);
        }
        return ResultModel.createResult(ResultModel.SUCCESS, "ok");
    }

}
