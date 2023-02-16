package com.sparta.hanghaememo.entity;

import com.sparta.hanghaememo.dto.BoardListRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
public class Board extends TimeStamped {

    //Entity는 DB에 데이터를 넣기 위해 존재하는 부분
    //DB에 데이터를 집어넣기 위해서는 각 테이블의 명 부분에 데이터가 들어가는 형식이기 때문에
    //아래 지정된 Board 처럼 테이블 명에 변수를 지정하여 넣는다
    //username 테이블에 user 데이터를 집어넣는 방식

    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;


    public Board(BoardListRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
        this.username = requestDto.getUsername();
        this.setCreatAt(getCreatAt());
        this.setModifiedAt(getModifiedAt());

    }

    // dto에서 각 변수의 값을 받아와서 데이터베이스에 받아 오기 위한 메서드

//    public void update(BoardListRequestDto requestDto, Long username){
//            this.username = username;
//            this.title = requestDto.getTitle()
//  }
    //메소드 update 에 BoardListRequstDto의 저장된 DB값을 requestDto에 변수에 입력하고, 기본값 생성자로 지정한다.

//    public void updateArticle(Long username, String title, String contents, String password) {
//        this.username = username;
//       this.contents = contents;
//        this.title = title;
//        this.password = password;
//    }
    //메소드 updateArticle에 BoardListRequstDto의 저장된 DB값을 requestDto에 변수에 입력하고, 기본값 생성자로 지정한다.
}


// 게시글
// 타이틀 컨첸츠 패스워드 유저ID

