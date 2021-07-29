<template>
  <div class="person">
    <div class="background">
      <img :src="backUrl" width="100%" height="100%" alt="" />
    </div>
    <div class="title">
      <el-card class="box-card">
          <el-avatar :size="100" v-if="avatarUrl" :src="avatarUrl"></el-avatar>
          <p><h3>{{name}}</h3>
          <p>{{introduce}}</p>
      </el-card>
    </div>
    <div class="content">
      <el-card class="box-card">
      <el-row class="tac" gutter="50">
        <el-col :span="4">
          <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose">
            <router-link to="/info">
              <el-menu-item index="1">
                <i class="el-icon-user"></i>
                <span slot="title">个人信息</span>
              </el-menu-item>
            </router-link>
            <router-link to="/setting">
              <el-menu-item index="2">
                <i class="el-icon-lock"></i>
                <span slot="title">修改密码</span>
              </el-menu-item>
            </router-link>
            <router-link to="/privacy">
              <el-menu-item index="3">
                <i class="el-icon-setting"></i>
                <span slot="title">隐私设置</span>
              </el-menu-item>
            </router-link>
            <router-link to="/like">
              <el-menu-item index="4">
                <i class="el-icon-star-off"></i>
                <span slot="title">我的收藏</span>
              </el-menu-item>
            </router-link>
            <el-submenu index="5">
              <template slot="title">
                <i class="el-icon-more-outline"></i>
                <span>内容管理</span>
              </template>
              <el-menu-item-group>
                <router-link to="/aManage">
                  <el-menu-item index="1-1">文章管理</el-menu-item>
                </router-link>
                <router-link to="/cManage">
                  <el-menu-item index="1-2">评论管理</el-menu-item>
                </router-link>
              </el-menu-item-group>
            </el-submenu>
          </el-menu>
        </el-col>
        <el-col :span="20">
          <router-view/>
        </el-col>
      </el-row>
      </el-card>
    </div>
  </div>
</template>
<script>
  import axios from "axios";

  export default {
    data(){
      return {
        message: null,
        avatarUrl:"",
        name:"",
        backUrl:"",
        introduce:""
      }
    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      getInfo(){
        //Edited part starts here!
        axios.get('http://localhost:8081/user/'+localStorage.getItem("Token"),{})
          .then(res=>{
            console.log(res.data)
            this.avatarUrl=res.data.obj.avatar_url
            this.name=res.data.obj.name
            if(res.data.obj.type==0)  this.type="普通用户"
            else this.type="管理员"
            console.log(res.data.obj.name)

          }).catch(err=>{
          console.log(err);
        })
        console.log("你好");
      }
    },
    created() {
      if(localStorage.getItem("token") == null)
      {
        this.$router.push("/login");
      }
      else
      {
        this.getInfo();
      }

    }

    //Edited part ends here!
  }
</script>
<style scoped>
  .background{
    width:100%;
    height:100%;
    z-index:-1;
    position: absolute;
  }
  .box-card{
    width: 90%;
    height: 80%;
  }
  .title{
    width: 100%;
    height: 300px;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .content{
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .el-menu-item{
    height: 60px;
  }

  a {
    text-decoration: none;
  }

  .router-link-active {
    text-decoration: none;
  }
</style>
