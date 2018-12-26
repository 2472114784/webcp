<template>
  <div class="hello">
    <div>我是demo页面</div>
    <div class="globalTest bg-blue">
       {{msg}}
    </div>
    <br>
    当前 count : {{count}}
    <br><br>
    测试AXIOS :
    <el-button @click="testGET">get</el-button>
    <el-button @click="testPOST">post</el-button>
    <el-button @click="testERROR">error</el-button>
    <br><br>
    <div>util工具包测试 ：{{today}}</div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import dateUtil from '../../../common/util/dateUtil'
  import {server} from '../../../common/http-service.js'
  export default {
    computed:mapGetters({
      count:'getCount'
    }),
    data() {
      return {
        msg: '全局样式测试',
        today:''
      }
    },
    mounted(){
      this.today = dateUtil.getToday();
    },
    methods:{
      testGET(){
        console.log("请求网络了")
        server.exam().then(data => console.log("获得数据", data));
      },
      testPOST(){
        this.$store.dispatch('deleteCount');
      },
      testERROR(){
        this.$store.dispatch('errorTest');
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="less">
  .hello {
    .globalTest {
      width: 200px;
      height: 200px;
      line-height: 200px;
      margin: 0 auto;
      color: @colorWhite;
    }
  }
</style>
