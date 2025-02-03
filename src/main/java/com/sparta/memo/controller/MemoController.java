package com.sparta.memo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




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
    
    /**
     * 메모 수정하기
     * @param id
     * @param requestDto
     * @return
     */
    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        if (memoList.containsKey(id)) {
            // 해당 메모 가져오기
            Memo memo = memoList.get(id);

            // 메모 수정
            memo.update(requestDto);
            return memo.getId();
        }else{
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    /**
     * 메모 삭제하기
     * @param id
     * @return
     */
    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id){
        // 해당 메모가 DB에 존재하는지 확인
        if (memoList.containsKey(id)) {
            // 해당 메모 삭제하기
            Memo memo = memoList.remove(id);
            return memo.getId();
        }else{
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
}