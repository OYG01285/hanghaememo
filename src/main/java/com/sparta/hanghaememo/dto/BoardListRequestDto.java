package com.sparta.hanghaememo.dto;

import com.sparta.hanghaememo.entity.Board;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor    // 생성자 주입을 임의의 코드 없이 자동으로 설정해주는 어노테이션이다.
public class BoardListRequestDto {

    private String username;
    private String title;
    private String contents;
    private String pw;

    // Board에서 변수를 불러온다

    public BoardListRequestDto(Board entity) {
        this.username = entity.getUsername();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.pw = entity.getPw();
    }
}


// 화면에서 넘어오는 값을 받기 위하여 변수를 생성하고, 해당 내용을 보드로 넘겨주기 위해 메소드를 생성한다.
