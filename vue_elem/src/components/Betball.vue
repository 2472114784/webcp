<template>
    <div id="bet_ball" class="bet_ball_shape bet_ball_size" @click="change" >
        <h class="bet_ball_text" >{{p_value}}</h>
    </div>
</template>

<script>
export default {
    name:'betball',
    props: ['p_value'],
    methods:{
        change:function (event) {
                this.p_value="12";
        }
    }
}
</script>
<style scoped>
    .bet_ball_shape{
        border: 2px solid #ff0000;
        border-radius: 50%;
        background-color: #ff0000;
    }
    .bet_ball_shape:hover{
        background-color: #ffff00;
    }
    .bet_ball_size{
        text-align: center;
		display: table-cell;
		vertical-align: middle;
		width:50px;
		height: 50px;
		background-color: red;

    }
    .bet_ball_text{
        color: white;
        font-size: 25px;
    }
</style>


