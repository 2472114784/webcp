<script>
  import IMModule, {emojiModule} from "../../../../../common/im/IMModule";
  import UserManager from '../../../../../common/dataManager/module/UserManager'
  import {EVENT_ADD_EMOJI} from "../../../../../common/eventbus/EventBus";

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
      /**
       * 接受消息监听
       */
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
              this.messages.push(messageModule);
              this.$nextTick(() => {
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
      },
    },
    mounted() {
      this.setOnReceiveMessageListener();
      console.log("比较import", this.imModule === IMModule)

    }
  };
</script>

<template>

  <!--聊天列表-->
  <div class="message-container global-flex-column-content-center" v-scroll-bottom="messages">
    <div v-for="item in messages" class="message-item">
      <p><span class="time-text">{{ item.date | time }}</span>
      </p>
      <div class="global-flex-row-content-end message-item-content" v-show="item.self">
        <div class="send-text">{{ item.content }}</div>
        <img class="send-avatar" width="30" height="30" :src="item.user.img "/>
      </div>
      <div class="global-flex-row-content-start message-item-content" v-show="!item.self">
        <img class="receive-avatar" width="30" height="30" :src="item.user.img "/>
        <div class="receive-text">{{ item.content }}</div>
      </div>
    </div>
  </div>

</template>

<style lang="less" scoped>


  .message-container {
    height: 500px;
    padding: 10px 15px;
    overflow-y: scroll;
    background-color: #13CE66;
  }

  .message-item {
    margin-top: 10px;
  }

  .time-text {
    margin: auto;
    text-align: center;
    padding: 0 18px;
    color: #fff;
    font-size: 12px;
    border-radius: 2px;
    background-color: #dcdcdc;

  }

  .send-avatar {
    float: left;
    margin: 0 0 0 10px;
    border-radius: 3px;
  }

  .receive-avatar {
    float: left;
    margin: 0 10px 0 0;
    border-radius: 3px;
  }

  .message-item-content {
    margin-top: 10px;
  }

  .send-text,
  .receive-text {
    float: left;
    min-width: 50pt;
    max-width: 60%;
    max-height: 100pt;
    text-align: left;
    background-color: skyblue;
    border-bottom-color: skyblue;
    /*为了给after伪元素自动继承*/
    color: #fff;
    font-size: 14px;
    line-height: 18px;
    padding: 5px 12px 5px 12px;
    box-sizing: border-box;
    border-radius: 6px;
    position: relative;
    word-break: break-all;
  }

  .send-text::after {
    content: '';
    position: absolute;
    top: 20px;
    right: -5px;
    width: 10px;
    height: 10px;
    margin-top: -10px;
    background: inherit;
    /*自动继承父元素的背景*/
    transform: rotate(45deg);
  }

  /** 通过对小正方形旋转45度解决 **/
  .receive-text::before {
    content: '';
    position: absolute;
    top: 20px;
    left: -5px;
    width: 10px;
    height: 10px;
    margin-top: -10px;
    background: inherit;
    /*自动继承父元素的背景*/
    transform: rotate(45deg);
  }

</style>
