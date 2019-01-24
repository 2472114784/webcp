<script>
  import {chatRoomModule} from "../../../../../common/im/IMModule";
  import {EVENT_ADD_EMOJI} from "../../../../../common/eventbus/EventBus";
  import EmojiView from './EmojiView'

  export default {

    data() {
      return {
        content: ''
      };
    },
    methods: {
      /**
       * 监听发送物理键
       */
      onKeyup(e) {
        if (e.ctrlKey && e.keyCode === 13 && this.content.length) {
          chatRoomModule.sendMessageFotText(this.content);
          this.content = '';
        }
      },
      /**
       * 添加emoji
       * @param emoji
       */
      addEmojiText: function (emoji) {
        if (emoji) {
          this.content += emoji;
        }
      }
    },
    components: {
      'v-emoji': EmojiView,
    },
  };
</script>

<template>
  <div class="input-container">

    <!--emoji表情-->
    <div class="input-emoji-container">
      <v-emoji :onClickCallback="addEmojiText"/>
    </div>
    <div class="input-text-container">
      <textarea placeholder="按 Ctrl + Enter 发送" v-model="content" @keyup="onKeyup"></textarea>
    </div>

  </div>
</template>

<style lang="less" scoped>
  .input-container {
    width: 100%;
    position: absolute;
    background: #eee;
  }

  .input-emoji-container {
    height: 100px;
    top: -100%;
    left: 0;
    overflow: hidden;

  }

  .input-text-container {
    left: 0;
    top: 0;
    height: 100px;

    textarea {
      background-color: #20A0FF;
      height: 100%;
      width: 100%;
      border: none;
      outline: none;
      font-family: "Micrsofot Yahei";
      resize: none;
    }
  }
</style>
