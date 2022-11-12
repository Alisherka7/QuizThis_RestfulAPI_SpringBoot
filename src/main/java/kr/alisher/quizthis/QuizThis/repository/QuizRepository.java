package kr.alisher.quizthis.QuizThis.repository;

import kr.alisher.quizthis.QuizThis.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    // 특정 게시글의 모든 댓글 조회
    @Query(value = "select * " +
            "from quiz where " +
            "idquizset = :idquizset ", nativeQuery = true)
    List<Quiz> findByIdSet(Integer idquizset);


    @Query(value = "select * " +
            "from quiz where " +
            "idquiz = :idquiz ", nativeQuery = true)
    Quiz findByIdData(Integer idquiz);

    @Override
    ArrayList<Quiz> findAll();
}
