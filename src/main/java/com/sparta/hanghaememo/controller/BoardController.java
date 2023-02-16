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
    public ResponseEntity<String> creatBoard(@RequestBody BoardListRequestDto requestDto, HttpServletRequest request){
         String scp = "";
         try{
             scp =boardService.creatBoard(requestDto, request);
         }catch(Exception e){
             scp = "400";
             return new ResponseEntity<String>(scp, HttpStatus.BAD_REQUEST);
        }return new ResponseEntity<String>(scp, HttpStatus.OK);
    }

    // Board 형식의 creatBoard 메소드를 생성한다
    // PostMapping을 사용하여 서버단 api/boards url로 통신하여
    // 서버단에서 입력한 데이터로 BoardListRequestDto 형식으로 requestDto를 초기화 한다.
    // 데이터는 Body단에 있으므로 RequstBody를 사용하여 Body의 데이터를 가져온다.
    // 그 후, 그 초기화된 데이터를 boardService에 creatBoard에 전달한다.

    @GetMapping("/api/boards")
    public List<BoardListRequestDto> search(HttpServletRequest request){
        return boardService.search(request);
    }

    @DeleteMapping("/api/boards/delete/{id}")
    public List<BoardListRequestDto> delete(HttpServletRequest request, @PathVariable Long id, @RequestParam String password ){
        return boardService.delete(request, id, password);
    }

    @PostMapping("/api/boards/update/{id}")
    public List<BoardListRequestDto> updateArticle(HttpServletRequest request, @RequestBody BoardListRequestDto requestDto,@PathVariable Long id, @RequestParam String password){
        return boardService.updateArticle(request, requestDto, id, password);
    }

}
