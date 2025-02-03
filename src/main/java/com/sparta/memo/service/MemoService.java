package com.sparta.memo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;

@Service
public class MemoService {
    private final MemoRepository memoRepository;

    // public MemoService(ApplicationContext context){
    //     // 1. Bean '이름'으로 가져오기
    //     // MemoRepository memoRepository = (MemoRepository) context.getBean("memoRepository");
        
    //     // 2. Bean '클래스' 형식으로 가져오기
    //     MemoRepository memoRepository = context.getBean(MemoRepository.class);

    //     this.memoRepository = memoRepository;
    // }

    public MemoService(MemoRepository memoRepository){
        this.memoRepository = memoRepository;
    }


    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        // RequestDto -> Entity
        Memo memo = new Memo(requestDto);

        // DB 저장
        Memo saveMemo = memoRepository.save(memo);

        // Entity -> ResponseDto
        MemoResponseDto responseDto = new MemoResponseDto(saveMemo);
        return responseDto;
    }

    public List<MemoResponseDto> getMemos() {
        // DB 조회
        return memoRepository.findAll();
    }

    public Long updateMemo(Long id, MemoRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = memoRepository.findById(id);
        if (memo != null) {
            // memo 내용 수정
            memoRepository.update(id, requestDto);
            return id;
        }else{
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    public Long deleteMemo(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = memoRepository.findById(id);
        if (memo != null) {
            // memo 삭제
            memoRepository.delete(id);
            return id;
        }else{
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

}
