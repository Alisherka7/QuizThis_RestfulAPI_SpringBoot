package kr.alisher.quizthis.QuizThis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class MainPageController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
