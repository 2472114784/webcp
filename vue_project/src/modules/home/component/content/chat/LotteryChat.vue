<template>
  <div class="chat-container">
    <div class="chat-main">
      <v-chat-message></v-chat-message>
      <v-chat-input></v-chat-input>
    </div>
  </div>
</template>

<script>
  import ChatMessageView from './ChatMessageView'
  import ChatInputView from './ChatInputView'

  export default {
    name: "LotteryChat",
    components: {
      'v-chat-message': ChatMessageView,
      'v-chat-input': ChatInputView,
    }
  }
</script>

<style scoped>
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
