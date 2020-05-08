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
public class ScoreResponse {

    @JsonProperty("score")
    private Integer score;
    @JsonProperty("total_score")
    private Integer totalScore;
    @JsonProperty("correct_options")
    private List<QuesOptionMapping> correctOptions;
    @JsonProperty("submitted_options")
    private List<QuesOptionMapping> submittedOptions;

    public ScoreResponse(ScoreResponseBuilder builder) {
        this.score = builder.score;
        this.totalScore = builder.totalScore;
        this.correctOptions = builder.correctOptions;
        this.submittedOptions = builder.submittedOptions;
    }

    public static class ScoreResponseBuilder {

        private Integer score;
        private Integer totalScore;
        private List<QuesOptionMapping> correctOptions;
        private List<QuesOptionMapping> submittedOptions;

        public ScoreResponseBuilder(Integer score, Integer totalScore, List<QuesOptionMapping> correctOptions, List<QuesOptionMapping> submittedOptions) {
            this.score = score;
            this.totalScore = totalScore;
            this.correctOptions = correctOptions;
            this.submittedOptions = submittedOptions;
        }

        public ScoreResponse build() {
            return new ScoreResponse(this);
        }
    }

}
