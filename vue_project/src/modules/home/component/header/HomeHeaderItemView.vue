<template>
  <div id="header_item" @click="onClick">
    <p class="header_item_text">{{headerItem.title}}</p>
  </div>
</template>
<script>
  export default {
    name: "home_header_item",
    props: {
      headerItem: {
        title: String,
        type: 0,
      }
    },
    methods: {
      onClick: function () {
        this.$emit("itemclick", this.headerItem.type)
      }
    }
  }
</script>
<style scoped>
  #header_item {
    display: flex;
    height: 100pt;
    min-width: 100pt;
  }

  .header_item_text {
    margin: auto;
  }
</style>
