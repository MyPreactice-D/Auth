package com.example.author.todo.controller;

import com.example.author.common.consts.Const;
import com.example.author.todo.dto.*;
import com.example.author.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<TodoSaveResponseDto> save(@SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId, @RequestBody TodoSaveRequestDto requestDto) {
        return ResponseEntity.ok(todoService.save(memberId, requestDto));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoResponseDto>> findAll(){
        return ResponseEntity.ok(todoService.findAll());
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<TodoResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.findById(id));
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<TodoUpdateResponseDto> update(@SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId
                                                        ,@PathVariable Long id,
                                                        @RequestBody TodoUpdateRequestDto requestDto) {
        return ResponseEntity.ok(todoService.update(memberId, id, requestDto));
    }

    @DeleteMapping("/todos/{id}")
    public void delete(@SessionAttribute(name = Const.LOGIN_MEMBER) Long MemberId,
                       @PathVariable Long id) {
        todoService.deleteById(MemberId, id);
    }

}
