package org.codejudge.sb.service;

import org.codejudge.sb.dao.QuizRepository;
import org.codejudge.sb.entity.Quiz;
import org.codejudge.sb.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public Quiz getQuizById(Integer quizId) {
        return quizRepository.getQuizById(quizId);
    }

    public Quiz createQuiz(Quiz quiz) throws CustomException {
        try {
            Quiz.validate(quiz);
            quiz = quizRepository.saveAndFlush(quiz);
        }
        catch (Exception e) {
            throw new CustomException("Error creating quiz", HttpStatus.BAD_REQUEST);
        }
        return quiz;
    }
}
