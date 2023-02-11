package com.sparta.hanghaememo.entity;

import com.sparta.hanghaememo.dto.BoardListRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

@Setter
@Getter
@ToString
@Entity
@NoArgsConstructor
public class Board extends TimeStamped {
    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String pw;


    //변수 지정 항목
    //ID는 PK로 생성되어 자동으로 신규 게시글이 생성될때 마다 1++되며 중복값을 가지지 않는다.

    public Board(BoardListRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.pw = requestDto.getPw();

    }

    // dto에서 각 변수의 값을 받아와서 데이터베이스에 받아 오기 위한 메서드

    public void update(BoardListRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
    }
    //메소드 update 에 BoardListRequstDto의 저장된 DB값을 requestDto에 변수에 입력하고, 기본값 생성자로 지정한다.

    public void updateArticle(String username, String title, String contents, String pw) {
        this.username = username;
        this.contents = contents;
        this.title = title;
        this.pw = pw;
    }
    //메소드 updateArticle에 BoardListRequstDto의 저장된 DB값을 requestDto에 변수에 입력하고, 기본값 생성자로 지정한다.
}

