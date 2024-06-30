package com.yash.quizapp.Controllers;


import com.yash.quizapp.DAO.QuestionDAO;
import com.yash.quizapp.Database.Question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionDAO dao;

    @GetMapping("allquestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return new ResponseEntity<>(dao.getAllQuestions(), HttpStatus.OK);
    }

    @GetMapping("category/{type}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String type) {
        return new ResponseEntity<>(dao.getQuestionsByCategory(type), HttpStatus.OK);
    }

    @PostMapping("addquestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        try {
        return new ResponseEntity<>(dao.addQuestion(question), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }
}
