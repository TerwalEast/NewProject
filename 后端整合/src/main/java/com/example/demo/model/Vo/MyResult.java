package com.example.demo.model.Vo;

import lombok.Data;

import java.util.List;

@Data
public class MyResult {
    private Integer code;
    private String msg;
    private List<?> list;
    private Object obj;
    public MyResult(){}
    public MyResult(String msg,Integer code){
        this.code=code;
        this.msg=msg;
    }
}
