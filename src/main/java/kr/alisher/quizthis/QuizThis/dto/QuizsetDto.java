package kr.alisher.quizthis.QuizThis.dto;

import kr.alisher.quizthis.QuizThis.entity.Quizset;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class QuizsetDto {

    private Integer idquizset;
    private String title;
    private String description;
    private Integer progress;
    private Integer iduser;

    public Quizset toEntity() {
        return new Quizset(
                idquizset, title, description, progress, iduser
        );
    }
}
