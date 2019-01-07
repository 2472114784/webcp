<template>
  <div>
    <el-input placeholder="请输入内容" v-model="account">
      <template slot="prepend">帐号:</template>
    </el-input>
    <el-input placeholder="请输入内容" v-model="password">
      <template slot="prepend">密码:</template>
    </el-input>
    <el-input placeholder="请输入内容" v-model="inviteCode">
      <template slot="prepend">邀请码:</template>
    </el-input>
    <el-button type="primary">注册</el-button>

  </div>
</template>

<script>
  import UserApi from '../../../common/http/api/UserApi'
  export default {
    name: "RegisterView",
    data() {
      return {
        account: String,
        password: String,
        inviteCode: String,
      }
    },
    methods: {
      httpRegister: function (account, password, inviteCode) {
        this.$http(UserApi.register(account, password, inviteCode)).then(data => {
          //TODO 记录userInfo
        })
      }
    }
  }
</script>

<style scoped>

</style>
