package com.sparta.hanghaememo.controller;

import com.sparta.hanghaememo.dto.BoardListRequestDto;
import com.sparta.hanghaememo.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RequiredArgsConstructor
@RestController
public class BoardController {

// Controller Server에서 API 요청(get, set 방식의 요청이 있을때)이 있을 때, 데이터를 Service로 넘겨주거나
// Server단에 전송해줄 Service에서 받아온 데이터를 조건과 일치할 때와 일치하지 않은 때, 조건에 맞는지 확인하여
// 출력하는 메소드를 제작하는 단

    private final BoardService boardService;



    // get 방식으로 가져온 데이터를 서버단에서 보내준 데이터를 내림차순으로 정리한다

    @PostMapping("/api/boards")
    public String creatBoard(@RequestBody BoardListRequestDto requestDto, HttpServletRequest request){
        String statusCode = "";
        try{
            statusCode =boardService.creatBoard(requestDto, request);
        }catch(Exception e){
            statusCode = "400";
            return new String(statusCode);
        }return new String(statusCode);
    }

    @GetMapping("/api/boards")
    public List<BoardListRequestDto> boardsearch(HttpServletRequest request){
        return boardService.boardsearch(request);
    }

    @DeleteMapping("/api/boards/delete/{id}")
    public List<BoardListRequestDto> boarddelete(HttpServletRequest request, @PathVariable Long id, @RequestParam String password ){
        return boardService.boarddelete(request, id, password);
    }

    @PostMapping("/api/boards/update/{id}")
    public List<BoardListRequestDto> boardupdateArticle(HttpServletRequest request, @RequestBody BoardListRequestDto requestDto,@PathVariable Long id, @RequestParam String password){
        return boardService.boardupdateArticle(request, requestDto, id, password);
    }

}
