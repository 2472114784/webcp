<template>
  <el-dialog
    title="注册"
    :visible.sync="checkLogin"
    width="300pt"
    center>
    <div class="global-flex-column-content-center container">
      <el-form :model="registerForm" :rules="registerRule" ref="ruleForm">
        <el-form-item prop="account" class="item-margin">
          <el-input
            placeholder="请输入账号"
            v-model="registerForm.account"
            prefix-icon=""
            clearable>
          </el-input>
        </el-form-item>
        <el-form-item prop="password" class="item-margin">
          <el-input
            placeholder="请输入密码"
            v-model="registerForm.password"
            clearable>
          </el-input>
        </el-form-item>
        <el-form-item class="item-margin">
          <el-input
            placeholder="请输入邀请码(选填)"
            v-model="registerForm.inviteCode"
            clearable>
          </el-input>
        </el-form-item>
        <el-form-item class="global-flex-row-content-center">
          <el-button type="primary" @click="httpRegister">注册</el-button>
          <!--<el-button type="primary" @click="restLogin">重置</el-button>-->
        </el-form-item>
      </el-form>
    </div>
  </el-dialog>
</template>

<script>
  import UserApi from '../../../common/http/api/UserApi'
  import {Message, Loading} from 'element-ui';
  import UserManager from '../../../common/dataManager/module/UserManager'
  import StringCheck from '../../../utils/js/string'

  export default {
    name: "RegisterView",
    data() {
      let checkPassword = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (StringCheck.checkPwd(value) < 2) {
            callback(new Error("密码等级不够"));
          } else {
            this.$refs.ruleForm2.validateField('password');
            callback();
          }
        }
      }
      return {
        checkLogin: true,
        registerForm: {
          account: "",
          password: "",
          inviteCode: "",
        },
        registerRule: {
          account: [
            {required: true, message: '请输入账号', trigger: 'blur'},
            {min: 6, max: 18, message: '长度在 6 到 18 个字符', trigger: 'blur'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
            {validator: checkPassword, trigger: 'blur'}
          ],
        },


      }
    },
    computed: {},
    methods: {
      httpRegister: function () {
        this.$refs['ruleForm'].validate((valid) => {
          if (valid) {
            this.$http(UserApi.register(this.registerForm.account, this.registerForm.password, this.registerForm.inviteCode)).then(data => {
              //存储user信息
              console.log("注册成功", data);
              UserManager.setUser(data);
              console.log("get", UserManager.getUser());
            }).catch(data => {
              console.log("error", data);
              Message.error(data.msg)
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });

      }
    }
  }
</script>

<style scoped>
  .container {
    border: white solid 5pt;
  }

  .item-margin {
    margin-bottom: 20pt;
  }
</style>
