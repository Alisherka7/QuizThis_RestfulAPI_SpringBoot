package kr.alisher.quizthis.QuizThis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class Quiz {

    @GetMapping("/admin/quiz")
    public String quiz(){
        return "admin/quiz";
    }
}
