<template>
  <div id="app">
    <v-home></v-home>
  </div>
</template>

<script>
import home from './pages/Home';
export default {
  name: 'app',
  components:{
    'v-home':home
  }
}
</script>

<style>
#app {
  
}
</style>
