package org.codejudge.sb.dao;

import org.codejudge.sb.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, UUID> {

    @Query(value = "select * from quiz where id = :id", nativeQuery = true)
    Quiz getQuizById(@Param("id") Integer id);
}
