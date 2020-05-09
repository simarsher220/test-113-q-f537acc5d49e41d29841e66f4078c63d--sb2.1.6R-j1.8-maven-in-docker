package org.codejudge.sb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.codejudge.sb.error.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Entity
@Table(name = "quiz")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quiz {

    private Integer id;
    private String name;
    private String description;

    public Quiz() {
    }

    @Column(name = "id")
    @JsonProperty("id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name")
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static void validate(Quiz quiz) throws CustomException {
        quiz.validateName();
        quiz.validateDescription();
    }

    private void validateDescription() throws CustomException {
        if (StringUtils.isEmpty(description)) {
            throw new CustomException("Description cannot be empty", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateName() throws CustomException {
        if (StringUtils.isEmpty(name)) {
            throw new CustomException("Name cannot be empty", HttpStatus.BAD_REQUEST);
        }
    }
}
