package com.sparta.hanghaememo.dto;

import com.sparta.hanghaememo.entity.Board;
import com.sparta.hanghaememo.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SignupRequestDto {
    private String username;

    private String password;

    public SignupRequestDto(User entity) {
        this.username = entity.getUsername();
        this.password = entity.getPassword();
    }
}

