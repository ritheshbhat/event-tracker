package com.sample.rest.Payload;

import lombok.Data;

@Data
public class SignupDto {
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String zipcode;
    private String state;
    private String country;
    //    private Timestamp created_on;
//    private Timestamp last_updated;
    private String utaId;
}
