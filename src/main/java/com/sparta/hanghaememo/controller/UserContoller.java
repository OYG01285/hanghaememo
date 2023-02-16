package com.sparta.hanghaememo.controller;


import com.sparta.hanghaememo.dto.LoginRequestDto;
import com.sparta.hanghaememo.dto.SignupRequestDto;
import com.sparta.hanghaememo.entity.User;
import com.sparta.hanghaememo.repository.UserRepositoy;

import com.sparta.hanghaememo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class UserContoller {

    private final UserService userService;

    //@GetMapping("/signup")
    //public ModelAndView signupPage() {
    //    No Environmentreturn new ModelAndView("signup");
    //}

    //이 부분이 이해가 되질 않는다
    //인강을 보면 http의 버튼을 눌르면 작동되는걸로 봐서 해당 url로 이동한다는것 같은데
    //내가 html 쪽을 영 모르다 보니 작동원리가 이해가 되질 않는다.
    //나중에 팀 프로젝트 할때 문제가 될것 같기는 하다.

    @ResponseBody
    @PostMapping("/api/user/signup")
    public User signup(@RequestBody SignupRequestDto signupRequestDto) {
        return  userService.signup(signupRequestDto);
    }

    @ResponseBody
    @PostMapping("/api/user/login")
    public void login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
       userService.login(loginRequestDto, response);
    }

}



