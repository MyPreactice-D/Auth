package com.example.author.auth.service;

import com.example.author.auth.dto.AuthSignupRequestDto;
import com.example.author.auth.dto.LoginRequestDto;
import com.example.author.auth.dto.LoginResponseDto;
import com.example.author.member.dto.MemberSaveRequestDto;
import com.example.author.member.dto.MemberSaveResponseDto;
import com.example.author.member.entity.Member;
import com.example.author.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signup(AuthSignupRequestDto requestDto) {
        Member member = new Member(requestDto.getEmail(), requestDto.getPassword());
        memberRepository.save(member);
    }

    public LoginResponseDto login(LoginRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("회원정보가 없음")
        );
        return new LoginResponseDto(member.getId());
    }
}
