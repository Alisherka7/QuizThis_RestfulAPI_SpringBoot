package kr.alisher.quizthis.QuizThis.controller;

import kr.alisher.quizthis.QuizThis.dto.QuizDto;
import kr.alisher.quizthis.QuizThis.entity.Quiz;
import kr.alisher.quizthis.QuizThis.entity.Quizset;
import kr.alisher.quizthis.QuizThis.repository.QuizRepository;
import kr.alisher.quizthis.QuizThis.repository.QuizsetRepository;
import kr.alisher.quizthis.QuizThis.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class Questions {

    @Autowired
    QuizsetRepository quizsetRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    QuizRepository quizRepository;

    @GetMapping("/admin/quiz/{iduser}/{idquizset}")
    public String quiz(@PathVariable Integer iduser, @PathVariable Integer idquizset, Model model){
        List<Quiz> quiz = quizRepository.findByIdSet(idquizset);
        model.addAttribute("questions", quiz);
        return "admin/quiz";
    }

    @GetMapping("/admin/quiz/edit/{idquiz}")
    public String quizEdit(@PathVariable Integer idquiz, Model model){
        Quiz quiz = quizRepository.findById(idquiz).orElse(null);
        model.addAttribute("quizDatas", quiz);
        return "admin/edit/quizEdit";
    }

    @PostMapping("/quizEdit")
    public String quizEditSave(QuizDto form) {
        //1: DTO를 엔티티로 반환한다
        Quiz dto = form.toEntity();
        Quizset quizset = quizsetRepository.findById(dto.getIdquizset()).orElse(null);

        //2: 엔티티를 데이터베이스로 저장한다.
        //2-1 데이터베이스에서 기존 데이터를 가져온다.
        Quiz target = quizRepository.findById(form.getIdquiz()).orElse(null);
        //2-2 기존 데이터가 있면! 값을 갱신한다.
        if(target != null){
            quizRepository.save(dto);
        }
        return "redirect:/admin/quiz/" + quizset.getIduser() + "/" + quizset.getIdquizset();
    }
}
