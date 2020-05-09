package org.codejudge.sb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codejudge.sb.error.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Entity
@Table(name = "question")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Question {

    private Integer id;
    private String name;
    private String image;
    private String options;
    private Integer correctOption;
    private Integer quizId;
    private Integer points;

    public Question() {
    }

    @Id
    @Column(name = "id")
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "options", nullable = false)
    @JsonProperty("options")
    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @Column(name = "correct_option", nullable = false)
    @JsonProperty("correct_option")
    public Integer getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(Integer correctOption) {
        this.correctOption = correctOption;
    }

    @JsonProperty("quiz")
    @Column(name = "quiz_id", nullable = false)
    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    @Column(name = "points", nullable = false)
    @JsonProperty("points")
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static void validate(Question question) throws CustomException {
        question.validateOptions();
        question.validateName();
        question.validateCorrectOption();
        question.validatePoints();

    }

    private void validateName() throws CustomException {
        if (StringUtils.isEmpty(name)) {
            throw new CustomException("Name cannot be empty", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateOptions() throws CustomException {
        if (StringUtils.isEmpty(options)) {
            throw new CustomException("options cannot be empty", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateCorrectOption() throws CustomException {
        if (null == correctOption) {
            throw new CustomException("correct option cannot be empty", HttpStatus.BAD_REQUEST);
        }
    }

    private void validatePoints() throws CustomException {
        if (null == points) {
            throw new CustomException("points cannot be empty", HttpStatus.BAD_REQUEST);
        }
    }
}
