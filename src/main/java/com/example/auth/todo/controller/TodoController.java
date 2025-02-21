package com.example.auth.todo.controller;

import com.example.auth.todo.dto.*;
import com.example.auth.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/todoos")
    public ResponseEntity<TodoSaveResponseDto> save(@RequestBody TodoSaveRequestDto requestDto) {
        return ResponseEntity.ok(todoService.save(requestDto));
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
    public ResponseEntity<TodoUpdateResponseDto> update(@PathVariable Long id,
                                                        @RequestBody TodoUpdateRequestDto requestDto) {
        return ResponseEntity.ok(todoService.update(id, requestDto));
    }

    @DeleteMapping("/todos/{id}")
    public void delete(@PathVariable Long id) {

    }

}
