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
    <el-button type="primary" @click="httpRegister(account,password,inviteCode)">注册</el-button>

  </div>
</template>

<script>
  import UserApi from '../../../common/http/api/UserApi'
  import {KEY_USER} from '../../../common/localdata/KeyStorage'
  import {Message, Loading} from 'element-ui';

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
        if (!account) {
          Message.error("请输入账号");
          return;
        }
        if (!password) {
          Message.error("请输入密码");
          return;
        }

        this.$http(UserApi.register(account, password, inviteCode)).then(data => {
          // 记录userInfo
          this.$session.set(KEY_USER, data);
        })
      }
    }
  }
</script>

<style scoped>

</style>
