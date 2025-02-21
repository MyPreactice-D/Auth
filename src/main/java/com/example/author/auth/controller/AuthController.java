package com.example.author.auth.controller;

import com.example.author.auth.dto.AuthSignupRequestDto;
import com.example.author.auth.dto.AuthLoginRequestDto;
import com.example.author.auth.dto.AuthLoginResponseDto;
import com.example.author.auth.service.AuthService;
import com.example.author.common.consts.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody AuthSignupRequestDto requestDto) {
        authService.signup(requestDto);
    }

    @PostMapping("/login")
    public void login(@RequestBody AuthLoginRequestDto requestDto, HttpServletRequest request) {
        AuthLoginResponseDto login = authService.login(requestDto);

        HttpSession session = request.getSession(); // 신규 세션 완성, 세션 쿠키 발급
        session.setAttribute(Const.LOGIN_MEMBER, login.getId());
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }
    }
}
