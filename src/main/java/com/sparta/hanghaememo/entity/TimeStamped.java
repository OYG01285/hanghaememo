package com.sparta.hanghaememo.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AutoCloseable.class)
public abstract class TimeStamped {

    //게시글 생성 및 수정 시 시간을 저장하는 entity
    @CreatedDate
    private LocalDateTime creatAt;

    @LastModifiedDate
    private  LocalDateTime modifiedAt;
}
