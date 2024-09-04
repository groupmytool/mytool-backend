package group.mytool.backend.core.entity;

import group.mytool.backend.core.exception.EnumGlobalError;
import lombok.Getter;
import lombok.Setter;

import static group.mytool.backend.core.exception.EnumGlobalError.SUCCESS;

@Getter
@Setter
public class Result<T> {

  private String message;
  private T data;
  private Integer code;

  public Result() {
  }

  public Result(EnumGlobalError error) {
    this.code = error.getCode();
    this.message = error.getMessage();
  }

  public static <T> Result<T> ok(T data) {
    Result<T> restRes = new Result<>(SUCCESS);
    restRes.setData(data);
    return restRes;
  }

  public static  <T> Result<T> error(EnumGlobalError error) {
    return Result.error(error.getCode(), error.getMessage());
  }

  public static  <T> Result<T> error(Integer code, String message) {
    Result<T> restRes = new Result<>();
    restRes.setCode(code);
    restRes.setMessage(message);
    return restRes;
  }

  public Boolean canSuccess() {
    return SUCCESS.getCode() == this.getCode();
  }

}
