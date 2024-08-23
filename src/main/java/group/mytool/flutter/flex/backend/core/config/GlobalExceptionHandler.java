package group.mytool.flutter.flex.backend.core.config;

import group.mytool.flutter.flex.backend.core.entity.Result;
import group.mytool.flutter.flex.backend.core.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static group.mytool.flutter.flex.backend.core.exception.EnumGlobalError.SYSTEM_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result baseRunTimeExceptionHandler(SystemException ex) {
        log.error("SystemException: ", ex);
        return Result.error(ex.getCode(), ex.getMsg());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Result exceptionHandler(Exception ex) {
        log.error("Exception: ", ex);
        return Result.error(SYSTEM_ERROR);
    }

}
