package group.mytool.flutter.flex.backend.core.exception;

import lombok.Getter;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Getter
public class SystemException extends RuntimeException {

    /**
     * 异常编码
     */
    private Integer code;
    /**
     * 异常提醒消息
     */
    private String msg;
    /**
     * 异常详情描述
     */
    private String detail;

    public SystemException(BaseEnumError error) {
        this(error, null);
    }

    public SystemException(BaseEnumError error, Throwable cause) {
        this(error.getCode(), error.getMsg(), error.getDetail(), cause);
    }

    public SystemException(Integer code, String msg) {
        this(code, msg, null, null);
    }

    public SystemException(Integer code, String msg, String detail, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
        this.detail = detail;
    }

    public static SystemException build(BaseEnumError error) {
        return new SystemException(error);
    }

    public SystemException(String message) {
        this(EnumGlobalError.SYSTEM_ERROR.getCode(), message, null, null);
    }

    public static SystemException build(String message) {
        return new SystemException(message);
    }

}
