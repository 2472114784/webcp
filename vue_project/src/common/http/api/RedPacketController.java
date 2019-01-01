package com.game.cp.controller;

import com.game.cp.config.ChatConfig;
import com.game.cp.manager.redPacket.RedPacketConfig;
import com.game.cp.model.ResultModel;
import com.game.cp.model.entity.RedPacketEntity;
import com.game.cp.services.service.RedPacketService;
import com.game.cp.services.service.RedPacketRecodeService;
import com.game.cp.utils.SeedUtil;
import com.game.cp.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedPacketController {

    @Autowired
    private RedPacketService redPacketService;

    @Autowired
    private RedPacketRecodeService redPacketRecodeService;

    /**
     * 发送红包
     * @param token
     * @param password
     * @param type
     * @param money
     * @param num
     * @return
     */
    @RequestMapping(value ="/sendRedPacket",method = RequestMethod.GET)
    public ResultModel sendRedPacket(@RequestHeader String token, @RequestParam String password,@RequestParam int type,@RequestParam double money,@RequestParam int num,@RequestParam String remark){
        RedPacketEntity redPacketEntity = new RedPacketEntity();
        redPacketEntity.setMoney(money);
        redPacketEntity.setNum(num);
        redPacketEntity.setType(type);
        redPacketEntity.setId(SeedUtil.getId());
        redPacketEntity.setRemark(StringUtils.isEmpty(remark)?ChatConfig.RED_PACKET_DEFAULT_REMARK :remark);
        return redPacketService.sendRedPacket(token,password,redPacketEntity);
    }


    /**
     * 抢红包
     * @param token
     * @return
     */
    @RequestMapping(value ="/receiveRedPacket",method = RequestMethod.GET)
    public ResultModel receiveRedPacket(@RequestHeader String token,@RequestParam long id){
        return redPacketService.receiveRedPacket(token,id);
    }

    /**
     * 查看红包记录列表
     * @param token
     * @param id
     * @return
     */
    @RequestMapping(value ="/getListRedPacketRecode",method = RequestMethod.GET)
    public ResultModel getListRedPacketRecode(@RequestHeader String token,@RequestParam int type,@RequestParam int page){
        return redPacketRecodeService.getListRedPacketRecode(token,type,page);
    }


    @RequestMapping(value ="/getRedPacketDetail",method = RequestMethod.GET)
    public ResultModel getRedPacketDetail(@RequestHeader String token,@RequestParam long redPacketId){
        return redPacketService.getRedPacketDetail(token,redPacketId);
    }

    /**
     * 获取红包详情中的已抢列表
     * @param token
     * @param type
     * @param page
     * @return
     */
    @RequestMapping(value ="/getRedPacketDetailListByRedPacketId",method = RequestMethod.GET)
    public ResultModel getRedPacketDetailListByRedPacketId(@RequestHeader String token,@RequestParam long redPacketId,@RequestParam int page){
        return redPacketRecodeService.findAllByRedPacket(token,redPacketId,RedPacketConfig.ACTION_TYPE_RECEIVER,page);
    }

}
