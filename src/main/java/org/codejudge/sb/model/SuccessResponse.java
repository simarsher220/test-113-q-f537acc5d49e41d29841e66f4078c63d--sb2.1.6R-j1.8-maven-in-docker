package org.codejudge.sb.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SuccessResponse {

    private String status;

    public SuccessResponse() {
        this.status = "success";
    }
}
