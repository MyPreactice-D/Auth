package com.example.author.todo.service;

import com.example.author.member.entity.Member;
import com.example.author.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    @Transactional
    public TodoSaveResponseDto save(Long memberId, TodoSaveRequestDto requestDto) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지않음")
        );

        Todo todo = new Todo(requestDto.getContent(), member);
        Todo save = todoRepository.save(todo);

        return new TodoSaveResponseDto(save.getId(), save.getContent(), member.getId(), member.getEmail());
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
    public TodoUpdateResponseDto update(Long memberId,Long id, TodoUpdateRequestDto requestDto) {

        if (!memberRepository.existsById(memberId)) {
            throw new IllegalArgumentException("그런 유저 없습니다.");
        }

        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("ID가 없습니다.")
        );
        todo.update(requestDto.getContent());

        return new TodoUpdateResponseDto(todo.getId(), todo.getContent());
    }

    @Transactional
    public void deleteById(Long memberId, Long id) {
        if (!memberRepository.existsById(memberId)) {
            throw new IllegalArgumentException("그런 유저 없습니다");
        }

        if (!todoRepository.existsById(id)) {
             throw new IllegalArgumentException("ID가 없습니다");
        }
        todoRepository.deleteById(id);
    }


}
