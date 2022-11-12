package kr.alisher.quizthis.QuizThis.controller;


import kr.alisher.quizthis.QuizThis.dto.QuizDto;
import kr.alisher.quizthis.QuizThis.dto.QuizsetDto;
import kr.alisher.quizthis.QuizThis.entity.Quiz;
import kr.alisher.quizthis.QuizThis.entity.Quizset;
import kr.alisher.quizthis.QuizThis.repository.QuizRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@Slf4j
public class QuestionsApiController {

    @Autowired
    QuizRepository quizRepository;

    // 데이터 리스트 반환
    @GetMapping("/api/quiz")
    public List<Quiz> index(){
        return quizRepository.findAll();
    }

    // 퀴즈 세트 추가
    @PostMapping("/api/quiz/add")
    public Quiz addNewQuiz(@RequestBody QuizDto dto) throws NoSuchAlgorithmException {
        Quiz question = dto.toEntity();
        return quizRepository.save(question);
    }

    // 퀴즈 세트의 목록과 댓글을 수정
    @PatchMapping("api/quiz/{id}")
    public ResponseEntity<Quiz> updateQuizset(@PathVariable Integer id, @RequestBody QuizDto dto){
        //1: 수정용 엔태태 생성
        Quiz quiz = dto.toEntity();
        //2: 대상 엔티티 조회
        Quiz target = quizRepository.findById(id).orElse(null);

        if(target == null || id != quiz.getIdquiz()){
            log.info("잘못된 요청! 아이디{}, 유저{}", id, quiz.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        //4: 업데이트 및 정상 요청(200)
        target.patch(quiz);
        Quiz updated = quizRepository.save(target);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // 퀴즈 세트 제거
    @DeleteMapping("api/quiz/{id}")
    public ResponseEntity<Quiz> deleteQuizset(@PathVariable Integer id){
        //대상 찾기
        Quiz target = quizRepository.findById(id).orElse(null);

        // 잘못된 처리
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        //대상 삭제
        quizRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
