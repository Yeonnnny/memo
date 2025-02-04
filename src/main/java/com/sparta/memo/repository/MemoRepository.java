package com.sparta.memo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sparta.memo.entity.Memo;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long>{
    
    List<Memo> findAllByOrderByModifiedAtDesc();
}
