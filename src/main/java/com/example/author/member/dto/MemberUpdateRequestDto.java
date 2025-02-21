package com.example.author.member.dto;

import lombok.Getter;

@Getter
public class MemberUpdateRequestDto {
    private final String email;
    private final String password;

    public MemberUpdateRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
