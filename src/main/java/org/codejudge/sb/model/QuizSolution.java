package org.codejudge.sb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class QuizSolution {

    @JsonProperty("quiz_id")
    private Integer quizId;
    private List<QuesOptionMapping> mappings;

    public static void validateForUpdate(QuizSolution results) {
        if (null == results) {
            throw new IllegalArgumentException("results can't be empty");
        }
        results.validateQuizId();
        QuesOptionMapping.saveForUpdate(results.getMappings());
    }

    private void validateQuizId() {
        if (null == this.quizId) {
            throw new IllegalArgumentException("quiz_id can't be empty");
        }
    }
}
