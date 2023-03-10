package com.sparta.hanghaememo.dto;

import com.sparta.hanghaememo.entity.Board;
import com.sparta.hanghaememo.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CommentRequestDto {

    private String contents;
    private Board board;
    private String username;


    public CommentRequestDto(Comment entity){
        this.board = entity.getBoard();
        this.contents = entity.getContents();
        this.username =entity.getUsername();
    }

}