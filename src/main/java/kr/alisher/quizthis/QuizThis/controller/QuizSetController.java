package kr.alisher.quizthis.QuizThis.controller;

import kr.alisher.quizthis.QuizThis.repository.QuizsetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class QuizSetController {

    @Autowired
    QuizsetRepository quizsetRepository;

    @Autowired

    @GetMapping("/admin/quizsets")
    public String quizsets(){
        return "admin/quizsets";
    }
}
