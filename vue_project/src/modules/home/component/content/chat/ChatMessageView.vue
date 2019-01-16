<script>
  import IMModule, {emojiModule} from "../../../../../common/im/IMModule";
  import UserManager from '../../../../../common/dataManager/module/UserManager'


  export default {
    data() {
      return {
        messages: [],
      }
    },
    props: {
      imModule: {}
    },
    filters: {
      // 将日期过滤为 hour:minutes
      time(date) {
        if (typeof date === 'string') {
          date = new Date(date);
        }
        return date.getHours() + ':' + date.getMinutes();
      }
    },
    directives: {
      // 发送消息后滚动到底部
      'scroll-bottom'() {
        console.log('scroll-bottom', this);
        // this.$nextTick(() => {
        //   this.el.scrollTop = this.el.scrollHeight - this.el.clientHeight;
        // });
      }
    },
    methods: {
      setOnReceiveMessageListener: function () {
        console.log("listener:");

        IMModule.setOnReceiveMessageListener(message => {
          console.log("listener receive:", message);

          switch (message.messageType) {
            case RongIMClient.MessageType.TextMessage:
              // message.content.content => 消息内容
              let messageModule = {};
              messageModule.user = {
                name: "huahua",
                img: "dist/images/1.jpg",
              };
              messageModule.date = new Date();
              messageModule.content = emojiModule.transformationForTextToEmoji(message.content.content);
              messageModule.self = message.senderUserId == UserManager.getUser().id;
              console.log('sendid', message.senderUserId, typeof message.senderUserId);
              console.log('userid', UserManager.getUser().id, typeof UserManager.getUser().id);
              console.log('is===', message.senderUserId === UserManager.getUser().id);
              this.messages.push(messageModule);
              this.$nextTick(() => {
                console.log("el", this);
                this.$el.scrollTop = this.$el.scrollHeight - 500;
              });
              break;
            case RongIMClient.MessageType.VoiceMessage:
              // 对声音进行预加载
              // message.content.content 格式为 AMR 格式的 base64 码
              break;
            case RongIMClient.MessageType.ImageMessage:
              // message.content.content => 图片缩略图 base64。
              // message.content.imageUri => 原图 URL。
              break;
          }
        });
      }
    },
    mounted() {
      this.setOnReceiveMessageListener();
      console.log("比较import", this.imModule === IMModule)

    }
  };
</script>

<template>
  <div class="message" v-scroll-bottom="messages">
    <ul v-if="messages">
      <li v-for="item in messages">
        <p class="time">
          <span>{{ item.date | time }}</span>
        </p>
        <div class="chat-main" :class="[{ self: item.self },{'not-self':!item.self}]">
          <img class="avatar" width="30" height="30" :src="item.user.img "/>
          <div class="text">{{ item.content }}</div>
        </div>
      </li>
    </ul>
  </div>
</template>

<style lang="less" scoped>
  .message {
    height: 500px;
    padding: 10px 15px;
    overflow-y: scroll;

    li {
      margin-bottom: 15px;
    }

    .time {
      margin: 7px 0;
      text-align: center;

      > span {
        display: inline-block;
        padding: 0 18px;
        font-size: 12px;
        color: #fff;
        border-radius: 2px;
        background-color: #dcdcdc;
      }
    }

    .avatar {
      float: left;
      margin: 0 10px 0 0;
      border-radius: 3px;
    }

    .text {
      display: inline-block;
      position: relative;
      padding: 0 10px;
      max-width: ~'calc(100% - 40px)';
      min-height: 30px;
      line-height: 2.5;
      font-size: 12px;
      text-align: left;
      word-break: break-all;
      background-color: #fafafa;
      border-radius: 4px;

      &:before {
        content: " ";
        position: absolute;
        top: 9px;
        right: 100%;
        border: 6px solid transparent;
        border-right-color: #fafafa;
      }
    }

    .self {
      text-align: right;

      .avatar {
        float: right;
        margin: 0 0 0 10px;
      }

      .text {
        background-color: #b2e281;

        &:before {
          right: inherit;
          left: 100%;
          border-right-color: transparent;
          border-left-color: #b2e281;
        }
      }
    }

    .not-self {
      text-align: left;

      .avatar {
        float: left;
        margin: 0 10px 0 0;
      }

      .text {
        background-color: #13CE66;

        &:before {
          right: 100%;
          left: inherit;
          border-right-color: #13CE66;
          border-left-color: transparent;
        }
      }
    }
  }
</style>
