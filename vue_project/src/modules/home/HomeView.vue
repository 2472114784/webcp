<template>
  <div class="global-main-layout">
    <v-header/>
    <keep-alive>
      <router-view v-if="$route.meta.keepAlive"></router-view>
    </keep-alive>
    <v-footer/>
  </div>
</template>

<script>
  import HeaderView from './component/header/HomeHeaderView'
  import FooterView from './component/footer/HomeFooterView'

  export default {
    name: "Home",
    components: {
      'v-header': HeaderView,
      'v-footer': FooterView
    }
  }
</script>

<style scoped>

</style>
