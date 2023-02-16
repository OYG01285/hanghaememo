package com.sparta.hanghaememo.repository;

import com.sparta.hanghaememo.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    //BoardRepositoy 에 DB와 연결된 JpaRepository 메소드를 상속시켜 사용할 수 있게 되었다.
    //JpaRepository 는 DB 테이블과 타입을 지정해 주면 DB 전체를 불러 올 수 있다.

    List<Board> findByOrderByCreatAtDesc();

    List<Board> findAllById(Long id);


    // 게시글 내림차순 정리
}

