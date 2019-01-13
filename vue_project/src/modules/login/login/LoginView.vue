<template>
  <el-dialog
    title="登录"
    :visible.sync="checkLogin"
    width="300pt"
    center>
    <div class="global-flex-column-content-center container">
      <el-input
        placeholder="请输入账号"
        v-model="account"
        prefix-icon=""
        clearable>
      </el-input>
      <el-input
        placeholder="请输入密码"
        v-model="password"
        clearable>
      </el-input>
      <el-button type="primary" @click="httpLogin(account,password)">登录</el-button>
    </div>
  </el-dialog>

</template>

<script>
  import UserApi from '../../../common/http/api/UserApi'
  import {Message, Loading} from 'element-ui';
  import UserManager from '../../../common/dataManager/module/UserManager'

  export default {
    name: "LoginView",
    data() {
      return {
        account: "123456",
        password: "123456",
        checkLogin: false,
      }
    },
    computed: {
      // ...mapState({
      //   user: state => state.UserStoreModule.state.user,
      // })

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
          UserManager.setUser(data);
          console.log("get", UserManager.getUser());
        }).catch(data => {
          console.log("resson", data);
          Message.error(data.msg)
        })
      },
    },

  }
</script>

<style lang="less" scoped>
  .container {
    border: white solid 5pt;
  }

  .el-input {
    margin-bottom: 20pt;
  }
</style>
