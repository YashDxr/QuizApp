package com.yash.quizapp.Database.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String text);

    @Query(value = "select * from question where category=:category order by random() limit :length", nativeQuery = true)
    List<Question> findQuestionsByCategory(String category, int length);
}
