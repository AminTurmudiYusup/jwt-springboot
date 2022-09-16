package com.example.springjwt.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AuthenticationRequest {
    @NotNull
    @Size(max = 255)
    private String userName;
    @NotNull
    @Size(max = 255)
    private String password;
}
