<template>
  <div>
    <p @click="joinRoom">加入房间</p>
    <p @click="sendMsg">发送消息</p>

    <div class="chat-container">
      <div class="chat-main">
        <v-chat-message></v-chat-message>
        <v-chat-input></v-chat-input>
      </div>
    </div>
  </div>
</template>

<script>
  import ChatMessageView from './ChatMessageView'
  import ChatInputView from './ChatInputView'
  import IMModule, {chatRoomModule} from "../../../../../common/im/IMModule";


  export default {
    name: "LotteryChat",
    components: {
      'v-chat-message': ChatMessageView,
      'v-chat-input': ChatInputView,
    },
    methods: {
      initChat: function () {
        IMModule.init();
      },
      joinRoom: function () {
        chatRoomModule.joinChatRoom(111);
      },
      sendMsg: function () {
        chatRoomModule.sendMessageFotText("hello");
      }
    },
    mounted() {
      this.initChat();
    }
  }
</script>

<style lang="less" scoped>
  .chat-container {
    margin: 20px auto;
    width: 800px;
    height: 600px;

    overflow: hidden;
    border-radius: 3px;
  }

  .chat-main {
    position: relative;
    overflow: hidden;
    background-color: #eee;
  }
</style>
