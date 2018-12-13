<template>
  <div id="app">
    <v-betballs/>
  </div>
</template>
<script>
import betball from '../components/Betball'
import betballs from '../components/Betballs'

export default {
  name:'home',
  data() {
    return {
      message: "hello vue!"
    };
  },

  methods: {
    
  },
  components:{
    'v-betballs':betballs,
  }
};
</script>
<style scoped>
