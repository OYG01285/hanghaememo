package com.sparta.hanghaememo.service;


import com.sparta.hanghaememo.Jwt.JwtUtil;
import com.sparta.hanghaememo.dto.BoardListRequestDto;
import com.sparta.hanghaememo.entity.Board;
import com.sparta.hanghaememo.entity.User;
import com.sparta.hanghaememo.repository.BoardRepository;
import com.sparta.hanghaememo.repository.UserRepositoy;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {

    // 서버단에서 실행이 있을 때, 컨트롤러의 명령을 받아 데이터를 받아와서 DB에 저장하거나
    // 서버단에서 요청이 있을 때, 컨트롤러의 명령을 받아 조회나 확인 등등의 모든 작업을 직접적으로 실행하는 부분

    private final BoardRepository boardRepository;
    private final UserRepositoy userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public String creatBoard(BoardListRequestDto requestDto, HttpServletRequest request) {
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

            Board board = new Board();
            board.setTitle(requestDto.getTitle());
            board.setContents(requestDto.getContents());
            board.setPassword(requestDto.getPassword());
            board.setUsername(user.getUsername());
            board.setCreatAt(board.getCreatAt());
            boardRepository.save(board);

        }
        return "200";
    }


    @Transactional

    public List<BoardListRequestDto> search(HttpServletRequest request) {
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


            List<BoardListRequestDto> list = new ArrayList<>();
            List<Board> boardsList;
            boardsList = boardRepository.findAll();


            for (Board product : boardsList) {
                list.add(new BoardListRequestDto(product));
            }

            return list;

        } else {
            return null;
        }
    }


    @Transactional
    public List<BoardListRequestDto> delete(HttpServletRequest request, Long id, String password) {
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


            Board dbboard = boardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 글입니다."));


            if (password.equals(dbboard.getPassword())) {
                boardRepository.delete(dbboard);
            }
        }
        return null;
    }

    @Transactional
    public List<BoardListRequestDto> updateArticle(HttpServletRequest request, BoardListRequestDto requestDto, Long id, String password) {
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


            Board dbboard = boardRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 글입니다."));


            if (password.equals(dbboard.getPassword())) {
                Board board = new Board();
                board.setTitle(requestDto.getTitle());
                board.setContents(requestDto.getContents());
                board.setPassword(requestDto.getPassword());
                board.setUsername(user.getUsername());
                board.setModifiedAt(board.getModifiedAt());
                boardRepository.save(dbboard);
            }
        }
        return null;
    }
}




