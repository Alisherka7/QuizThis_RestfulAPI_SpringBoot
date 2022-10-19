package kr.alisher.quizthis.QuizThis.controller;

import kr.alisher.quizthis.QuizThis.dto.UsersForm;
import kr.alisher.quizthis.QuizThis.entity.Quizset;
import kr.alisher.quizthis.QuizThis.entity.Users;
import kr.alisher.quizthis.QuizThis.repository.QuizsetRepository;
import kr.alisher.quizthis.QuizThis.repository.UsersRepository;
import kr.alisher.quizthis.QuizThis.service.SHA256;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.List;

@Controller
@Slf4j // 로깅을 위한 골뱅이 안노테이션
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private QuizsetRepository quizsetRepository;

    private SHA256 sha256 = new SHA256();

    private Date date_time = new Date(new java.util.Date().getTime());

    @GetMapping("/admin")
    public String admin(Model model) throws NoSuchAlgorithmException {
        // 1 모든 사용자들과 퀴즈세트의 수를 가져온다
        int numbersOfUsers = usersRepository.findAll().size();
        int numbersOfQuizSets = quizsetRepository.findAll().size();
        List<Quizset> quizst = quizsetRepository.findAll();

        for(Quizset q : quizst){
            System.out.println(q.getIduser());
        }

        // 퀴즈 세트
        model.addAttribute("numbersOfQuizSets", numbersOfQuizSets);
        // 사용자들의 수
        model.addAttribute("numbersOfUsers", numbersOfUsers);

        // 3 뷰 페이지를 설정
        return "admin/index";
    }

    @GetMapping("/admin/newuser")
    public String newUser(){
        return "admin/adduser";
    }


    @GetMapping("/admin/userslist/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다.");
        // 삭제하는 객체가 찾아오고 delete메소드로 제거됨
        Users target = usersRepository.findById(id).orElse(null);
        if(target != null){
            usersRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.!");
        }
        return "redirect:/admin/userslist";
    }

    @PostMapping("/createuser")
    public String newUserForm(UsersForm form) throws NoSuchAlgorithmException {
        // 유저 폼 객체에서 setDateTimeNow 함수로 계정 등록 기간 추가함
        form.setDateTimeNow(date_time);

        // HA256으로 암호화된 비밀번호
        String cryptogram = sha256.encrypt(form.getPassword());
        form.setSHA256Password(cryptogram);

        //1 DTO를 변환 -> Entity
        Users users = form.toEntity();
        log.info("DATE Time -> " + date_time.toString());

        //2. Repository에게 entity를 DB안에 저장하게 함!
        Users saved = usersRepository.save(users);
        return "redirect:/admin/userslist";
    }

    @GetMapping("/admin/userslist")
    public String userslist(Model model){
        // 1 모든 Users를 가져온다
        List<Users> usersEntityList = usersRepository.findAll();

        // 1.1 모든 Users 비밀번호들을 마스킹한다
        passwordMasking(usersEntityList);




        // 2 가져온 Users 묶음을 뷰로 전달
        model.addAttribute("usersList", usersEntityList);

        return "admin/usersList";
    }

    private void passwordMasking(List<Users> usersEntityList) {
        for(int i =0; i<usersEntityList.size(); i++){
            usersEntityList.get(i).masking("*****");
        }
    }

}
