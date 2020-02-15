package org.codejudge.sb.service;

import org.codejudge.sb.dao.QuestionRepository;
import org.codejudge.sb.dao.QuizRepository;
import org.codejudge.sb.entity.Question;
import org.codejudge.sb.entity.Quiz;
import org.codejudge.sb.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    public Question getQuestionById(Integer questionId) {
        return questionRepository.getQuestionById(questionId);
    }

    public Question createQuestion(Question question) throws CustomException {
        Integer quizId = question.getQuizId();
        Quiz quiz = quizRepository.getQuizById(quizId);
        if (quiz == null) {
            throw new CustomException("No quiz found!", HttpStatus.BAD_REQUEST);
        }
        try {
            Question.validate(question);
            question = questionRepository.saveAndFlush(question);
        }
        catch (Exception e) {
            throw new CustomException("Error adding question!", HttpStatus.BAD_REQUEST);
        }
        return question;
    }

    public List<Question> getQuestionsByQuizId(Integer quizId) {
        List<Question> questions = questionRepository.getQuestionsByQuizId(quizId);
        if (questions == null) {
            return new ArrayList<>();
        }
        return questions;
    }
}
