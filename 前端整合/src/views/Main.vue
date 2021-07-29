<template>
  <div style="width: 100%">
    <div id="h" style="width: 87%;position: absolute;left: 5%">
      <el-card class="box-card">
      </el-card>
    </div>
      <div id="title" style="width: 85%;position: absolute;left: 5%;top: 15%">
        <img src="src/assets/title.jpg">
        <h1>欢迎来到WHuang Blog</h1>
        <p>创造自己的精彩</p>
      </div>

    <div id="show" style="width: 87%;position: absolute;left: 5%;top:30%">
      <el-row :gutter="20">
        <el-col :span="14">
          <div class="block">
            <el-carousel trigger="click" height="300px">
              <el-carousel-item>
                <el-card class="box-card" style="height: 300px">
                  <el-card class="box-card">
                    <div slot="header" class="clearfix" style="height: 30px">
                      <span><h4>文章：{{lists[1].title}}</h4></span>
                    </div>
                    <a>{{lists[1].createTime}}</a>
                  </el-card>
                  <el-card class="box-card">
                    <div slot="header" class="clearfix" style="height: 30px">
                      <span><h4>{{lists[0].title}}</h4></span>
                    </div>
                    <a>{{lists[0].createTime}}</a>
                  </el-card>
                </el-card>
              </el-carousel-item>
              <el-carousel-item>
                <el-card class="box-card" style="height: 300px">
                  <el-card class="box-card">
                    <div slot="header" class="clearfix" style="height: 30px">
                      <span><h4>文章：{{lists[2].title}}</h4></span>
                    </div>
                    <a>{{lists[2].createTime}}</a>
                  </el-card>
                  <el-card class="box-card">
                    <div slot="header" class="clearfix" style="height: 30px">
                      <span><h4>{{lists[3].title}}</h4></span>
                    </div>
                    <a>{{lists[3].createTime}}</a>
                  </el-card>
                </el-card>
              </el-carousel-item>
            </el-carousel>
          </div>
        </el-col>
        <el-col :span="10">
          <el-carousel height="300px" width="600px" indicatorPosition="outside" ref="carousel">
          <el-carousel-item class="carousel-item" v-for="item in imgs" v-bind:key="item.url">
            <img class="carousel-img" :src="item.url" style="width: 100%;height: 100%">
          </el-carousel-item>
          </el-carousel>
        </el-col>
      </el-row>
    </div>

    <div id="content" style="width:87%;position: absolute;left: 5%;top: 70%">
      <el-tabs :tab-position="tabPosition" style="height: 200px;">
        <el-tab-pane label="文章">
<!--          搜索框-->
          <div class="navbar">
            <label class="labal">文章搜索</label>
            <el-input class="input"
                      placeholder="请输入内容"
                      prefix-icon="el-icon-search"
                      v-model.trim="msg.search">
            </el-input>
            <el-button type="primary" icon="el-icon-search" @click="searchBtn">搜索</el-button>
          </div>
<!--          限制条件-->
          <el-collapse>
            <el-collapse-item title="搜索限制" name="1">
              <el-form ref="form" label-width="80px">
                <el-form-item label="作者">
                  <el-input v-model.trim="msg.user"></el-input>
                </el-form-item>
                <el-form-item label="标题">
                  <el-input v-model.trim="msg.title"></el-input>
                </el-form-item>
                <el-form-item label="发布时间">
                  <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择日期" v-model.trim="msg.date1" style="width: 100%;"></el-date-picker>
                  </el-col>
                  <el-col class="line" :span="2">-</el-col>
                  <el-col :span="11">
                    <el-date-picker type="date" placeholder="选择日期" v-model.trim="msg.date2" style="width: 100%;"></el-date-picker>
                  </el-col>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="onSubmit">搜索</el-button>
                </el-form-item>
              </el-form>
            </el-collapse-item>
          </el-collapse>
<!--          页码-->
          <div class="block">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="msg.current"
              :page-sizes="[5, 10, 20]"
              :page-size="msg.size"
              layout="total, sizes, prev, pager, next, jumper"
              :total="msg.total">
            </el-pagination>
          </div>
<!--          显示-->
          <div>
            <li v-if="lists.length===0">
              <el-empty description="没有搜到有效信息"></el-empty>
            </li>
            <el-col :span="8" v-for="(article,index) in lists" v-bind:key="index">
              <el-card :body-style="{ padding: '10px' }">
                <img v-if="article.backUrl" :src="article.backUrl" class="image"/>
                <img v-else src="https://pic2.zhimg.com/v2-0cc642bd1a977891d2c3407ff2f55619_r.jpg" class="image"/>
                <div style="padding: 14px;">
                  <span class="article-user" @click="gotoArticle(index)">文章标题：{{ article.title }}</span>
                  <div>
                    <span class="article-user" @click="gotoAuthor(index)">作者：{{ article.user }}</span>
                  </div>
                  <div class="bottom clearfix">
                    <time class="time">创造时间：{{ article.createTime }}</time>
                  </div>
                  <div>
                    <span>标签：</span>
                    <span v-if="article.labels.length===0"><el-tag>无标签</el-tag></span>
                    <span class="article-label" v-for="(label,i) in article.labels" v-bind:key="i">
                      <el-tag>{{ label }}</el-tag>
                    </span>
                  </div>
                </div>
              </el-card>
            </el-col>
          </div>
        </el-tab-pane>
        <el-tab-pane label="用户">
          <!--          搜索框-->
          <div class="navbar">
            <label class="labal">用户搜索</label>
            <el-input class="input"
                      placeholder="请输入内容"
                      prefix-icon="el-icon-search"
                      v-model.trim="user.search">
            </el-input>
            <el-button type="primary" icon="el-icon-search" @click="searchUser">搜索</el-button>
          </div>

          <!--          页码-->
          <div class="block">
            <el-pagination
              @size-change="handleSizeChangeUser"
              @current-change="handleCurrentChangeUser"
              :current-page="user.current"
              :page-sizes="[5, 10, 20]"
              :page-size="user.size"
              layout="total, sizes, prev, pager, next, jumper"
              :total="user.total">
            </el-pagination>
          </div>

          <div>
            <li v-if="lists.length===0">
              <el-empty description="没有搜到有效信息"></el-empty>
            </li>
            <!--        显示-->
            <el-col :span="6" v-for="(curruser,index) in userLists" v-bind:key="index">
              <span @click="gotoUser(index)">
                <el-card :body-style="{ padding: '10px' }">
                  <UserBrief :name="curruser.name" :avatarUrl="curruser.avatarUrl"/>
                </el-card>
              </span>
            </el-col>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>




  </div>
</template>
<script>

import utils from "../../utils/utils";
import UserBrief from "../components/UserBrief.vue";
export default {
  name: 'Main',
  components: { UserBrief },
  data()
  {
    return{
      tabPosition:'top',
      msg:{
        search:"",
        size:5,
        current:1,
        total:0,
        user:"",
        title:'',
        date1:'',
        date2:''
      },
      user:{
        search:"",
        size:5,
        current:1,
        total:0,
      },
      articleId:-1,
      userId:-1,
      lists:[],
      userLists:[],
      token:-1,
      img:'https://ftp.bmp.ovh/imgs/2021/07/9c8cad3dfe0c6949.jpg',
      imgs: [
        {url:'https://ftp.bmp.ovh/imgs/2021/07/7de54f28a4ed2d28.jpg', link: '/content1'},
        {url: 'https://ftp.bmp.ovh/imgs/2021/07/497366ec3551fe69.jpg', link: '/content2'},
        {url: 'https://ftp.bmp.ovh/imgs/2021/07/15a3127e814c4ced.png', link: '/content3'}
      ]
    }
  },
  created() {
    this.searchBtn();
    this.searchUser();
    this.token=localStorage.getItem("token");
  },
  methods:{
    //搜索
    searchBtn(){
      let success=(response)=>{
        this.lists=response.data.list;
        this.msg.total=response.data.code
      }
      utils.axiosMethod({
        method:"Post",
        url:"/article/search/",
        data:this.msg,
        callback:success
      })
    },
    //限制条件搜索
    onSubmit(){
      let success=(response)=>{
        this.lists=response.data.list;
        this.msg.total=response.data.code
      }
      utils.axiosMethod({
        method:"Post",
        url:"/article/limit/",
        data:this.msg,
        callback:success
      })
    },
    //前往文章页面
    gotoArticle(index){
      this.articleId=this.lists[index].id;
      this.$router.push('/information?artId='+this.articleId);
    },
    //前往作者页面
    gotoAuthor(index){
      this.userId=this.lists[index].userId;
      this.$router.push('/userInfo?userId='+this.userId);
    },
    //前往作者页面
    gotoUser(index){
      this.userId=this.userLists[index].id;
      this.$router.push('/userInfo?userId='+this.userId);
    },
    //文章页码改变
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.msg.size=val;
      this.msg.current=1;
      this.searchBtn();
    },
    //文章页码改变
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.msg.current=val;
      this.searchBtn();
    },
    //用户页码改变
    handleSizeChangeUser(val) {
      console.log(`每页 ${val} 条`);
      this.user.size=val;
      this.user.current=1;
      this.searchUser();
    },
    //用户页码改变
    handleCurrentChangeUser(val) {
      console.log(`当前页: ${val}`);
      this.user.current=val;
      this.searchUser();
    },
    //查询用户
    searchUser(){
      let success=(response)=>{
        this.userLists=response.data.list;
        this.user.total=response.data.code
      }
      utils.axiosMethod({
        method:"Post",
        url:"/user/search",
        data:this.user,
        callback:success
      })
    },
    //前往自己的主页
    gotoMe(){
      if(this.token!==null)
        this.$router.push('/person');
      else
        this.$message.error('请先登录！');
    }
  }
}
</script>
<style>
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}


/*







*/




.header-image-main-inside {
  position: relative;
  text-align: center;
}

/* Bottom left text */
.bottom-left {
  position: absolute;
  bottom: 8px;
  left: 16px;
}

/* Top left text */
.top-left {
  position: absolute;
  top: 8px;
  left: 16px;
}

/* Top right text */
.top-right {
  position: absolute;
  top: 8px;
  right: 16px;
}

/* Bottom right text */
.bottom-right {
  position: absolute;
  bottom: 8px;
  right: 16px;
}

/* Centered text */
.centered {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.entry-title:hover{
  color:rgb(48, 122, 187);
}

.header-image{
  max-height: 600px;
}

.footer{
  background-color: #366dc0;
}


@import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);

* {
  font-family: Roboto;
}

section {
  width: 100%;
  display: inline-block;
  background: #ccc;
  height: 60vh;
  text-align: center;
  font-size: 22px;
  font-weight: 700;
  text-decoration: underline;
}

.footer-distributed {
  background-color: #292c2f;
  box-shadow: 0 1px 1px 0 rgba(0, 0, 0, 0.12);
  box-sizing: border-box;
  width: 100%;
  text-align: left;
  font: normal 16px sans-serif;
  padding: 45px 50px;
}

.footer-distributed .footer-left p {
  color: #8f9296;
  font-size: 14px;
  margin: 0;
}
/* Footer links */

.footer-distributed p.footer-links {
  font-size: 18px;
  font-weight: bold;
  color: #ffffff;
  margin: 0 0 10px;
  padding: 0;
  transition: ease .25s;
}

.footer-distributed p.footer-links a {
  display: inline-block;
  line-height: 1.8;
  text-decoration: none;
  color: inherit;
  transition: ease .25s;
}

.footer-distributed .footer-links a:before {
  content: "·";
  font-size: 20px;
  left: 0;
  color: #fff;
  display: inline-block;
  padding-right: 5px;
}

.footer-distributed .footer-links .link-1:before {
  content: none;
}

.footer-distributed .footer-right {
  float: right;
  margin-top: 6px;
  max-width: 180px;
}

.footer-distributed .footer-right a {
  display: inline-block;
  width: 35px;
  height: 35px;
  background-color: #33383b;
  border-radius: 2px;
  font-size: 20px;
  color: #ffffff;
  text-align: center;
  line-height: 35px;
  margin-left: 3px;
  transition:all .25s;
}

.footer-distributed .footer-right a:hover{transform:scale(1.1); -webkit-transform:scale(1.1);}

.footer-distributed p.footer-links a:hover{text-decoration:underline;}

/* Media Queries */

@media (max-width: 600px) {
  .footer-distributed .footer-left, .footer-distributed .footer-right {
    text-align: center;
  }
  .footer-distributed .footer-right {
    float: none;
    margin: 0 auto 20px;
  }
  .footer-distributed .footer-left p.footer-links {
    line-height: 1.8;
  }
}

</style>
