package com.example.demo.model.Vo;

import lombok.Data;

@Data
public class Search {
    private String search;
    private Integer size;
    private Integer current;
    private Byte state;
    public Search(){
        search="";
        size=5;
        current=1;
    }
}
