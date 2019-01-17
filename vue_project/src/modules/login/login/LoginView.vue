<template>
  <el-dialog
    title="登录"
    :visible.sync="checkLogin"
    width="300pt"
    center>
    <div class="global-flex-column-content-center container">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
        <el-form-item prop="account" class="item-margin">
          <el-input
            placeholder="请输入账号"
            v-model="ruleForm.account"
            prefix-icon=""
            clearable>
          </el-input>
        </el-form-item>
        <el-form-item prop="password" class="item-margin">
          <el-input
            placeholder="请输入密码"
            v-model="ruleForm.password"
            clearable>
          </el-input>
        </el-form-item>
        <el-form-item class="global-flex-row-content-center">
          <el-button type="primary" @click="httpLogin">登录</el-button>
          <el-button type="primary" @click="restLogin">重置</el-button>
        </el-form-item>
      </el-form>
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
        checkLogin: true,
        ruleForm: {
          account: "",
          password: "",
        },
        rules: {
          account: [
            {required: true, message: '请输入账号', trigger: 'blur'},
            {min: 6, max: 18, message: '长度在 6 到 18 个字符', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
          ],
        },
      }
    },
    computed: {
      // ...mapState({
      //   user: state => state.UserStoreModule.state.user,
      // })

    },
    methods: {
      httpLogin: function () {
        console.log("准备请求登录");
        this.$refs['ruleForm'].validate((valid) => {
          if (valid) {
            this.$http(UserApi.login(this.ruleForm.account, this.ruleForm.password)).then(data => {
              //存储user信息
              console.log("登录成功", data);
              UserManager.setUser(data);
              console.log("get", UserManager.getUser());
            }).catch(data => {
              console.log("resson", data);
              Message.error(data.msg)
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      restLogin: function () {
        this.$refs['ruleForm'].resetFields();
      }
    },

  }
</script>

<style lang="less" scoped>

  .container {
    border: white solid 5pt;
  }

  .item-margin {
    margin-bottom: 20pt;
  }
</style>
