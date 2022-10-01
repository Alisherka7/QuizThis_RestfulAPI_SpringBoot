package kr.alisher.quizthis.QuizThis.controller;

import kr.alisher.quizthis.QuizThis.entity.Users;
import kr.alisher.quizthis.QuizThis.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j // 로깅을 위한 골뱅이 안노테이션
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/admin")
    public String admin(Model model){
        // 1 모든 Users를 가져온다
        List<Users> usersEntityList = usersRepository.findAll();

        // 1.1 모든 Users 비밀번호들을 마스킹한다
        passwordMasking(usersEntityList);

        // 2 가져온 Users 묶음을 뷰로 전달
        model.addAttribute("usersList", usersEntityList);

        // 3 뷰 페이지를 설정
        return "admin/index";
    }

    private void passwordMasking(List<Users> usersEntityList) {
        for(int i =0; i<usersEntityList.size(); i++){
            usersEntityList.get(i).masking("*****");
        }
    }

}
