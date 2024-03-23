package org.example.myojssm.config;

import org.example.myojssm.common.Result;
import org.springframework.util.StringUtils;
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
        return Result.fail(StringUtils.hasLength(e.getMessage())?e.getMessage():"操作失败");
    }
}
