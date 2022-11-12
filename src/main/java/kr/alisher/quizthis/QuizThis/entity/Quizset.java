package kr.alisher.quizthis.QuizThis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Quizset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성 아노테이션
    private Integer idquizset;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private Integer progress;

    @Column
    private Integer iduser;

    public void patch(Quizset quizset){
        if(quizset.title != null){
            this.title = quizset.title;
        }
        if(quizset.description != null){
            this.description = quizset.description;
        }
        if(quizset.progress != null){
            this.progress = quizset.progress;
        }
    }

}
