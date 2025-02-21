package com.example.author.todo.dto;

import lombok.Getter;

@Getter
public class TodoSaveResponseDto {
    private final Long id;
    private final String content;

    public TodoSaveResponseDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
