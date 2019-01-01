package com.game.cp.controller;

import com.game.cp.manager.limitRequestCount.RequestLimit;
import com.game.cp.model.ResultModel;
import com.game.cp.model.entity.LotteryEntity;
import com.game.cp.services.service.EasyShoppingService;
import com.game.cp.services.service.LotteryOrderService;
import com.game.cp.services.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LotteryOrderController {
    @Autowired
    private LotteryOrderService lotteryOrderService;

    @Autowired
    private LotteryService lotteryService;

    @Autowired
    private EasyShoppingService easyShoppingService;

    /**创建订单
     *
     */
    @Transactional
    @RequestLimit(interval = 1000)
    @RequestMapping(value = "/createLotteryOrder", method = RequestMethod.POST)
    public ResultModel createPcOrder(@RequestHeader String token,@RequestHeader long request_time,@RequestBody LotteryEntity orders) {
        return lotteryOrderService.createOrders(token, request_time,orders);

    }

    @Transactional
    @RequestMapping(value = "/cancelLotteryOrder", method = RequestMethod.GET)
    public ResultModel cancelLotteryOrder(@RequestHeader String token,@RequestParam long orderId){
        return lotteryOrderService.cancelOrder(token, orderId);

    }


    /**
     * 查看用户所有pc订单
     *
     * @return
     */
    @RequestMapping(value = "/selectLotteryOrder", method = RequestMethod.GET)
    public ResultModel selectLotteryOrder(@RequestHeader String token, @RequestParam int orderStatus,@RequestParam(value = "page", defaultValue = "0") Integer page) {// @PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        return lotteryOrderService.findAllLotteryOrderByTokenAndStatus(token, orderStatus,page);
    }





    /**
     * 查看用户历史订单
     *
     * @return
     */
    @RequestMapping(value = "/selectPCHositoryOrder", method = RequestMethod.GET)
    public ResultModel selectPCHositoryOrder(@RequestHeader String token, @RequestParam int orderStatus, @RequestParam(value = "page", defaultValue = "0") Integer page) {

         return lotteryOrderService.findAllLotteryOrderByTokenAndStatus(token, orderStatus, page);

    }

    /**
     * 获取彩种具体玩法
     *
     * @param lottery_id 菜种id
     * @return
     */
    @RequestMapping(value = "/getLotteryById", method = RequestMethod.GET)
    public ResultModel getLotteryById(@RequestParam long lottery_id) {
        LotteryEntity lottery = lotteryService.getLotteryDetailByLotteryId(lottery_id);
        return ResultModel.createResult(ResultModel.SUCCESS, "", lottery);
    }

    @RequestMapping(value = "/getLotteryList", method = RequestMethod.GET)
    public ResultModel getLotteryList() {
        List<LotteryEntity> all = lotteryService.getLotteryList();
        return ResultModel.createResult(ResultModel.SUCCESS, "", all);
    }

    /**
     * 创建或修改快捷下注
     * @param token
     * @param lotteryId
     * @param items
     * @return
     */
    @RequestMapping(value = "/createEasyShoppingByLotteryId",method = RequestMethod.GET)
    public ResultModel createEasyShoppingByLotteryId(@RequestHeader String token,@RequestParam long lotteryId,@RequestParam String items){
        return easyShoppingService.createEasyShoppingByLotteryId(token,lotteryId,items);
    }

    /**
     * 获取快捷下注
     * @param token
     * @param lotteryId
     * @return
     */
    @RequestMapping(value = "/getEasyShoppingByLotteryId",method = RequestMethod.GET)
    public ResultModel getEasyShoppingByLotteryId(@RequestHeader String token,@RequestParam long lotteryId){
        return easyShoppingService.getEasyShoppingByLotteryId(token,lotteryId);
    }

    /**
     * 获取快捷支付
     * @param token
     * @param lotteryId
     * @return
     */
    @RequestMapping(value = "/getEasyLotteryEntityByLotteryId",method = RequestMethod.GET)
    public ResultModel getEasyLotteryEntityByLotteryId(@RequestHeader String token,@RequestParam long lotteryId){
        return easyShoppingService.getEasyLotteryEntityByLotteryId(token,lotteryId);
    }

}
