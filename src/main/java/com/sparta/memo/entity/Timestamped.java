package com.sparta.memo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // 메인메소드가 있는 클래스에 @EnableJpaAuditing 어노테이션 꼭 달아줘야 함!
public abstract class Timestamped { // 이 클래스 자체를 객체로 생성할 일은 없기 때문에 abstract로 지정
    
    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;

    // DATE : 2024-12-25
    // TIME : 20:18:15
    // TIMESTAMP : 2024-12-25 20:18:15.89999

}

