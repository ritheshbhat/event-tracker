package com.sample.rest.Payload;

import lombok.Data;

@Data
public class LoginDto {
    private String utaIdOrEmail;
    private String password;
}
