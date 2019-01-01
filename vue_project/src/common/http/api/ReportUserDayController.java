package com.game.cp.controller;

import com.game.cp.model.ResultModel;
import com.game.cp.services.service.ReportUserDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportUserDayController {


    @Autowired
    private ReportUserDayService reportUserDayService;

    /**
     * 查看自己的报表（暂不用）
     * @param token
     * @param page
     * @return
     */
    @RequestMapping(value = "/getListReportByUserId", method = RequestMethod.GET)
    public ResultModel getListReportByUserId(@RequestHeader String token,@RequestParam int page) {
        return reportUserDayService.getListReportByUserId(token,page);
    }


    @RequestMapping(value = "/adminGetListReportByUserId", method = RequestMethod.GET)
    public ResultModel adminGetListReportByUserId(@RequestHeader String token,@RequestParam long userId,@RequestParam int page) {
        return reportUserDayService.adminGetListReportByUserId(token,userId,page);
    }
}
