<template>
  <div class="manage">
    <div class="manage">
      <div class="title">
        <p></p>
        <hr>
        <h2>评论管理</h2>
        <hr>
      </div>
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="收到评论" name="first">
          <div  v-for="(item,index) in getList" class="content">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <el-button style="float: right; padding: 3px 0" type="text" @click="del(index)">删除</el-button>
                <el-button style="float: right; padding: 3px 0" type="text" @click="turn(index)">回复</el-button>
              </div>
              <p>{{item.userId}}于{{item.article_id}}说：{{item.text}}</p>
              <p>{{item.create_time}}</p>
            </el-card>
          </div>
        </el-tab-pane>
        <el-tab-pane label="我发出的" name="second">
          <div  v-for="(item,index) in postList" class="content">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <el-button style="float: right; padding: 3px 0" type="text" @click="del(index)">删除</el-button>
                <el-button style="float: right; padding: 3px 0" type="text" @click="turn(index)">回复</el-button>
              </div>
              <p>{{item.userId}}于{{item.article_id}}说：{{item.text}}</p>
              <p>{{item.create_time}}</p>
            </el-card>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>
<script scoped>
  import axios from 'axios'
  export default {
    data()
    {
      return{
        getList:[],
        postList:[],
        activeName: 'second'
      }
    },
    methods:{
      getGet(){
        axios.get('http://localhost:8081/test4',{})
          .then(res=>{
            console.log(res.data)
            var jObject=res.data.valueOf()
            this.getList=res.data
            console.log(this.getList)
          }).catch(err=>{
          console.log(err);
        })
        console.log("你好");
      },
      getPost(){
        axios.get('http://localhost:8081/test8',{})
          .then(res=>{
            console.log(res.data)
            var jObject=res.data.valueOf()
            this.postList=res.data
            console.log(this.postList)
          }).catch(err=>{
          console.log(err);
        })
        console.log("你好");
      },
      handleClick(tab, event) {
        console.log(tab, event);
      },
      del(index){
        console.log(index);
      },
      turn(index){
        console.log(index);
      }
    },
    created() {
      this.getGet();
      this.getPost();
    }
  }
</script>

<style>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .title{
    width: 90%;
    text-align: center;
  }
  .box-card {
    width: 90%;
  }
</style>
