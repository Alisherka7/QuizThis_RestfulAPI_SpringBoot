package kr.alisher.quizthis.QuizThis.controller;

import kr.alisher.quizthis.QuizThis.dto.QuizsetDto;
import kr.alisher.quizthis.QuizThis.entity.Quiz;
import kr.alisher.quizthis.QuizThis.entity.Quizset;
import kr.alisher.quizthis.QuizThis.repository.QuizsetRepository;
import kr.alisher.quizthis.QuizThis.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@Slf4j
public class QuizSetApiController {

    @Autowired
    private QuizsetRepository quizsetRepository;

    // 데이터 리스트 반환
    @GetMapping("/api/quizset")
    public List<Quizset> index(){
        return quizsetRepository.findAll();
    }

    // 퀴즈 세트 추가
    @PostMapping("/api/quizset/add")
    public Quizset addNewSet(@RequestBody QuizsetDto dto) throws NoSuchAlgorithmException {
        Quizset set = dto.toEntity();
        return quizsetRepository.save(set);
    }

    // 퀴즈 세트의 목록과 댓글을 수정
    @PatchMapping("api/quizset/{id}")
    public ResponseEntity<Quizset> updateQuizset(@PathVariable Integer id, @RequestBody QuizsetDto dto){
        //1: 수정용 엔태태 생성
        Quizset quizset = dto.toEntity();
        //2: 대상 엔티티 조회
        Quizset target = quizsetRepository.findById(id).orElse(null);

        if(target == null || id != quizset.getIdquizset()){
            log.info("잘못된 요청! 아이디{}, 유저{}", id, quizset.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        //4: 업데이트 및 정상 요청(200)
        target.patch(quizset);
        Quizset updated = quizsetRepository.save(target);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    // 퀴즈 세트 제거
    @DeleteMapping("api/quizset/{id}")
    public ResponseEntity<Quizset> deleteQuizset(@PathVariable Integer id){
        //대상 찾기
        Quizset target = quizsetRepository.findById(id).orElse(null);

        // 잘못된 처리
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        //대상 삭제
        quizsetRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
