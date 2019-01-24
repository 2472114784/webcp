import ChatRoomApi from '../http/api/ChatRoomApi'
import UserManager from '../dataManager/module/UserManager'

const appKey = 'z3v5yqkbz1jv0';

class IMManager {

  initVue(vue) {
    this.$vue = vue;
  }

  /**
   * 初始化IM
   * @param params
   * @param callbacks
   * @param modules
   */
  init() {
    console.log("初始化融云", this);
    //初始化IM
    RongIMLib.RongIMClient.init(appKey);
    //语音播放初始化
    // RongIMLib.RongIMVoice.init();

    // var instance = RongIMClient.getInstance();

    // 设置连接监听状态 （ status 标识当前连接状态 ）
    // 连接状态监听器
    RongIMClient.setConnectionStatusListener({
      onChanged: function (status) {
        switch (status) {
          case RongIMLib.ConnectionStatus.CONNECTED:
            console.log('链接成功');
            // callbacks.getInstance && callbacks.getInstance(instance);
            break;
          case RongIMLib.ConnectionStatus.CONNECTING:
            console.log('正在链接');
            break;
          case RongIMLib.ConnectionStatus.DISCONNECTED:
            console.log('断开连接');
            break;
          case RongIMLib.ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT:
            console.log('其他设备登录');
            break;
          case RongIMLib.ConnectionStatus.DOMAIN_INCORRECT:
            console.log('域名不正确');
            break;
          case RongIMLib.ConnectionStatus.NETWORK_UNAVAILABLE:
            console.log('网络不可用');
            break;
        }
      }
    });

    /*
           文档：http://www.rongcloud.cn/docs/web.html#3、设置消息监听器
           注意事项：
               1：为了看到接收效果，需要另外一个用户向本用户发消息
               2：判断会话唯一性 ：conversationType + targetId
               3：显示消息在页面前，需要判断是否属于当前会话，避免消息错乱。
               4：消息体属性说明可参考：http://rongcloud.cn/docs/api/js/index.html
           */
    // 消息监听器
    RongIMClient.setOnReceiveMessageListener({
      // 接收到的消息
      onReceived: function (message) {
        // 判断消息类型
        switch (message.messageType) {
          case RongIMClient.MessageType.TextMessage:
            // message.content.content => 消息内容
            console.log("receive:" + message.content.content);
            break;
          case RongIMClient.MessageType.VoiceMessage:
            // 对声音进行预加载
            // message.content.content 格式为 AMR 格式的 base64 码
            break;
          case RongIMClient.MessageType.ImageMessage:
            // message.content.content => 图片缩略图 base64。
            // message.content.imageUri => 原图 URL。
            break;
          case RongIMClient.MessageType.DiscussionNotificationMessage:
            // message.content.extension => 讨论组中的人员。
            break;
          case RongIMClient.MessageType.LocationMessage:
            // message.content.latiude => 纬度。
            // message.content.longitude => 经度。
            // message.content.content => 位置图片 base64。
            break;
          case RongIMClient.MessageType.RichContentMessage:
            // message.content.content => 文本消息内容。
            // message.content.imageUri => 图片 base64。
            // message.content.url => 原图 URL。
            break;
          case RongIMClient.MessageType.InformationNotificationMessage:
            // do something...
            break;
          case RongIMClient.MessageType.ContactNotificationMessage:
            // do something...
            break;
          case RongIMClient.MessageType.ProfileNotificationMessage:
            // do something...
            break;
          case RongIMClient.MessageType.CommandNotificationMessage:
            // do something...
            break;
          case RongIMClient.MessageType.CommandMessage:
            // do something...
            break;
          case RongIMClient.MessageType.UnknownMessage:
            // do something...
            break;
          case RongIMClient.MessageType.RedPacketMessage://自定义红包消息
            break;
          default:
          // do something...
        }
        iMManager.onReceiveMessageListener && iMManager.onReceiveMessageListener(message);
        console.log("onReceiveMessageListener", iMManager)
      }
    });
    /**
     * 设置过滤消息
     */
    this.setFilterMessages();
    /**
     * 注册自定义消息
     */
    this.registerCustomMessage();
    /**
     * 链接
     */
    this.connect();
  }

  /**
   * 设置过滤消息 TODO 为设置过滤内容
   */
  setFilterMessages() {
    var messageNames = ['CommandMessage']; // 可以设置多个。
    RongIMClient.getInstance().setFilterMessages(messageNames);

  }
  /**
   * 注册自定义消息
   */
  registerCustomMessage() {
    this.registerRedPacketMessage();

  }

  /**
   * 注册红包消息
   *  private long id;
   private long userId;
   private String remark;
   private double money;
   private int num;
   private int type;
   private String createTime;
   */
  registerRedPacketMessage() {
    var messageName = "RedPacketMessage"; // 消息名称。
    var objectName = "RC:Chatroom"; // 消息内置名称，请按照此格式命名。
    var isCounted = true; // 消息计数
    var isPersited = true; // 消息保存
    var mesasgeTag = new RongIMLib.MessageTag(isCounted, isPersited);// 消息是否保存是否计数，true true 计数且保存，false false 不计数不保存
    var prototypes = ["id", "userId", "remark", "money", "num", "type", "createTime"]; // 消息类中的属性名。
    RongIMClient.registerMessageType(messageName, objectName, mesasgeTag, prototypes);
  }

  /**
   * 设置接收消息监听
   * @param onReceiveMessageListener
   */
  setOnReceiveMessageListener(onReceiveMessageListener) {
    console.log("设置监听", this);
    // 消息监听器
    this.onReceiveMessageListener = onReceiveMessageListener;
  }

  /**
   * 获取token
   * @param callback
   */
  getToken(callback) {
    this.$vue.$http(ChatRoomApi.getRoomToken()).then(data => {
      UserManager.setUser(data);
      callback(data);
    })
  }

  /**
   * 链接IM
   */
  connect() {
    if (UserManager.isLogin()) {
      if (!UserManager.hasImToken()) {
        this.getToken(user => {
          this.connectIM(UserManager.getUser().rongToken);
        })
      } else {
        this.connectIM(UserManager.getUser().rongToken);
      }
    } else {
      //TODO 需要登录
    }


  }

  /**
   * 通过token链接IM
   * @param token
   */
  connectIM(token) {
    /**
     * 开始链接
     */
    RongIMClient.connect(token, {
      onSuccess: function (userId) {
        console.log("Connect successfully." + userId);
        // callbacks.getCurrentUser && callbacks.getCurrentUser({
        //   userId: userId
        // });
      },
      onTokenIncorrect: function () {
        console.log('token无效');
      },
      onError: function (errorCode) {
        var info = '';
        switch (errorCode) {
          case RongIMLib.ErrorCode.TIMEOUT:
            info = '超时';
            break;
          case RongIMLib.ConnectionState.UNACCEPTABLE_PAROTOCOL_VERSION:
            info = '不可接受的协议版本';
            break;
          case RongIMLib.ConnectionState.IDENTIFIER_REJECTED:
            info = 'appkey不正确';
            break;
          case RongIMLib.ConnectionState.SERVER_UNAVAILABLE:
            info = '服务器不可用';
            break;
        }
        console.log(errorCode);
      }
    });
  }

  /**
   * 重链
   */
  reconnect() {
    /**
     * 重链
     * @type {{onTokenIncorrect: onTokenIncorrect, onError: onError, onSuccess: onSuccess}}
     */
    var callback = {
      onSuccess: function (userId) {
        console.log("Reconnect successfully." + userId);
      },
      onTokenIncorrect: function () {
        console.log('token无效');
      },
      onError: function (errorCode) {
        console.log(errorcode);
      }
    };
    var config = {
      // 默认 false, true 启用自动重连，启用则为必选参数
      auto: true,
      // 网络嗅探地址 [http(s)://]cdn.ronghub.com/RongIMLib-2.2.6.min.js 可选
      url: 'cdn.ronghub.com/RongIMLib-2.2.6.min.js',
      // 重试频率 [100, 1000, 3000, 6000, 10000, 18000] 单位为毫秒，可选
      rate: [100, 1000, 3000, 6000, 10000]
    };
    RongIMClient.reconnect(callback, config);

  };


}

class ChatRoomModule {

  constructor() {
    this.sendMessageCallback = {
      onSuccess: function (message) {
        //message 为发送的消息对象并且包含服务器返回的消息唯一Id和发送消息时间戳
        console.log("Send successfully");
        iMManager.onReceiveMessageListener && iMManager.onReceiveMessageListener(message);
      },
      onError: function (errorCode, message) {
        var info = '';
        switch (errorCode) {
          case RongIMLib.ErrorCode.TIMEOUT:
            info = '超时';
            break;
          case RongIMLib.ErrorCode.UNKNOWN_ERROR:
            info = '未知错误';
            break;
          case RongIMLib.ErrorCode.REJECTED_BY_BLACKLIST:
            info = '在黑名单中，无法向对方发送消息';
            break;
          case RongIMLib.ErrorCode.NOT_IN_DISCUSSION:
            info = '不在讨论组中';
            break;
          case RongIMLib.ErrorCode.NOT_IN_GROUP:
            info = '不在群组中';
            break;
          case RongIMLib.ErrorCode.NOT_IN_CHATROOM:
            info = '不在聊天室中';
            break;
          default :
            info = message;
            break;
        }
        console.log('发送失败:' + info);
      }
    }
  }

  /**
   * 是否已加入此房间
   * @returns {*}
   */
  hasChatRoom(chatRoomId) {
    return this.chatRoomId === chatRoomId;
  }

  /**
   * 加入聊天室
   *
   * @param chatRoomId 聊天室id
   */
  joinChatRoom(chatRoomId) {
    if (!chatRoomId) {
      return;
    }
    console.log("加入房间");
    this.chatRoomId = chatRoomId;
    var count = 10;// 拉取最近聊天最多 50 条。
    RongIMClient.getInstance().joinChatRoom(chatRoomId, count, {
      onSuccess: function () {
        // 加入聊天室成功。
        console.log("加入聊天室成功");
      },
      onError: function (error) {
        // 加入聊天室失败
        console.log("加入聊天室失败");

      }
    });
  }

  /**
   * 退出聊天室
   * @param chatRoomId 聊天室id
   */
  exitChatRoom() {
    if (!this.chatRoomId) {
      return;
    }
    RongIMClient.getInstance().quitChatRoom(this.chatRoomId, {
      onSuccess: function () {
        // 退出聊天室成功。
        this.chatRoomId = null;
        console.log("退出聊天室成功");

      },
      onError: function (error) {
        // 退出聊天室失败。
        console.log("退出聊天室失败");

      }
    });
  }

  /**
   * 获取聊天室信息
   */
  getChatRoomInfo() {
    if (!this.chatRoomId) {
      return
    }
    var count = 10; // 获取聊天室人数 （范围 0-20 ）
    var order = RongIMLib.GetChatRoomType.REVERSE;// 排序方式。
    RongIMClient.getInstance().getChatRoomInfo(this.chatRoomId, count, order, {
      onSuccess: function (chatRoom) {
        // chatRoom => 聊天室信息。
        // chatRoom.userInfos => 返回聊天室成员。
        // chatRoom.userTotalNums => 当前聊天室总人数。
      },
      onError: function (error) {
        // 获取聊天室信息失败。
      }
    });
  }


  /**
   * 发送文本消息
   * @param targetId
   * @param content
   * @param extra
   */
  sendMessageFotText(content) {
    if (!this.chatRoomId) {
      return;
    }
    var msg = new RongIMLib.TextMessage({content: content, extra: ""});
    var conversationtype = RongIMLib.ConversationType.CHATROOM;
    RongIMClient.getInstance().sendMessage(conversationtype, this.chatRoomId, msg, this.sendMessageCallback);
  }

  /**
   * 发送图片 （TODO 未封装）
   */
  sendMessageForImage() {
    /*
   图片转为可以使用 HTML5 的 FileReader 或者 canvas 也可以上传到后台进行转换。

   注意事项：
       1、缩略图必须是 base64 码的 jpg 图。
       2、不带前缀。
       3、大小建议不超过 100 K。
 */
    var base64Str = "base64 格式缩略图";
    var imageUri = "图片 URL"; // 上传到自己服务器的 URL。
    var msg = new RongIMLib.ImageMessage({content: base64Str, imageUri: imageUri});
    var conversationtype = RongIMLib.ConversationType.PRIVATE; // 单聊,其他会话选择相应的消息类型即可。
    var targetId = "xxx"; // 目标 Id
    RongIMClient.getInstance().sendMessage(conversationtype, targetId, msg, this.sendMessageCallback);
  }

  /**
   *  发送红包
   */
  sendMessageForRedPacket(redPacketId, sendUserId, redPacketRemark, redPacketMoney, redPacketNum, redPacketType, redPacketCreateTime) {
    var conversationType = RongIMLib.ConversationType.CHATROOM; //单聊,其他会话选择相应的消息类型即可。
    var targetId = this.chatRoomId; // 想获取自己和谁的历史消息，targetId 赋值为对方的 Id。
    var msg = new RongIMClient.RegisterMessage.RedPacketMessage({
      id: redPacketId,
      userId: sendUserId,
      remark: redPacketRemark,
      money: redPacketMoney,
      num: redPacketNum,
      type: redPacketType,
      createTime: redPacketCreateTime
    });
    RongIMClient.getInstance().sendMessage(conversationType, targetId, msg, {
      onSuccess: function (message) {
      },
      onError: function (errorCode) {
      }
    });
  }


}

class EmojiModule {
  constructor() {
    // this.init();
  }

  init() {
    RongIMLib.RongIMEmoji.init();
    // // 表情信息可参考 http://unicode.org/emoji/charts/full-emoji-list.html
    // var config = {
    //   size: 24, // 大小, 默认 24, 建议18 - 58
    //   url: "//f2e.cn.ronghub.com/sdk/emoji-48.png", // Emoji 背景图片
    //   lang: "zh", // Emoji 对应名称语言, 默认 zh
    //   // 扩展表情
    //   extension: {
    //     dataSource: {
    //       u1F914: {
    //         en: "thinking face", // 英文名称
    //         zh: "思考", // 中文名称
    //         tag: "🤔", // 原生 Emoji
    //         position: "0 0" // 所在背景图位置坐标
    //       }
    //     },
    //     // 新增 Emoji 背景图 url
    //     url: "//cdn.ronghub.com/thinking-face.png"
    //   }
    // };
    // RongIMLib.RongIMEmoji.init(config);
  }

  /**
   * Emoji 转名称
   *  "😀😁测试 Emoji"=> "[笑嘻嘻][露齿而笑]测试 Emoji"
   * @param message
   */
  transformationForEmojiToText(emojiMessage) {
    return RongIMLib.RongIMEmoji.emojiToSymbol(emojiMessage);
  }

  /**
   * 名称转 Emoji
   * "[笑嘻嘻][露齿而笑]测试 Emoji"=> "😀😁测试 Emoji"
   * @param textMessage
   * @returns {*}
   */
  transformationForTextToEmoji(textMessage) {
    console.log("Emoji", RongIMLib.RongIMEmoji);
    return RongIMLib.RongIMEmoji.symbolToEmoji(textMessage);
  }

  /**
   * Emoji 转 HTML
   *
   *  "\uf600测试 Emoji"  => "<span class='rong-emoji-content' name='[笑嘻嘻]'>😀</span>测试 Emoji"
   */
  transformationForEmojiToHtml(emojiMessage) {
    return RongIMLib.RongIMEmoji.emojiToHTML(emojiMessage);
  }

  /**
   * 名称转 HTML
   * "[露齿而笑]测试 Emoji" // => "<span class='rong-emoji-content' name='[露齿而笑]'>😁</span>测试 Emoji"
   */
  transformationForTextToHtml(textMessage) {
    return RongIMLib.RongIMEmoji.symbolToHTML(textMessage);
  }

  /**
   * list => [
   {
             unicode: 'u1F600',
             emoji: "😀",
             node: span,
             symbol: "[笑嘻嘻]"
         },
   ...
   ]
   * @returns {Array|HTMLElement}
   */
  getEmojiList() {
    return RongIMLib.RongIMEmoji.list;
  }
  /**
   * 支持 ADM、CMD
   *
   * "\uf600测试 Emoji" => "😀测试 Emoji"
   * @param textMessage
   */
  transformationForTextToEmojiByRongEmoji(textMessage) {
    // return RongIMEmoji.symbolToEmoji(textMessage);

    // requirejs
    // require.config({
    //   paths: {
    //     'RongIMEmoji': '//cdn.ronghub.com/RongEmoji-2.2.7.min'
    //   }
    // });
    // require(['RongIMEmoji'], function (RongIMEmoji) {
    //   return RongIMEmoji.symbolToEmoji(textMessage);
    // });
  }
}

const iMManager = new IMManager();
export default iMManager;
export const chatRoomModule = new ChatRoomModule();
export const emojiModule = new EmojiModule();



