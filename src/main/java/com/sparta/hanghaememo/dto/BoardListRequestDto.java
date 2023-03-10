package com.sparta.hanghaememo.dto;

import com.sparta.hanghaememo.entity.Board;
import com.sparta.hanghaememo.entity.Timestamped;
import com.sparta.hanghaememo.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import java.lang.ref.PhantomReference;
import java.sql.Time;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor    // 생성자 주입을 임의의 코드 없이 자동으로 설정해주는 어노테이션이다.
public class BoardListRequestDto{

    //RequserDto는 서버단에서 보내주는 데이터를 저장하기 위해 사용한다
    //첫번째 과제 내용은 게시글 작성 시, 유저이름, 제목, 내용, 패스워드를 입력받아
    //데이터 베이스 내용을 지정하는 방식이기 때문에,
    //데이터 베이스에 들어가는 각 객체를 생성한다.


    //여기서 의문점
    //BoardListRequestDto(Board entity) 방식으로 매소드를 생성했는데
    //어짜피 똑같은 변수라면 BoardListRequestDto(BoardListRequestDto entity)
    //로 지정해서 생성해도 되지 않을까?
    //현재 서비스단에 지정해놓은 방식때문에
    //쉽게 코드를 바꿀 수는 없지만 물어봐야 할듯

    private String title;
    private String contents;
    private String password;
    private String username;


    // BoardListRequestDto에 객체를 생성한다

    public BoardListRequestDto(Board entity) {
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.password =entity.getPassword();
        this.username =entity.getUsername();
    }
}


// 화면에서 넘어오는 값을 받기 위하여 변수를 생성하고, 해당 내용을 보드로 넘겨주기 위해 메소드를 생성한다.
