<template>
  <div class="emoji-container">
    <p class="emoji-text" v-for="(item,index) in emojiList" :key="index" @click="onClickCallback(item.emoji)">
      {{item.emoji}}</p>
  </div>
</template>

<script>
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
  import {emojiModule} from '../../../../../common/im/IMModule'

  export default {
    name: "EmojiView",
    data() {
      return {
        emojiList: [],
      }
    },
    props: {
      onClickCallback: null
    },
    mounted() {
      this.emojiList = emojiModule.getEmojiList();
    }
  }
</script>

<style scoped>
  .emoji-container {
    display: flex;
    padding: 10px;
    height: 100px;
    justify-content: flex-start;
    flex-flow: column wrap;
    align-content: flex-start;
    overflow-y: scroll;

  }

  .emoji-text {
    font-size: 20px;
    margin-top: 0px;
    margin-bottom: 0px;
  }
</style>
