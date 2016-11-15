package com.knightNote.other;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by knight on 16/2/24.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler{

    @ResponseStatus(value= HttpStatus.OK)
    public static class UserNotFoundException extends RuntimeException {}

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public Object handleConflict() {
        Map<String, Object> res = new HashMap<>();
        res.put("success",0);
        res.put("message","请先登录");
        res.put("error","000");
        return res;
    }
}
