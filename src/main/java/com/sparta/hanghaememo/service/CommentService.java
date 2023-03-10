package com.sparta.hanghaememo.service;


import com.sparta.hanghaememo.Jwt.JwtUtil;
import com.sparta.hanghaememo.dto.BoardListRequestDto;
import com.sparta.hanghaememo.dto.CommentRequestDto;
import com.sparta.hanghaememo.entity.Board;
import com.sparta.hanghaememo.entity.Comment;
import com.sparta.hanghaememo.entity.User;
import com.sparta.hanghaememo.repository.BoardRepository;
import com.sparta.hanghaememo.repository.CommentRepository;
import com.sparta.hanghaememo.repository.UserRepositoy;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final BoardRepository boardRepository;
    private final UserRepositoy userRepository;
    private final CommentRepository commentRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public String creatComment(CommentRequestDto requestDto, HttpServletRequest request) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        Board board = requestDto.getBoard();
        String statusCode = "";

        // 토큰이 있는 경우에만 관심상품 조회 가능
        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                statusCode = "400";
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Board boardid = boardRepository.findById(board.getId()).orElseThrow(
                    () -> new IllegalArgumentException("일치하는 게시글이 없습니다")
            );

            Comment comment = new Comment();
            comment.setBoard(requestDto.getBoard());
            comment.setContents(requestDto.getContents());
            comment.setUsername(user.getUsername());
            commentRepository.save(comment);
            statusCode = "200";
        }
        return statusCode;
    }

    @Transactional
    public ResponseEntity<String> commentdelete(HttpServletRequest request, Long id) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;


        // 토큰이 있는 경우에만 관심상품 조회 가능
        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );


            Comment commentdb = commentRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 글입니다."));


            if (user.equals(commentdb.getUsername())) {
                commentRepository.delete(commentdb);
            }else{
                throw new IllegalArgumentException("아이디가 일치하지 않습니다.");
            }
        }
        return null;
    }

    @Transactional
    public List<CommentRequestDto> commentupdateArticle(HttpServletRequest request, BoardListRequestDto requestDto, Long id) {
        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 관심상품 조회 가능
        if (token != null) {
            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );


            Comment commentdb = commentRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 글입니다."));


            if (user.equals(commentdb.getUsername())){
                Comment comment = new Comment();
                comment.setContents(requestDto.getContents());
                commentRepository.save(comment);
            }
        }
        return null;
    }
}

