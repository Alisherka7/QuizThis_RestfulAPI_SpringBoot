package kr.alisher.quizthis.QuizThis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j // 로깅을 위한 골뱅이 안노테이션
public class LoginController {

    @GetMapping("/admin/login")
    public String login(){
        return "admin/login/login";
    }
}
