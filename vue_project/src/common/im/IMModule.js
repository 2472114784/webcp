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
  init(callbacks) {

    //初始化IM
    RongIMLib.RongIMClient.init(appKey);
    //语音播放初始化
    // RongIMLib.RongIMVoice.init();

    var instance = RongIMClient.getInstance();

    // 设置连接监听状态 （ status 标识当前连接状态 ）
    // 连接状态监听器
    RongIMClient.setConnectionStatusListener({
      onChanged: function (status) {
        switch (status) {
          case RongIMLib.ConnectionStatus.CONNECTED:
            console.log('链接成功');
            callbacks.getInstance && callbacks.getInstance(instance);
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
          default:
          // do something...
        }
        callbacks.receiveNewMessage && callbacks.receiveNewMessage(message);
      }
    });
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
        callbacks.getCurrentUser && callbacks.getCurrentUser({
          userId: userId
        });
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


export default new IMManager();
