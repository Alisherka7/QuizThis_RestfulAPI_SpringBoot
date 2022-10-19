package kr.alisher.quizthis.QuizThis.service;

import kr.alisher.quizthis.QuizThis.dto.QuizsetDto;
import kr.alisher.quizthis.QuizThis.entity.Quizset;
import kr.alisher.quizthis.QuizThis.repository.QuizsetRepository;
import kr.alisher.quizthis.QuizThis.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class QuizsetService {
    @Autowired
    private QuizsetRepository quizsetRepository;

    @Autowired
    private UsersRepository usersRepository;

//    public List<QuizsetDto> quizsets(Integer iduser){
//        // 조회: 댓글 목록
//        List<Quizset> qz = quizsetRepository.findAllById(iduser);
//
//        // 변환: 엔티티 -> DTO
//        List<QuizsetDto> dtos = new ArrayList<QuizsetDto>();
//        for(int i =0; i<qz.size();i++){
//            Quizset q = qz.get(i);
//            QuizsetDto dto = QuizsetDto.createQuizSetDto(q);
//            dtos.add(dto);
//        }
//        // 반환
//        return dtos;
//    }
}
