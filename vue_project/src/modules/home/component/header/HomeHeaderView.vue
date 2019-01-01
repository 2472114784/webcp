<template>
  <div id="home_header" class="content global-layout-width">
    <div id="logo"></div>
    <v-header-items/>
  </div>
</template>
<script>
  import headerItems from './HomeHeaderItemsView'

  export default {
    name: "home_header",
    components: {
      'v-header-items': headerItems,
    }
  };
</script>
<style scoped>
  .content {
    display: flex;
    justify-content: flex-start;
  }

  #logo {
    width: 200pt;
    height: 50pt;
    background-color: aqua;
  }
</style>

