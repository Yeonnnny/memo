package com.sparta.memo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api")
public class MemoController {

    private final Map<Long, Memo> memoList = new HashMap<>();

    /**
     * 메모 생성하기기
     * @param requestDto
     * @return
     */
    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        // RequestDto -> Entity
        Memo memo = new Memo(requestDto);

        // Memo Max ID Check
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet())+1 : 0;
        memo.setId(maxId);

        // DB 저장
        memoList.put(memo.getId(), memo);

        // Entity -> ResponseDto
        MemoResponseDto responseDto = new MemoResponseDto(memo);
    
        return responseDto;
    }

    /**
     * 메모 조회하기
     * @return
     */
    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        //Map To List
        List<MemoResponseDto> responseList = memoList.values().stream().map(MemoResponseDto::new).toList();
        return responseList;
    }
    

    
}
