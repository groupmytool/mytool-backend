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
  private final Integer code;
  /**
   * 异常提醒消息
   */
  private final String message;
  /**
   * 异常详情描述
   */
  private final String detail;

  public SystemException(EnumGlobalError error) {
    this(error, null);
  }

  public SystemException(EnumGlobalError error, Throwable cause) {
    this(error.getCode(), error.getMessage(), error.getDetail(), cause);
  }

  public SystemException(Integer code, String message) {
    this(code, message, null, null);
  }

  public SystemException(Integer code, String message, String detail, Throwable cause) {
    super(message, cause);
    this.code = code;
    this.message = message;
    this.detail = detail;
  }

  public SystemException(String message) {
    this(EnumGlobalError.SYSTEM_ERROR.getCode(), message, null, null);
  }

  public static SystemException build(EnumGlobalError error) {
    return new SystemException(error);
  }

  public static SystemException build(String message) {
    return new SystemException(message);
  }

}
