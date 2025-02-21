package com.example.author.todo.service;

import com.example.author.todo.dto.*;
import com.example.author.todo.entity.Todo;
import com.example.author.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoSaveResponseDto save(TodoSaveRequestDto requestDto) {
        Todo todo = new Todo(requestDto.getContent());
        todoRepository.save(todo);

        return new TodoSaveResponseDto(todo.getId(), todo.getContent());
    }

    @Transactional(readOnly = true)
    public List<TodoResponseDto> findAll() {
        List<Todo> todos = todoRepository.findAll();

        return todos.stream().map(todo -> new TodoResponseDto(todo.getId(), todo.getContent())).toList();
    }

    @Transactional(readOnly = true)
    public TodoResponseDto findById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("일정이 없습니다")
        );

        return new TodoResponseDto(todo.getId(), todo.getContent());
    }

    @Transactional
    public TodoUpdateResponseDto update(Long id, TodoUpdateRequestDto requestDto) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID가 없습니다.")
        );
        todo.update(requestDto.getContent());

        return new TodoUpdateResponseDto(todo.getId(), todo.getContent());
    }

    @Transactional
    public void deleteById(Long id) {
        if (!todoRepository.existsById(id)) {
             throw new IllegalArgumentException("ID가 없습니다");
        }
        todoRepository.deleteById(id);
    }


}
