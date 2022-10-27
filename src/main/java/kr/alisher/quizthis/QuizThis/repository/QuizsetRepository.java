package kr.alisher.quizthis.QuizThis.repository;

import kr.alisher.quizthis.QuizThis.entity.Quizset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface QuizsetRepository extends JpaRepository<Quizset, Integer> {

    // 특정 게시글의 모든 댓글 조회
    @Query(value = "select * " +
    "from quizset where " +
    "iduser = :curIduser ", nativeQuery = true)
    List<Quizset> findByIdUser(Integer curIduser);


    // 특정 닉네임의 모든 댓글 조회
    List<Quizset> findByQuizTitle(Integer idquizset);

    @Override
    ArrayList<Quizset> findAll();
}
