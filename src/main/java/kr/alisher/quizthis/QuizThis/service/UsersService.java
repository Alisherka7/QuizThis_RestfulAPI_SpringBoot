package kr.alisher.quizthis.QuizThis.service;


import kr.alisher.quizthis.QuizThis.dto.UsersForm;
import kr.alisher.quizthis.QuizThis.entity.Users;
import kr.alisher.quizthis.QuizThis.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public Users create(UsersForm form){
        Users users = form.toEntity();
        return usersRepository.save(users);
    }
}
