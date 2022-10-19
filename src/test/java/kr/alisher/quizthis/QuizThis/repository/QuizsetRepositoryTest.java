package kr.alisher.quizthis.QuizThis.repository;

import kr.alisher.quizthis.QuizThis.entity.Quizset;
import kr.alisher.quizthis.QuizThis.entity.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA와 연동한 테스트!

class QuizsetRepositoryTest {

    @Autowired
    QuizsetRepository quizsetRepository;

    @Test
    @DisplayName("특정")
    void findByIdUser() {
        /* Case1 : 1번 사용자의 모든 퀴즈세트 조회 */
        {

            Date date_time = new Date(new java.util.Date().getTime());
            // 입력 데이터 준비
            int iduser = 2;

            // 실제 수행
            List<Quizset> quizsets = quizsetRepository.findByIdUser(iduser);

            Users users = new Users(2, "hello", "qwerty", "123321",date_time,"ewr@gmail.com");

            // 예상하기
            Quizset a = new Quizset(1, "Programming", "Quiz Programming language", 25, 2);
            Quizset b = new Quizset(2, "Data ", "Quiz test data structures", 10, 2);
            Quizset c = new Quizset(3, "3333QuizSet", "33333 description", 5, 2);

            List<Quizset> expected = Arrays.asList(a,b,c);
            // 검증
            assertEquals(expected.toString(), quizsets.toString(), "1번 사용자의 모든 퀴즈세트");
        }
    }

    @Test
    void findByQuizTitle() {
    }
}