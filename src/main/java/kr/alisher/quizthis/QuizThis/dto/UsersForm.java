package kr.alisher.quizthis.QuizThis.dto;

import kr.alisher.quizthis.QuizThis.entity.Users;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

@AllArgsConstructor
@ToString
public class UsersForm {

    private Integer id_user;
    private String username;
    private String login;
    private String password;

    @Temporal(TemporalType.DATE)
    private java.util.Date date_time;
    private String email;

    public Users toEntity(){
        return new Users(id_user, username, login, password, date_time, email);
    }
    public void setDateTimeNow(Date date){
        this.date_time = date;
    }

    public void setSHA256Password(String hash){
        this.password = hash;
    }

    public String getPassword(){
        return password;
    }
}
