<template>
    <div id="bet_balls">
        <table >
        <tr>
            <th><v-betball p_value="2" key="1"/></th>
            <th><v-betball p_value="3" key="2"/></th>
            <th><v-betball p_value="虎" key="3"/></th>
            <th><v-betball p_value="龙" key="4"/></th>
        </tr>
        </table>
    </div>
</template>
<script>
import betball from "./Betball.vue";
export default {
    name:'betballs',
    data(){
        return{

        }
    },
components:{
    'v-betball':betball,
}
}
</script>
<style scoped>
    table{
        border-collapse:separate; 
        border-spacing:5px 0px;
    }
</style>


