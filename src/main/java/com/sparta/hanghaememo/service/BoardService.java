package com.sparta.hanghaememo.service;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.sparta.hanghaememo.dto.BoardListRequestDto;
import com.sparta.hanghaememo.entity.Board;
import com.sparta.hanghaememo.repository.BoardRepository;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    @Transactional
    public Long update(Long id, BoardListRequestDto requestDto) throws IllegalAccessException {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalAccessException("존재하지 않는 글입니다.")
        );

        board.update(requestDto);
        return board.getId();
    }
    @Transactional
    public Board creatBoard(BoardListRequestDto requestDto) {
        Board board = new Board();
        board.setUsername(requestDto.getUsername());
        board.setTitle(requestDto.getTitle());
        board.setContents(requestDto.getContents());
        board.setPw(requestDto.getPw());

        return boardRepository.save(board);
    }

    @Transactional
    public BoardListRequestDto searchById(Long id){
        Board entity = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        return new BoardListRequestDto(entity);
    }

    @Transactional
    public String delete(Long id, Board board) {

        Board dbboard = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
        String stateCode = "";
        if (board.getPw().equals(dbboard.getPw())) {
            stateCode = "200";
            boardRepository.delete(dbboard);
        }else{
            stateCode = "400";
        }
        return stateCode;
    }

    @Transactional
    public String updateArticle(Long id, BoardListRequestDto requestDto){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 글입니다.")
        );

        String stateCode = "";

        if(requestDto.getPw().equals(board.getPw())) {
            board.setUsername(requestDto.getUsername());
            board.setTitle(requestDto.getTitle());
            board.setContents(requestDto.getContents());
            board.setPw(requestDto.getPw());
            stateCode = "200";

           //System.out.println(board.toString());

            boardRepository.save(board);

        }else{
            stateCode = "400";
        }
        return stateCode;

        //오류 코드를 보내기 위해 updateArticle를 String로 작성하였으나
        //boardRepository에(DB에) 자료를 넣기 위해서는 Board타입으로 자료를 dbboard에 넣어서
        //return 시켜야 하는데 방법이 생각나질 않음
    }
}



