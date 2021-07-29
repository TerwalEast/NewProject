<template>
  <div class="info">
    <el-form>
      <el-form-item>姓名:{{name}}</el-form-item>
      <el-form-item>邮箱:{{email}}</el-form-item>
      <el-form-item>电话:{{phone}}</el-form-item>
      <el-form-item>权限:{{type}}</el-form-item>
    </el-form>
  </div>
</template>
<script>
  import axios from 'axios'
  export default {
    data()
    {
        return{
          name:"",
          email:"",
          phone:"",
          type:"",
          avatarUrl:""
        }
    },
    methods:{
      getInfo(){
        axios.get('http://localhost:8081/test',{})
          .then(res=>{
          console.log(res.data)
          var jObject=res.data.valueOf()
          this.name=jObject.name
          this.email=jObject.email
          this.phone=jObject.phone
          this.avatarUrl=jObject.avatar_url
          if(jObject.type==0)  this.type="普通用户"
          else this.type="管理员"
          console.log(jObject.name)
        }).catch(err=>{
          console.log(err);
        })
        console.log("你好");
      }
    },
    created() {
      this.getInfo();
    }
  }
</script>
<style scoped>
  .info{
    position: absolute;
    top:10%;
    left: 50%;
  }
</style>
