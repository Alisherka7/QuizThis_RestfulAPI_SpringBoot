package kr.alisher.quizthis.QuizThis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성 아노테이션
    private int id_user;

    @Column
    private String username;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String progress;

    public void patch(Users users){
        if(users.username != null){
            this.username = users.username;
        }
        if(users.login != null){
            this.login = users.login;
        }
        if(users.password != null){
            this.password = users.password;
        }
        if(users.progress != null){
            this.progress = users.progress;
        }
    }

    public void masking(String maskingStr){
        this.password = maskingStr;
    }
}
