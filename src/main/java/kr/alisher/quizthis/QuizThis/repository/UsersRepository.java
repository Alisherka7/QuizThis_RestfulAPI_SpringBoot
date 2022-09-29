package kr.alisher.quizthis.QuizThis.repository;

import kr.alisher.quizthis.QuizThis.entity.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UsersRepository extends CrudRepository<Users, Integer> {
    @Override
    ArrayList<Users> findAll();
}
