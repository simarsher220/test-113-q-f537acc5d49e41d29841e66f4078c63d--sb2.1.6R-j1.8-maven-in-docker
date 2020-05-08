package org.codejudge.sb.service;

import lombok.extern.slf4j.Slf4j;
import org.codejudge.sb.dao.UserRepository;
import org.codejudge.sb.entity.Question;
import org.codejudge.sb.entity.Quiz;
import org.codejudge.sb.entity.User;
import org.codejudge.sb.error.CustomException;
import org.codejudge.sb.model.QuesOptionMapping;
import org.codejudge.sb.model.QuizQuestions;
import org.codejudge.sb.model.QuizSolution;
import org.codejudge.sb.model.ScoreResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    public ScoreResponse calculateScore(QuizSolution results) throws CustomException {
        QuizSolution.validateForUpdate(results);
        User user = validateUser();
        user.setResults(results);
        List<Question> questions = questionService.getQuestionsByQuizId(results.getQuizId());
        Map<Integer, Integer> quesCorrectOption = questions.stream().collect(Collectors.toMap(Question::getId, Question::getCorrectOption));
        Map<Integer, Integer> quesPoints = questions.stream().collect(Collectors.toMap(Question::getId, Question::getPoints));
        int score = 0, totalScore = 0;
        for (QuesOptionMapping mapping : results.getMappings()) {
            if (null == mapping.getOptionId()) {
                continue;
            }
            score += quesCorrectOption.get(mapping.getQuesId()).equals(mapping.getOptionId()) ? quesPoints.get(mapping.getQuesId()) : 0;
            totalScore += quesPoints.get(mapping.getQuesId());
        }
        user.setScore(score);
        userRepo.save(user);
        List<QuesOptionMapping> correctOptions = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : quesCorrectOption.entrySet()) {
            correctOptions.add(new QuesOptionMapping.QuesOptionMappingBuilder(entry.getKey(), entry.getValue()).build());
        }
        return new ScoreResponse.ScoreResponseBuilder(score, totalScore, correctOptions, results.getMappings()).build();
    }

    public User validateUser() throws CustomException {
        String authToken = request.getHeader("auth-token");
        if (StringUtils.isEmpty(authToken)) {
            throw new CustomException("Unauthorized!", HttpStatus.UNAUTHORIZED);
        }
        User user = userRepo.getUserByHeaders(authToken);
        if (user == null) {
            throw new CustomException("Unauthorized!", HttpStatus.UNAUTHORIZED);
        }
        return user;
    }

    public List<Quiz> getAllQuiz() throws CustomException {
        User user = validateUser();
        List<Quiz> quizzes = quizService.getAllQuiz();
        if (CollectionUtils.isEmpty(quizzes)) {
            throw new CustomException("No quizzes found!", HttpStatus.NOT_FOUND);
        }
        return quizzes;
    }

    public QuizQuestions getQuizQuestions(Integer quizId) throws CustomException {
        validateUser();
        Quiz quiz = quizService.getQuizById(quizId);
        if (quiz == null) {
            throw new CustomException("Quiz not found!", HttpStatus.NOT_FOUND);
        }
        List<Question> questions = questionService.getQuestionsByQuizId(quizId);
        QuizQuestions quizQuestions = new QuizQuestions();
        quizQuestions.setName(quiz.getName());
        quizQuestions.setDescription(quiz.getDescription());
        quizQuestions.setQuestions(questions);
        return quizQuestions;
    }
}
