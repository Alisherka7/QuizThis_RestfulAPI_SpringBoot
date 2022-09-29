package kr.alisher.quizthis.QuizThis.dto;

import kr.alisher.quizthis.QuizThis.entity.Users;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class UsersForm {
    private int id_user;
    private String username;
    private String login;
    private String password;
    private String progress;

    public Users toEntity(){
        return new Users(id_user, username, login, password, progress);
    }
}
