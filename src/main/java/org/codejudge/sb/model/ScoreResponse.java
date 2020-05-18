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
    private List<QuesOptionMappingResponse> questions;

    public ScoreResponse(ScoreResponseBuilder builder) {
        this.score = builder.score;
        this.totalScore = builder.totalScore;
        this.questions = builder.questions;
    }

    public static class ScoreResponseBuilder {

        private Integer score;
        private Integer totalScore;
        private List<QuesOptionMappingResponse> questions;

        public ScoreResponseBuilder(Integer score, Integer totalScore, List<QuesOptionMappingResponse> questions) {
            this.score = score;
            this.totalScore = totalScore;
            this.questions = questions;
        }

        public ScoreResponse build() {
            return new ScoreResponse(this);
        }
    }

}
