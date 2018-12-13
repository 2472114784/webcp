<template>
    <div id="home_content">
        <v-banner></v-banner>
    <div @click="getServerTime">点我{{time}}</div>
    <input v-model="init_time"/>
    <div @click="setInitTime">设置初试时间</div>
    <div @click="registerCountDown">点我开始倒计时</div>
    <div>{{countDown}}</div>
    </div>
</template>
<script>
import banner from './Banner'
export default{
    data(){
        return{
            time:"00:00:00",
            init_time:0,
            countDown:0,
        }
    },
    components:{
        'v-banner':banner,
    },
    methods:{
        getServerTime:function(){
            this.time=this.$timeManager.getServerTime();
        },
        setInitTime:function(){
            this.$timeManager.setServerTime(this.init_time);
        },
        registerCountDown:function(){
            console.log('点击开始倒计时')
            this.countDown++;
            this.$countDownManager.register(function() {
            })
        }
    }
}
</script>
<style scoped>
img{
    width: 100px;
    height: 100px;
}
</style>

