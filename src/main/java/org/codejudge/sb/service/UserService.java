package org.codejudge.sb.service;

import lombok.extern.slf4j.Slf4j;
import org.codejudge.sb.dao.UserRepository;
import org.codejudge.sb.entity.Question;
import org.codejudge.sb.entity.Quiz;
import org.codejudge.sb.entity.User;
import org.codejudge.sb.error.CustomException;
import org.codejudge.sb.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
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
        List<Integer> quesIds = questions.stream().map(Question::getId).collect(Collectors.toList());
        List<Integer> submittedQuesIds = results.getMappings().stream().map(QuesOptionMapping::getQuesId).collect(Collectors.toList());
        Set<Integer> uniqueSubmittedIds = results.getMappings().stream().map(QuesOptionMapping::getQuesId).collect(Collectors.toSet());
        for (Integer quesId : submittedQuesIds) {
            if (!quesIds.contains(quesId)) {
                throw new CustomException("Submitted question doesn't belong to quiz questions!", HttpStatus.BAD_REQUEST);
            }
        }
        if (submittedQuesIds.size() != uniqueSubmittedIds.size()) {
            throw new CustomException("There seem to be multiple submissions for same question!", HttpStatus.BAD_REQUEST);
        }
        Map<Integer, String> quesCorrectResponse = new HashMap<>();
        for (Question question : questions) {
            String correctOption = question.getOptions().split(",")[question.getCorrectOption() - 1];
            quesCorrectResponse.put(question.getId(), correctOption);
        }
        Map<Integer, String> quesOptionResponse = new HashMap<>();
        for (QuesOptionMapping mapping : results.getMappings()) {
            quesOptionResponse.put(mapping.getQuesId(), mapping.getOptionId());
        }
        int score = 0;
        List<QuesOptionMappingResponse> qomResponse = new ArrayList<>();
        for (Question question : questions) {
            String questionCorrectResponse = quesCorrectResponse.get(question.getId());
            String questionOptionResponse = quesOptionResponse.get(question.getId());
            qomResponse.add(new QuesOptionMappingResponse.QuesOptionMappingResponseBuilder(question.getId(), questionCorrectResponse, questionOptionResponse).build());
            if (null == quesOptionResponse.get(question.getId())) {
                continue;
            }
            score += questionOptionResponse.equals(questionCorrectResponse) ? question.getPoints() : 0;
        }
//        for (QuesOptionMapping mapping : results.getMappings()) {
//            qomResponse.add(new QuesOptionMappingResponse.QuesOptionMappingResponseBuilder(mapping.getQuesId(), quesCorrectResponse.get(mapping.getQuesId()), mapping.getOptionId()).build());
//            if (null == mapping.getOptionId()) {
//                continue;
//            }
//            score += quesCorrectResponse.get(mapping.getQuesId()).equals(mapping.getOptionId()) ? quesPoints.get(mapping.getQuesId()) : 0;
//        }
        Integer totalScore = questions.stream().mapToInt(Question::getPoints).sum();
        user.setScore(score);
        userRepo.save(user);
        return new ScoreResponse.ScoreResponseBuilder(score, totalScore, qomResponse).build();
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
        for (Question question : questions) {
            question.setCorrectOption(null);
        }
        quizQuestions.setQuestions(questions);
        return quizQuestions;
    }
}
