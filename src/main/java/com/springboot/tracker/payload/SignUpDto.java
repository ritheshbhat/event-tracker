package com.springboot.tracker.payload;


import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String username;
    private String Email;
    private String password;
    private long utaId;
    private long zipcode;
    private String address;
    private long phNo;


}
