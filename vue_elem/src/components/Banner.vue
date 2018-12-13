<template>
  <div id="banner">
    <el-carousel indicator-position="outside">
      <el-carousel-item v-for="item in data_banner.data" :key="item.bannerUrl">
        <img :src="item.bannerUrl" :alt="item.bannerUrl">
      </el-carousel-item>
    </el-carousel>
    <div @click="changeData">切换数据</div>
  </div>
</template>
<script>
export default {
  name: "banner",
  data() {
    return {
      data_banner: []
    };
  },
  methods: {
    http_banner: function() {
      this.$server.exam().then(data=>this.data_banner=data)
    },
    changeData: function(){
      this.data_banner.data=[{bannerUrl:'wwww'},{bannerUrl:'wwww'},{bannerUrl:'wwww'},{bannerUrl:'wwww'}];
    }
  },
  mounted() {
    this.http_banner();
  }
};
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
img{
  width: 100%;
  height: 100%;
}
</style>