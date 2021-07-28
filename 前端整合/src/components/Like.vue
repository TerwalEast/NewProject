<template>
  <div class="like">
    <div class="title">
      <p></p>
      <hr>
      <h2>收藏夹</h2>
      <hr>
    </div>
    <div  v-for="(item,index) in list" class="content">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>{{item.title}}</span>
          <el-button style="float: right; padding: 3px 0" type="text" @click="cancel(index)">取消收藏</el-button>
        </div>
        <p>{{item.summary}}</p>
        <p>{{item.user_id}}{{item.create_time}}</p>
      </el-card>
    </div>
  </div>
</template>
<script>
  import axios from 'axios'
  export default {
    data()
    {
      return{
        list:[]
      }
    },
    methods:{
      getLike(){
        axios.get('http://localhost:8081/test2',{})
          .then(res=>{
            console.log(res.data)
            var jObject=res.data.valueOf()
            this.list=res.data
            console.log(this.list)
          }).catch(err=>{
          console.log(err);
        })
        console.log("你好");
      },
      cancel(index){
        console.log(index);
      }
    },
    created() {
      this.getLike();
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
