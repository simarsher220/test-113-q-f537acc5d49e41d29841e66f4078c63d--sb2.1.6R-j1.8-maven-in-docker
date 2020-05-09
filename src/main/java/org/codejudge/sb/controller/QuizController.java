package org.codejudge.sb.controller;

import lombok.extern.slf4j.Slf4j;
import org.codejudge.sb.entity.Question;
import org.codejudge.sb.entity.Quiz;
import org.codejudge.sb.error.CustomException;
import org.codejudge.sb.model.EmptyResponse;
import org.codejudge.sb.model.QuizQuestions;
import org.codejudge.sb.service.QuestionService;
import org.codejudge.sb.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/api/quiz/{quizId}")
    public ResponseEntity getQuiz(@PathVariable("quizId") Integer quizId) throws CustomException {
        Quiz quiz = quizService.getQuizById(quizId);
        if (quiz == null) {
            return new ResponseEntity<>(new EmptyResponse(), HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        }
    }

    @PostMapping("/api/quiz/")
    @ResponseBody
    public ResponseEntity getQuiz(@RequestBody Quiz quiz) throws CustomException {
        quiz = quizService.createQuiz(quiz);
        return new ResponseEntity<>(quiz, HttpStatus.CREATED);
    }

    @GetMapping("/api/quiz-questions/{quizId}")
    public ResponseEntity getQuizWithQuestions(@PathVariable("quizId") Integer quizId) {
        Quiz quiz = quizService.getQuizById(quizId);
        if (quiz == null) {
            return new ResponseEntity<>(new EmptyResponse(), HttpStatus.NOT_FOUND);
        }
        List<Question> questions = questionService.getQuestionsByQuizId(quizId);
        QuizQuestions quizQuestions = new QuizQuestions();
        quizQuestions.setName(quiz.getName());
        quizQuestions.setDescription(quiz.getDescription());
        quizQuestions.setQuestions(questions);
        return new ResponseEntity<>(quizQuestions, HttpStatus.OK);
    }
}
