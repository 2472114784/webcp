<template>
  <el-input-number v-model="num" @change="change" :min="1" label="描述文字"></el-input-number>
</template>

<script>
  export default {
    name: "NumView",
    data() {
      return {
        num: 1,
      }
    },
    props: {
      numChangeCallback: Function,
    },
    methods: {
      change: function (value) {
        this.numChangeCallback(value);
      }
    }
  }
</script>

<style scoped>

</style>
