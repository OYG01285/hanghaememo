package com.sparta.hanghaememo.controller;

import com.sparta.hanghaememo.dto.BoardListRequestDto;
import com.sparta.hanghaememo.entity.Board;
import com.sparta.hanghaememo.repository.BoardRepository;
import com.sparta.hanghaememo.service.BoardService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardController {


    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @GetMapping("api/boards")
    public List<Board> redBoardList(BoardListRequestDto requestDto) {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    @PostMapping("api/boards")
    public Board creatBoard(@RequestBody BoardListRequestDto requestDto){
        return boardService.creatBoard(requestDto);
    }

    @GetMapping("api/boards/{id}")
    public BoardListRequestDto searchByID(@PathVariable Long id){
        return boardService.searchById(id);
    }

    @DeleteMapping("api/boards/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, @RequestBody Board board){
        String stateCode = boardService.delete(id, board);
        return new ResponseEntity<String>(stateCode, HttpStatus.OK);
    }

    @PostMapping("api/boards/update/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable Long id, @RequestBody BoardListRequestDto requestDto){
        String stateCode = boardService.updateArticle(id, requestDto);
        return new ResponseEntity<String>(stateCode, HttpStatus.OK);
    }

}
