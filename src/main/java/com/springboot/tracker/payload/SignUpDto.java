package com.springboot.tracker.payload;


import lombok.Data;

import java.util.List;

@Data
public class SignUpDto {
    private String name;
    private String username;
    private String Email;
    private String password;
    private long utaId;
    private long zipcode;
    private String address;
    private List<Long> departments;

    private long phNo;


}

