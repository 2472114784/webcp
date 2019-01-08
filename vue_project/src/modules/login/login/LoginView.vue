<template>
  <div class="global-flex-column-content-center container">
    <el-input
      placeholder="请输入账号"
      v-model="account"
      clearable>
    </el-input>
    <el-input
      placeholder="请输入密码"
      v-model="password"
      clearable>
    </el-input>
    <el-button type="primary" @click="httpLogin(account,password)">登录</el-button>
    <el-button type="primary" @click="seeUser">查看</el-button>
    <p>{{user}}</p>
  </div>
</template>

<script>
  import {mapState, mapActions} from 'vuex'
  import UserApi from '../../../common/http/api/UserApi'
  import {Message, Loading} from 'element-ui';
  import {KEY_USER} from '../../../common/localdata/KeyStorage'
  import {KEY_SET_USER} from '../../../common/store/modules/UserStoreModule'

  export default {
    name: "LoginView",
    data() {
      return {
        account: "123456",
        password: "123456",
        // user:this.$store.state.UserStoreModule.state.user
      }
    },
    computed: {
      // ...mapState({
      //   user: state => state.UserStoreModule.state.user,
      // })
      user: function () {
        return this.$store.state.UserStoreModule.user
      }
    },
    methods: {
      httpLogin: function (account, password) {
        console.log("准备请求登录");
        if (!account) {
          Message.error("请输入账号");
          return;
        }
        if (!password) {
          Message.error("请输入密码");
          return;
        }
        console.log("请求登录");

        this.$http(UserApi.login(account, password)).then(data => {
          //存储user信息
          console.log("登录成功", data);
          this.$session.set(KEY_USER, data);
          console.log("get", this.$session.get(KEY_USER));
          this.$store.commit('UserStoreModule/setUser', data);
        })
      },
      seeUser: function () {
        console.log("查看")
        console.log('user', this.user);
      }
    },

  }
</script>

<style lang="less" scoped>
  .container {
    width: 300pt;
    height: 300pt;
    border: white solid 5pt;
  }
</style>
