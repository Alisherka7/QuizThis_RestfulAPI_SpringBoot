package kr.alisher.quizthis.QuizThis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;


@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
public class UserQuizSets {
    private String userName;
    private Integer id;
    private int quizSetCol;
}
