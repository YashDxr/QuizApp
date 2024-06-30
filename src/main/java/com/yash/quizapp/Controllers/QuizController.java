package com.yash.quizapp.Controllers;


import com.yash.quizapp.DAO.QuizDao;
import com.yash.quizapp.Database.Quiz.QuestionWrapper;
import com.yash.quizapp.Database.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizDao dao;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int length, @RequestParam String title){
        try{
            return new ResponseEntity<>(dao.createQuiz(category, length, title), HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getquiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
        return new ResponseEntity<>(dao.getQuiz(id), HttpStatus.OK);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<String> submitQuiz(@PathVariable int id , @RequestBody List<Response> responses){
        System.out.println(id+":"+responses);
        return new ResponseEntity<>(dao.submitAnswers(id, responses), HttpStatus.OK);
    }
}
