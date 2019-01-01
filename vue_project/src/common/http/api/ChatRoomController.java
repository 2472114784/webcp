package com.game.cp.controller;

import com.game.cp.model.ResultModel;
import com.game.cp.model.entity.ChatRoomEntity;
import com.game.cp.model.entity.LotteryEntity;
import com.game.cp.services.service.ChatRoomService;
import com.game.cp.services.service.LotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private LotteryService lotteryService;
    //获取rongToken
    @RequestMapping(value = "/getRoomToken", method = RequestMethod.GET)
    public ResultModel getRoomToken(@RequestHeader String  token) throws Exception {

        return chatRoomService.getRoomToken(token);
    }

    @RequestMapping(value = "/createRoom", method = RequestMethod.GET)
    public ResultModel createRoom(@RequestParam String roomName) throws Exception {
        return chatRoomService.createRoom(roomName);
    }


    @RequestMapping(value = "/destroyRoom", method = RequestMethod.GET)
    public ResultModel destroyRoom(@RequestParam String roomId) throws Exception {
//        Chatroom rongRoom = ChatUtils.getRongRoom();
//        /**
//         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/chatroom/chatroom.html#create
//         *
//         * 销毁聊天室
//         *
//         * */
//        ChatroomModel[] chatrooms = {
//                new ChatroomModel().setId("chatroomId"+System.currentTimeMillis()).setName(roomName)
//        };
//        ResponseResult result = rongRoom.create(chatrooms);
//        //TODO 存储到数据库
//        System.out.println("create:  " + result.toString());
        return ResultModel.createResult(ResultModel.SUCCESS,"");
    }


    @RequestMapping(value = "/sendRoomMessage", method = RequestMethod.GET)
    public ResultModel sendRoomMessage(@RequestHeader String token,@RequestParam String message) throws Exception {
        return chatRoomService.sendRoomMessage(token,message);
    }

    @RequestMapping(value = "/sendRoomBroadcast", method = RequestMethod.GET)
    public ResultModel sendRoomBroadcast(@RequestHeader String token,@RequestParam String message) throws Exception {
        return chatRoomService.sendRoomBroadcast(token,message);

    }

    /**
     * getLotteryRoomByLotteryId
     *
     * @return
     */
    @RequestMapping(value = "/getLotteryRoomByLotteryId", method = RequestMethod.GET)
    public ResultModel getLotteryRoomByLotteryId(long lotteryId) {
        LotteryEntity lottery = lotteryService.getLotteryDetailByLotteryId(lotteryId);
        return ResultModel.createResult(ResultModel.SUCCESS, "", lottery);
    }

    /**
     * 加入房间
     * @param token
     * @param roomId
     * @return
     */
    @RequestMapping(value = "/joinChatRoom", method = RequestMethod.GET)
    public ResultModel joinChatRoom(@RequestHeader String token,@RequestParam String roomId) {
        return chatRoomService.joinChatRoom(token,roomId);

    }

    /**
     * 退出房间房间
     * @param token
     * @return
     */
    @RequestMapping(value = "/exitChatRoom", method = RequestMethod.GET)
    public ResultModel exitChatRoom(@RequestHeader String token) {
        return chatRoomService.exitChatRoom(token);

    }

    /**
     * 添加禁言XX（指定房间禁言）
     * @param token
     * @param roomId
     * @param ids
     * @throws Exception
     */
    @RequestMapping(value ="/gagAddUserChatRoom",method = RequestMethod.GET)
    public ResultModel gagAddUserChatRoom(@RequestHeader String token, @RequestParam String roomId , @RequestParam long userId) throws Exception {
       return chatRoomService.gagAddUserChatRoom(token,roomId,userId);

    }

    /**
     * 移除禁言XX（指定房间禁言）
     * @param token
     * @param roomId
     * @param ids
     * @throws Exception
     */
    @RequestMapping(value ="/gagRemoveUserChatRoom",method = RequestMethod.GET)
    public ResultModel gagRemoveUserChatRoom(@RequestHeader String token, @RequestParam long userId) throws Exception {
        return chatRoomService.gagRemoveUserChatRoom(token,userId);
    }

    /**
     * 获取该房间的禁言人员
     * @param token
     * @param roomId
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/getListGagUserChatRoom",method = RequestMethod.GET)
    public ResultModel getListGagUserChatRoom(@RequestHeader String token) throws Exception {
        return chatRoomService.getListGagUserChatRoom(token);
    }


    /**
     * 校验房间密码
     * @param token
     * @param roomId
     * @param roomPassword
     * @return
     */
    @RequestMapping(value ="/checkRoomPassword",method = RequestMethod.GET)
    public ResultModel checkRoomPassword(@RequestHeader String token,@RequestParam int roomId,@RequestParam String roomPassword){
        return chatRoomService.checkRoomPassword(token,roomId,roomPassword);
    }

}
