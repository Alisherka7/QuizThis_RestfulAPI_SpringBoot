package kr.alisher.quizthis.QuizThis.controller;

import kr.alisher.quizthis.QuizThis.entity.Quiz;
import kr.alisher.quizthis.QuizThis.entity.UserQuizSets;
import kr.alisher.quizthis.QuizThis.entity.Users;
import kr.alisher.quizthis.QuizThis.repository.QuizRepository;
import kr.alisher.quizthis.QuizThis.repository.QuizsetRepository;
import kr.alisher.quizthis.QuizThis.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class QuizSetController {

    @Autowired
    QuizsetRepository quizsetRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    QuizRepository quizRepository;

    UserQuizSets userQuizSets;

    @GetMapping("/admin/quizsets")
    public String quizsets(Model model){
        List<Users> users = usersRepository.findAll();// 동현석, 김정우, 김지태....
        List<Quiz> quiz = quizRepository.findByIdUser(1);
        for(Quiz j : quiz){
            System.out.println(j.getQuestion() + "--->" + j.getAnswer());
        }
        List<UserQuizSets> userDatas = getUserNameAndQuizSet(users);
        model.addAttribute("userDatas", userDatas);
        return "admin/quizsets";
    }

    private List<UserQuizSets> getUserNameAndQuizSet(List<Users> users) {
        String username;
        Integer id;
        int col;

        List<UserQuizSets> userDatas = new ArrayList<>();
        for(int i =0; i<users.size(); i++){
            username = users.get(i).getUsername();
            id = users.get(i).getId_user();
            col = quizsetRepository.findByIdUser(id).size();
            System.out.println("Username -->" + username);
            System.out.println("id -->" + id);
            System.out.println("col -->" + col);
            userDatas.add(new UserQuizSets(username, id, col));
        }
        return userDatas;
    }

}
