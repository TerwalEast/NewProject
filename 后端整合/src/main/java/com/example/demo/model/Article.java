package com.example.demo.model;

import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class Article {
    Integer id;
    Integer user_id;
    String title;
    String content;
    String summary;
    Date create_Time;
    Date modify_Time;



    Byte state;
    //-----------------
    List<String> labels;
    List<Integer> labelIds;
    String stateStr;
    LocalDateTime createTime;
    LocalDateTime modifyTime;
    Integer userId;
    public void newArticle(){
        this.setModifyTime(LocalDateTime.now());
        this.setCreateTime(LocalDateTime.now());
    }


}
