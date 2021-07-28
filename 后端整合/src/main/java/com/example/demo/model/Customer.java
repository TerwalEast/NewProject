package com.example.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class Customer {
    Integer id;
    String name;
    String password;
    String introduce;
    String email;
    String phone;
    String back_url;
    String avatar_url;
    Byte type;
    Byte state;

    public String getName() {return this.name;
    }
}
