package group.mytool.flutter.flex.backend.core.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseRuntimeException extends RuntimeException {

    /**
     * 异常编码
     */
    private String code;
    /**
     * 异常提醒消息
     */
    private String msg;
    /**
     * 异常详情描述
     */
    private String detail;

    public BaseRuntimeException(BaseEnumError error) {
        this(error, null);
    }

    public BaseRuntimeException(BaseEnumError error, Throwable cause) {
        this(error.getCode(), error.getMsg(), error.getDetail(), cause);
    }

    public BaseRuntimeException(String code, String msg) {
        this(code, msg, null, null);
    }

    public BaseRuntimeException(String code, String msg, String detail, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
        this.detail = detail;
    }


}
