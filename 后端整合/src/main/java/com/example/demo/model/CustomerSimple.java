package com.example.demo.model;

import lombok.Data;

@Data
public class CustomerSimple {
        Integer id;
        String name;
        String avatarUrl;
        String backUrl;
        Byte type;
        Byte state;
    }
