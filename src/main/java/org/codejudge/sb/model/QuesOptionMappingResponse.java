package org.codejudge.sb.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QuesOptionMappingResponse {

    @JsonProperty("ques_id")
    private Integer quesId;

    @JsonProperty("correct_option")
    private String correctOption;

    @JsonProperty("submitted_option")
    private String submittedOption;

    public QuesOptionMappingResponse(QuesOptionMappingResponseBuilder builder) {
        this.quesId = builder.quesId;
        this.submittedOption = builder.submittedOption;
        this.correctOption = builder.correctOption;
    }

    public static class QuesOptionMappingResponseBuilder {

        private Integer quesId;
        private String correctOption;
        private String submittedOption;

        public QuesOptionMappingResponseBuilder(Integer quesId, String correctOption, String submittedOption) {
            this.quesId = quesId;
            this.correctOption = correctOption;
            this.submittedOption = submittedOption;
        }

        public QuesOptionMappingResponse build() {
            return new QuesOptionMappingResponse(this);
        }
    }

}
