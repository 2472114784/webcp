<template>
  <div>
    <p @click="joinRoom">加入房间</p>
    <p @click="sendMsg">发送消息</p>
    <div class=" chat-container">
      <div class="chat-message-container">
        <v-chat-message :imModule="imModule"/>
      </div>
      <div class="chat-input-container">
        <v-chat-input/>
      </div>
    </div>
  </div>
</template>

<script>
  import ChatMessageView from './ChatMessageView'
  import ChatInputView from './ChatInputView'
  import IMModule, {chatRoomModule, emojiModule} from "../../../../../common/im/IMModule";

  export default {
    name: "LotteryChat",
    data() {
      return {
        imModule: IMModule,
      }
    },
    components: {
      'v-chat-message': ChatMessageView,
      'v-chat-input': ChatInputView,
    },
    methods: {
      initChat: function () {
        IMModule.init();
        emojiModule.init();
      },
      joinRoom: function () {
        chatRoomModule.joinChatRoom('room01');
      },
      sendMsg: function () {
        chatRoomModule.sendMessageFotText("hello");
      },

    },
    mounted() {
      this.initChat();

    }
  }
</script>

<style lang="less" scoped>
  .chat-container {
    width: 300px;
    height: 400px;
    position: absolute;
  }

  .chat-message-container {
    left: 0;
    top: 0;
    height: 70%;
    overflow: hidden;

  }

  .chat-input-container {
    left: 0;
    top: 70%;
    height: 30%;
  }


</style>
