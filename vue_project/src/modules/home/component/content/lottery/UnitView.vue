<template>
  <el-dropdown split-button type="primary" @command="handleCommand">
    {{showText}}
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item command="1">元</el-dropdown-item>
      <el-dropdown-item command="2">角</el-dropdown-item>
      <el-dropdown-item command="3">分</el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
</template>

<script>
  export const TYPE_YUAN = 1;
  export const TYPE_JIAO = 2;
  export const TYPE_FEN = 3;
  export default {
    name: "UnitView",
    data() {
      return {
        selectedCommand: TYPE_YUAN,
      }
    },
    props: {
      unitChangeSelectedCallback: Function,

    },
    computed: {
      showText: function () {
        let result = "";
        switch (this.selectedCommand) {
          case TYPE_YUAN:
            result = "元";
            break;
          case TYPE_JIAO:
            result = "角";
            break;
          case TYPE_FEN:
            result = "分";
            break;
        }
        return result;
      }
    },

    methods: {
      handleCommand: function (command) {

        this.selectedCommand = parseInt(command);
        this.unitChangeSelectedCallback(this.selectedCommand);
      }
    }
  }
</script>

<style scoped>

</style>
