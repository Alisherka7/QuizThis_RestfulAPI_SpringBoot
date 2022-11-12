package kr.alisher.quizthis.QuizThis.dto;

import kr.alisher.quizthis.QuizThis.entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@Getter
@ToString
public class QuizDto {
    private Integer idquiz;
    private String question;
    private String answer;
    private Integer idquizset;

    public Quiz toEntity(){
        return new Quiz(idquiz, question, answer, idquizset);
    }
}
