package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Memo {
    private Long id;
    private String username;
    private String contents;

    // MemoRequestDto -> Entity
    public Memo(MemoRequestDto requestDto){
        this.username  = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }

}