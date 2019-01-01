package com.game.cp.controller;

import com.game.cp.config.MoneyConfig;
import com.game.cp.model.ResultModel;
import com.game.cp.model.entity.MoneyChannelEntity;
import com.game.cp.model.entity.MoneyOrderEntity;
import com.game.cp.model.entity.UserEntity;
import com.game.cp.services.service.MoneyChannelService;
import com.game.cp.services.service.MoneyOrderService;
import com.game.cp.services.service.UserService;
import com.game.cp.utils.AppUtils;
import com.game.cp.utils.Md5Util;
import com.game.cp.utils.SeedUtil;
import com.game.cp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
public class MoneyOrderController {


    @Autowired
    private MoneyOrderService moneyOrderService;
    @Autowired
    private UserService userService;
    @Autowired
    private MoneyChannelService moneyChannelService;
    /**
     * 创建 提现/充值 订单
     *
     * @param orderType
     * @param money
     * @return
     */
    @Transactional
    @RequestMapping(value = "/createMoneyOrder", method = RequestMethod.GET)
    public ResultModel createMoneyOrder(@RequestHeader String token, @RequestParam String password,@RequestParam long channelId,@RequestParam int orderType, @RequestParam double money,@RequestParam String orderUserName,@RequestParam String orderCertificate) {
        if(StringUtils.isEmpty(token)||channelId==0||orderType==0||money==0){
            return ResultModel.createResult(ResultModel.FAILE, "参数错误");
        }
        UserEntity user = userService.findUserByToken(token);
        if (user == null) {
            //用户不存在
            return ResultModel.createResult(ResultModel.FAILE, "用户可能在其他地方登录");
        }
        if(orderType==MoneyConfig.ORDER_MONEY_TYPE_CHONGZHI&&StringUtils.isEmpty(orderUserName,orderCertificate)){
            return ResultModel.createResult(ResultModel.FAILE, "参数错误");
        }else if(orderType==MoneyConfig.ORDER_MONEY_TYPE_TIXIAN){
            // TODO 检查提现密码是否正确
            if(StringUtils.isEmpty(user.getWithdrawPassword())){
                return ResultModel.createResult(ResultModel.FAILE, "还没有设置提现密码");
            }
            if(!user.getWithdrawPassword().equals(Md5Util.md5Hex(password))){
                return ResultModel.createResult(ResultModel.FAILE, "密码错误,请重试");
            }
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());
        MoneyOrderEntity moneyOrder = new MoneyOrderEntity();
        moneyOrder.setId(SeedUtil.getId());
        moneyOrder.setMoney(money);
        moneyOrder.setOrderType(orderType);
        moneyOrder.setChannelId(channelId);
        moneyOrder.setCreateTime(now);
        moneyOrder.setUpdateTime(now);
        moneyOrder.setOrderUserName(orderUserName);
        moneyOrder.setOrderCertificate(orderCertificate);
        moneyOrder.setOrderNo(AppUtils.createMoneyOrderNo(orderType));
        moneyOrder.setStatus(MoneyConfig.MONEY_ORDER_STATUS_PENDING);
        moneyOrder.setUserId(user.getId());

        return moneyOrderService.createOrder(token,moneyOrder);
    }

    /**
     * 查看用户 提现/充值 订单
     *
     * @return
     */
    @RequestMapping(value = "/selectMoneyOrder", method = RequestMethod.GET)
    public ResultModel selectMoneyOrder(@RequestHeader String token,@RequestParam int orderStatus,@RequestParam(value = "page", defaultValue = "0") Integer page) {

        return moneyOrderService.findMoneyOrderByTokenAndOrderStatus(token,orderStatus,page);
    }

    /**
     * 操作订单
     * @param token
     * @param id
     * @param event MoneyConfig.
 *                  public static final int MONEY_ORDER_STATUS_CANCEL = 1;  // 已拒绝/取消
 *     public static final int MONEY_ORDER_STATUS_PENDING = 2;  // 待处理
 *     public static final int MONEY_ORDER_STATUS_COMPLETED = 3;  // 已处理
 *     public static final int MONEY_ORDER_STATUS_REFUSE = 4;//订单已拒绝
     */
    @RequestMapping(value = "/updateMoneyOrder", method = RequestMethod.GET)
    public ResultModel updateMoneyOrder(@RequestHeader String token, @RequestParam long id, @RequestParam int event){
        return moneyOrderService.handlerMoneyOrder(token,id,event);
    }

    /**
     * 获得money渠道
     * @param channelType
     */
    @RequestMapping(value = "/getMoneyChannel", method = RequestMethod.GET)
    public ResultModel getMoneyChannel(@RequestHeader String token,@RequestParam int channelType){
        return moneyChannelService.findMoneyChannelByType(token,channelType);
    }

    /**
     * 创建money渠道
     */
    @RequestMapping(value = "/createMoneyChannel", method = RequestMethod.POST)
    public ResultModel createWithdrawChannel(@RequestHeader String token,@RequestBody MoneyChannelEntity moneyChannel ){
        return moneyChannelService.createMoneyChannel(token,moneyChannel);
    }

    @RequestMapping(value = "/findAllByOrderTypeAndOrderStatus", method = RequestMethod.GET)
    public ResultModel findAllByOrderTypeAndOrderStatus(@RequestHeader String token,@RequestParam int orderType,@RequestParam int orderStatus,@RequestParam int page){
        return moneyOrderService.findAllByOrderTypeAndOrderStatus(token,orderType,orderStatus,page);
    }
}
