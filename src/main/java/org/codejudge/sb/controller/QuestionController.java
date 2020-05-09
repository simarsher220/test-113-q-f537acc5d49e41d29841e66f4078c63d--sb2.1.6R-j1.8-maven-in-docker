package org.codejudge.sb.controller;

import org.codejudge.sb.entity.Question;
import org.codejudge.sb.error.CustomException;
import org.codejudge.sb.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/api/questions/{questionId}")
    public ResponseEntity getQuestion(@PathVariable("questionId") Integer questionId) throws CustomException {
        Question question = questionService.getQuestionById(questionId);
        if (question == null) {
            throw new CustomException("Couldn't find question", HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(question, HttpStatus.OK);
        }
    }

    @PostMapping("/api/questions/")
    public ResponseEntity createQuestion(@RequestBody Question question) throws CustomException {
        question = questionService.createQuestion(question);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

}
