package kr.alisher.quizthis.QuizThis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;


@Entity
@Slf4j
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성 아노테이션
    Integer idquiz;

    @Column
    String question;

    @Column
    String answer;

    @Column
    Integer idquizset;
}
