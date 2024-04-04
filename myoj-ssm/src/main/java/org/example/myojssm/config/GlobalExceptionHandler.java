package org.example.myojssm.config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.validation.ConstraintViolationException;
import org.example.myojssm.common.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-23
 * Time: 13:58
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result exceptionAdvice(Exception e) {
        e.printStackTrace();
        return Result.fail(StringUtils.hasLength(e.getMessage())?e.getMessage():"Operation failed");
    }

    @ExceptionHandler(NullPointerException.class)
    public Result nullPointerExceptionAdvice(NullPointerException e) {
        e.printStackTrace();
        return Result.fail(StringUtils.hasLength(e.getMessage())?e.getMessage():"Operation failed");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result constraintViolationExceptionAdvice(ConstraintViolationException e) {
        e.printStackTrace();
        return Result.fail(StringUtils.hasLength(e.getMessage())?"Illegal parameters":"Operation failed");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidExceptionAdvice(MethodArgumentNotValidException e) {
        e.printStackTrace();
        return Result.fail(StringUtils.hasLength(e.getMessage())?"Illegal parameters":"Operation failed");
    }

}
