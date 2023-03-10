package com.sparta.hanghaememo.entity;

import com.sparta.hanghaememo.dto.BoardListRequestDto;
import com.sparta.hanghaememo.dto.CommentRequestDto;
import com.sparta.hanghaememo.dto.SignupRequestDto;
import com.sparta.hanghaememo.repository.BoardRepository;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@Entity(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped {

    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Comment(CommentRequestDto requestDto, Board board) {
        this.board = board;
        this.contents = requestDto.getContents();
        this.username = requestDto.getUsername();
    }
}
