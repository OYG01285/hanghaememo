package com.sparta.hanghaememo.entity;

import com.sparta.hanghaememo.dto.BoardListRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@Entity(name = "board")
@NoArgsConstructor
public class Board extends Timestamped {

    //Entity는 DB에 데이터를 넣기 위해 존재하는 부분
    //DB에 데이터를 집어넣기 위해서는 각 테이블의 명 부분에 데이터가 들어가는 형식이기 때문에
    //아래 지정된 Board 처럼 테이블 명에 변수를 지정하여 넣는다
    //username 테이블에 user 데이터를 집어넣는 방식

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    public Board(BoardListRequestDto requestDto, List<Comment> comments) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
        this.username = requestDto.getUsername();
        this.comments = comments;
        }




}


