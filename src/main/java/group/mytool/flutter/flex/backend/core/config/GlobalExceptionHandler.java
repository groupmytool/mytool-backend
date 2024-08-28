package group.mytool.flutter.flex.backend.core.config;

import group.mytool.flutter.flex.backend.core.entity.Result;
import group.mytool.flutter.flex.backend.core.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(SystemException.class)
  @ResponseStatus(HttpStatus.OK)
  public Result<Map<String, String>> baseRunTimeExceptionHandler(SystemException ex) {
    logger.warn("SystemException: ", ex);
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
    logger.debug("MethodArgumentNotValidException: ", ex);
    return Result.error(PARAM_ILLEGAL.getCode(), message);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.OK)
  public Result<Map<String, String>> exceptionHandler(Exception ex) {
    logger.error("Exception: ", ex);
    return Result.error(SYSTEM_ERROR);
  }

}
