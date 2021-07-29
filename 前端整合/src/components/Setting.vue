<template xmlns:el-form="http://www.w3.org/1999/html">
  <div class="setting">
    <div class="content">
      <el-form>
        <el-form-item label="旧密码">
          <el-input id="old" v-model="theOld" type="password"></el-input>
        </el-form-item>
        {{theOld}}
        <el-form-item label="新密码">
          <el-input id="new" v-model="theNew" type="password"></el-input>
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input id="confirm" v-model="theConfirm" type="password"></el-input>
        </el-form-item>
        <el-button @click="confirm()">确认</el-button>
      </el-form>
    </div>
  </div>
</template>
<script>
  export default {
    data(){
      return{
        theOld:"",
        theNew:"",
        theConfirm:""
      }
    },
    methods:{
      confirm(){
        console.log("yes");
      }
    }
  }
</script>
