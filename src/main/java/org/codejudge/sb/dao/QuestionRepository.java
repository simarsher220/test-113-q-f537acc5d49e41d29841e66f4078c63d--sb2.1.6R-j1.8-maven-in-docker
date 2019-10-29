package org.codejudge.sb.dao;

import org.codejudge.sb.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

    @Query(value = "select * from question where id = :id", nativeQuery = true)
    Question getQuestionById(@Param("id") UUID id);

    @Query(value = "select * from question where quiz_id = :quiz_id", nativeQuery = true)
    List<Question> getQuestionsByQuizId(@Param("quiz_id") UUID quizId);
}
