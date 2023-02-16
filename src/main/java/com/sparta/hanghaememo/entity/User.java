package com.sparta.hanghaememo.entity;


import com.sparta.hanghaememo.dto.BoardListRequestDto;
import com.sparta.hanghaememo.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Setter
@Getter
@ToString
@Entity(name = "users")
@NoArgsConstructor
public class User {

    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String username;

    @Column(nullable = false, length = 15)
    private String password;



    public User(SignupRequestDto requestDto) {
        this.username = username;
        this.password = password;

    }
}

