package com.yash.quizapp.DAO;

import com.yash.quizapp.Database.*;
import com.yash.quizapp.Database.Question.Question;
import com.yash.quizapp.Database.Question.QuestionRepo;
import com.yash.quizapp.Database.Quiz.QuestionWrapper;
import com.yash.quizapp.Database.Quiz.Quiz;
import com.yash.quizapp.Database.Quiz.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizDao {

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuestionRepo questionRepo;

    public String createQuiz(String category, int length, String title) {
        List<Question> questions = questionRepo.findQuestionsByCategory(category, length);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(questions);

        quizRepo.save(quiz);

        return "Quiz created...";
    }

    public List<QuestionWrapper> getQuiz(int id) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Question> questions = quiz.getQuestion();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();

        for(Question q : questions){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(),q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionWrappers.add(qw);
        }

        return questionWrappers;
    }


    public String submitAnswers(int id, List<Response> responses) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Question> questions = quiz.getQuestion();

        int result = 0;
        int i = 0;

        for(Response r : responses){
            if(r.getAnswer().equals(questions.get(i).getRightAnswer())) result++;

            i++;
        }

        return "Results: "+result;
    }
}
