package kr.alisher.quizthis.QuizThis.controller;

import kr.alisher.quizthis.QuizThis.dto.UsersForm;
import kr.alisher.quizthis.QuizThis.entity.Users;
import kr.alisher.quizthis.QuizThis.repository.UsersRepository;
import kr.alisher.quizthis.QuizThis.service.SHA256;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.List;

@RestController // RestAPI 용 컨트롤러! 데이터(JSON)를 반환
@Slf4j
public class UsersApiController {

    private SHA256 sha256 = new SHA256();

    @Autowired // DI
    private UsersRepository usersRepository;


    // Setting current time
    private Date date_time = new Date(new java.util.Date().getTime());

    //GET 유저 데이터들을 JSON으로 가져옴
    @GetMapping("/api/users")
    public List<Users> index(){
        return usersRepository.findAll();
    }

    @GetMapping("/api/users/{id}")
    public Users index(@PathVariable Integer id){
        return usersRepository.findById(id).orElse(null);
    }

    //Post
    @PostMapping("/api/users")
    public Users create(@RequestBody UsersForm dto) throws NoSuchAlgorithmException {
        // HA256으로 암호화된 비밀번호
        String cryptogram = sha256.encrypt(dto.getPassword());
        dto.setSHA256Password(cryptogram);

        dto.setDateTimeNow(date_time);
        Users user = dto.toEntity();
        return usersRepository.save(user);
    }

    // Patch
    @PatchMapping("/api/users/{id}")
    public ResponseEntity<Users> update(@PathVariable Integer id, @RequestBody UsersForm dto){
        //1: 수정용 엔티티 생성
        Users users = dto.toEntity();
        log.info("id{}, user{}", id, users.toString());

        //2: 대상 엔티티를 조회
        Users target = usersRepository.findById(id).orElse(null);

        //3: 질못된 요청 처리(대상의 없거나, id가 다른 경우)
        if(target == null || id != users.getId_user()){
            log.info("잘못된 요청! id{}, user{}", id, users.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        //4: 업데이트 및 정상 용답(200)
        target.patch(users);
        Users updated = usersRepository.save(target);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // Delete
    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Users> delete(@PathVariable Integer id){
        // 대상 찾기
        Users target = usersRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if(target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 대상 삭제
        usersRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
}
