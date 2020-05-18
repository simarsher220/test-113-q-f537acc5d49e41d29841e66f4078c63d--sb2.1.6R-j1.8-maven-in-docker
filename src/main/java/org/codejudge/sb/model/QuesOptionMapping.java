package org.codejudge.sb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class QuesOptionMapping {

    @JsonProperty("ques_id")
    private Integer quesId;

    @JsonProperty("submitted_option")
    private String optionId;

    public QuesOptionMapping(QuesOptionMappingBuilder builder) {
        this.quesId = builder.quesId;
        this.optionId = builder.optionId;
    }

    public static void saveForUpdate(List<QuesOptionMapping> mappings) {
        if (CollectionUtils.isEmpty(mappings)) {
            return;
        }
        for (QuesOptionMapping mapping : mappings) {
            mapping.validateQuesId();
        }
    }

    private void validateQuesId() {
        if (null == this.quesId) {
            throw new IllegalArgumentException("ques_id can't be empty");
        }
    }

    public static class QuesOptionMappingBuilder {

        private Integer quesId;
        private String optionId;

        public QuesOptionMappingBuilder(Integer quesId, String optionId) {
            this.quesId = quesId;
            this.optionId = optionId;
        }

        public QuesOptionMapping build() {
            return new QuesOptionMapping(this);
        }
    }
}
