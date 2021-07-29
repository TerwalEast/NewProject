<template>
  <div class="manage">
    <div class="title">
      <p></p>
      <hr>
      <h2>文章管理</h2>
      <hr>
    </div>
    <div  v-for="(item,index) in list"  class="content">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>{{item.title}}</span>
          <el-button style="float: right; padding: 3px 0" type="text" @click="edit(index)">编辑</el-button>
          <el-button style="float: right; padding: 3px 0" type="text" @click="del(index)">删除</el-button>
          <el-button style="float: right; padding: 3px 0" type="text" @click="reset(index)" v-if="item.state">设为私密</el-button>
          <el-button style="float: right; padding: 3px 0" type="text" @click="reset(index)" v-if="!item.state">设为公开</el-button>
        </div>
        <p>{{item.summary}}</p>
        <p>{{item.user_id}}{{item.create_time}}</p>
      </el-card>
    </div>
  </div>
</template>
<script scoped>
  import axios from 'axios'
  export default {
    data()
    {
      return{
        list:[]
      }
    },
    methods:{
      getList(){
        axios.get('http://localhost:8081/test3',{})
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
      edit(index){
        console.log(index);
      },
      del(index){
        console.log(index);
          this.$confirm('此操作将永久删除文章, 是否继续?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
      },
      reset(index){
        console.log(index);
        this.list[index].state=!this.list[index].state;
      }
    },
    created() {
      this.getList();
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
