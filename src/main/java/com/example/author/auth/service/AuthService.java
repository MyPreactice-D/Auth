package com.example.author.auth.service;

import com.example.author.auth.dto.AuthSignupRequestDto;
import com.example.author.auth.dto.AuthLoginRequestDto;
import com.example.author.auth.dto.AuthLoginResponseDto;
import com.example.author.member.entity.Member;
import com.example.author.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signup(AuthSignupRequestDto requestDto) {
        Member member = new Member(requestDto.getEmail(), requestDto.getPassword());
        memberRepository.save(member);
    }

    @Transactional
    public AuthLoginResponseDto login(AuthLoginRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("회원정보가 없음")
        );

        return new AuthLoginResponseDto(member.getId());
    }
}
