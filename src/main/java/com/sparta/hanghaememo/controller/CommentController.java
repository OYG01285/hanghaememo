package com.sparta.hanghaememo.controller;


import com.sparta.hanghaememo.dto.BoardListRequestDto;
import com.sparta.hanghaememo.dto.CommentRequestDto;
import com.sparta.hanghaememo.service.BoardService;
import com.sparta.hanghaememo.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;
    @PostMapping("/api/Board/Comment")
    public ResponseEntity<String> creatComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request){
        String statusCode = "";
        try{
            statusCode = commentService.creatComment(requestDto, request);
        }catch(Exception e){
            statusCode = "400";
            return new ResponseEntity<String>(statusCode, HttpStatus.BAD_REQUEST);
        }return new ResponseEntity<String>(statusCode, HttpStatus.OK);
    }

    @DeleteMapping("/api/Board/Comment/delete/{id}")
    public ResponseEntity<String> commentdelete(HttpServletRequest request, @PathVariable Long id ){
        return commentService.commentdelete(request, id);
    }

    @PostMapping("/api/Board/Comment/update/{id}")
    public List<CommentRequestDto> commentupdateArticle(HttpServletRequest request, @RequestBody BoardListRequestDto requestDto,@PathVariable Long id){
        return commentService.commentupdateArticle(request, requestDto, id);
    }


}
