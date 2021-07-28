package com.example.demo.model;

import lombok.Data;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Comment {
    private Integer id;
    private Integer userId;
    private Integer articleId;
    private String text;
    private String createTime;
}
