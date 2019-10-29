package org.codejudge.sb.error;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

    private String status;
    private String reason;

    public ErrorResponse(String reason) {
        this.status = "failure";
        this.reason = reason;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
