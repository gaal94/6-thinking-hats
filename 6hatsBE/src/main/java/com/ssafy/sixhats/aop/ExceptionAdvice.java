package com.ssafy.sixhats.aop;

import com.ssafy.sixhats.exception.UnAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity handleException(UnAuthorizedException e){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", "forbidden");
        HttpStatus status = HttpStatus.FORBIDDEN;

        return new ResponseEntity(resultMap, status);
    }

}
