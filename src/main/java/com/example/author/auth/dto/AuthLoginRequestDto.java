package com.example.author.auth.dto;

import lombok.Getter;

@Getter
public class AuthLoginRequestDto {
    private String email;
    private String password;
}
