package com.sparta.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparta.memo.entity.Memo;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long>{
    
}
