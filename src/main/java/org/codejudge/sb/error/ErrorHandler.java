package org.codejudge.sb.error;

import org.codejudge.sb.model.EmptyResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(0)
public class ErrorHandler {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity handleCustomException(CustomException e) {
        if (e.getHttpStatus() == HttpStatus.NOT_FOUND) {
            return new ResponseEntity<>(new EmptyResponse(), e.getHttpStatus());
        }
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, e.getHttpStatus());
    }

//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity handleException(Exception e) {
//        ErrorResponse response = new ErrorResponse(e.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//    }



}
