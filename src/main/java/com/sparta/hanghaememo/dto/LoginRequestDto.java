package com.sparta.hanghaememo.dto;

import com.sparta.hanghaememo.entity.Board;
import com.sparta.hanghaememo.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginRequestDto {

    private String username;

    private String password;

    public LoginRequestDto(User entity) {
        this.username = entity.getUsername();
        this.password = entity.getPassword();
    }
}
