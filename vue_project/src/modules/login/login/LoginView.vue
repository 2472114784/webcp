<template>
  <div class="global-flex-column-content-center">
    <el-input
      placeholder="请输入内容"
      v-model="account"
      clearable>
    </el-input>
    <el-input
      placeholder="请输入内容"
      v-model="password"
      clearable>
    </el-input>
    <el-button type="primary" @click="httpLogin(account,password)">登录</el-button>

  </div>
</template>

<script>
  import UserApi from '../../../common/http/api/UserApi'
  export default {
    name: "LoginView",
    data() {
      return {
        account: String,
        password: String,
      }
    },
    methods: {
      httpLogin: function (account, password) {
        if (!account || !password) {
          return;
        }
        this.$http(UserApi.login(account, password)).then(data => {
          //TODO 存储user信息
        })
      }
    }
  }
</script>

<style scoped>

</style>
