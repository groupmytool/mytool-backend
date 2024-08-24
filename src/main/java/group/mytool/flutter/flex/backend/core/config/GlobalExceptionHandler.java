package group.mytool.flutter.flex.backend.core.config;

import group.mytool.flutter.flex.backend.core.entity.Result;
import group.mytool.flutter.flex.backend.core.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Objects;

import static group.mytool.flutter.flex.backend.core.exception.EnumGlobalError.PARAM_ILLEGAL;
import static group.mytool.flutter.flex.backend.core.exception.EnumGlobalError.SYSTEM_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(SystemException.class)
  @ResponseStatus(HttpStatus.OK)
  public Result<Map<String, String>> baseRunTimeExceptionHandler(SystemException ex) {
    log.warn("SystemException: ", ex);
    return Result.error(ex.getCode(), ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.OK)
  public Result<Map<String, String>> exceptionHandler(MethodArgumentNotValidException ex) {
    BindingResult bindingResult = ex.getBindingResult();
    if (Objects.isNull(bindingResult)) {
      return Result.error(PARAM_ILLEGAL);
    }
    FieldError fieldError = bindingResult.getFieldError();
    if (Objects.isNull(fieldError)) {
      return Result.error(PARAM_ILLEGAL);
    }
    String message = fieldError.getDefaultMessage();
    log.debug("MethodArgumentNotValidException: ", ex);
    return Result.error(PARAM_ILLEGAL.getCode(), message);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.OK)
  public Result<Map<String, String>> exceptionHandler(Exception ex) {
    log.error("Exception: ", ex);
    return Result.error(SYSTEM_ERROR);
  }

}
