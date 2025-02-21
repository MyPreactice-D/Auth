package com.example.auth.member.controller;

import com.example.auth.member.dto.*;
import com.example.auth.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public ResponseEntity<MemberSaveResponseDto> save(@RequestBody MemberSaveRequestDto requestDto){
        return ResponseEntity.ok(memberService.save(requestDto));
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponseDto>> findAll(){
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(memberService.findById(id));
    }

    @PutMapping("/members/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody MemberUpdateRequestDto requestDto){
       memberService.update(id, requestDto);
    }

    @DeleteMapping("/members/{id}")
    public void delete(@PathVariable Long id){
        memberService.deleteById(id);
    }
}
