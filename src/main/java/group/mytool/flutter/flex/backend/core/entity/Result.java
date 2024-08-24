package group.mytool.flutter.flex.backend.core.entity;

import group.mytool.flutter.flex.backend.core.exception.EnumGlobalError;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Map;

import static group.mytool.flutter.flex.backend.core.exception.EnumGlobalError.SUCCESS;

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

    public static <T> Result ok(T data) {
        Result restRes = new Result(SUCCESS);
        restRes.setData(data);
        return restRes;
    }

    public static Result<Map<String, String>> error(EnumGlobalError error) {
        return Result.error(error.getCode(), error.getMessage());
    }

    public static Result<Map<String, String>> error(Integer code, String message) {
        Result restRes = new Result();
        restRes.setCode(code);
        restRes.setMessage(message);
        restRes.setData(Collections.emptyMap());
        return restRes;
    }

    public Boolean canSuccess() {
        return SUCCESS.getCode() == this.getCode();
    }

}
