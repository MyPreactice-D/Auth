package com.example.auth.member.service;

import com.example.auth.member.dto.*;
import com.example.auth.member.entity.Member;
import com.example.auth.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberSaveResponseDto save(MemberSaveRequestDto requestDto) {
        Member member = new Member(requestDto.getEmail(), requestDto.getPassword());
        memberRepository.save(member);

        return new MemberSaveResponseDto(member.getId(), member.getEmail());
    }

    @Transactional(readOnly = true)
    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();
//        List<MemberResponseDto> dtos = new ArrayList<>();
//        for (Member member : members) {
//            dtos.add(new MemberResponseDto(member.getId(), member.getEmail()));
//        }
//        return dtos;
         return members.stream().map(member -> new MemberResponseDto(member.getId(), member.getEmail())).toList();
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("사용자가 없음")
        );

        return new MemberResponseDto(member.getId(), member.getEmail());
    }

    @Transactional
    public void update(Long id, MemberUpdateRequestDto requestDto) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("사용자가 없음")
        );
        member.update(requestDto.getEmail(), requestDto.getPassword());
    }

    @Transactional
    public void deleteById(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new IllegalArgumentException("삭제 불가능");
        }
        memberRepository.deleteById(id);
    }
}
