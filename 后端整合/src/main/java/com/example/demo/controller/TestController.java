package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.CommentDao;
import com.example.demo.dao.LikeDao;
import com.example.demo.dao.OtherDao;
import com.example.demo.model.Article;
import com.example.demo.model.Comment;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    @Autowired
    CustomerService customerService;

    @Autowired
    ArticleDao articleDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    LikeDao likeDao;

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/test")
    public String test() throws JsonProcessingException {
        Customer customer=customerService.getById(1);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        jsonStr =  mapper.writeValueAsString(customer);
        return jsonStr;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/test2")
    public String test2() throws JsonProcessingException {
        List<Article> list=articleDao.getLikeByUser(1);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        jsonStr =  mapper.writeValueAsString(list);
        return jsonStr;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/test3")
    public String test3() throws JsonProcessingException {
        List<Article> list=articleDao.getArticleByUserId(1);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        jsonStr =  mapper.writeValueAsString(list);
        return jsonStr;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/test4")
    public String test4() throws JsonProcessingException {
        List<Comment> list=commentDao.getCommentUserPost(1);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        jsonStr =  mapper.writeValueAsString(list);
        return jsonStr;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/test5")
    public String test5() throws JsonProcessingException {
        List<Comment> list=commentDao.getCommentUserGet(1);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        jsonStr =  mapper.writeValueAsString(list);
        return jsonStr;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/test6")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/test7")
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String data = request.getReader().readLine();
        JSONObject json=JSONObject.parseObject(data);;
        Integer aid=Integer.parseInt(json.getString("id"));
        System.out.print("前端正在访问：" + data);
        likeDao.deleteLikeByUserArticle(aid,1);
        response.getWriter().write("接收到前端数据：" + aid);
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/test8")
    public String test8() throws JsonProcessingException {
        List<Comment> list=commentDao.getCommentUserPost(1);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        jsonStr =  mapper.writeValueAsString(list);
        return jsonStr;
    }
}
