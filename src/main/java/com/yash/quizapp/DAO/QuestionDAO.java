package com.yash.quizapp.DAO;

import com.yash.quizapp.Database.Question.Question;
import com.yash.quizapp.Database.Question.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionDAO {

    @Autowired
    QuestionRepo repo;

    public List<Question> getAllQuestions() {
        return repo.findAll();
    }

    public List<Question> getQuestionsByCategory(String text) {
        return repo.findByCategory(text.toLowerCase());
    }

    public Question addQuestion(Question question) {
        return repo.save(question);
    }
}
