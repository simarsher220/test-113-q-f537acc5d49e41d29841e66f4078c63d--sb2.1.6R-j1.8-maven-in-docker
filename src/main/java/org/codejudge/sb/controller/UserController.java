package org.codejudge.sb.controller;

import org.codejudge.sb.entity.Quiz;
import org.codejudge.sb.error.CustomException;
import org.codejudge.sb.model.QuizSolution;
import org.codejudge.sb.model.ScoreResponse;
import org.codejudge.sb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity hello() throws CustomException {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }

    @PostMapping("/api/user/quiz-score")
    @ResponseBody
    public ResponseEntity calculateScore(@RequestBody QuizSolution results) throws CustomException {
        return new ResponseEntity<ScoreResponse>(userService.calculateScore(results), HttpStatus.OK);
    }

    @GetMapping("/api/quiz/all")
    @ResponseBody
    public ResponseEntity getAllQuiz() throws CustomException {
        List<Quiz> quizzes = userService.getAllQuiz();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    @GetMapping("/api/quiz-questions/all/{quizId}")
    @ResponseBody
    public ResponseEntity getQuizQuestions(@PathVariable("quizId") Integer quizId) throws CustomException {
        return new ResponseEntity<>(userService.getQuizQuestions(quizId), HttpStatus.OK);
    }
}
