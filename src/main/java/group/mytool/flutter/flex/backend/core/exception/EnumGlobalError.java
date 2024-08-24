package group.mytool.flutter.flex.backend.core.exception;

import lombok.Getter;

import static group.mytool.flutter.flex.backend.core.util.Constant.ERROR_MESSAGE;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Getter
public enum EnumGlobalError {

  SUCCESS(0, "success", "success"),

  // 系统异常：100~199
  METHOD_ERROR(101, ERROR_MESSAGE, "方法调用异常"),
  MEDIA_ERROR(103, ERROR_MESSAGE, "方法多媒体类型参数异常"),
  SQL_ERROR(104, ERROR_MESSAGE, "数据库操作SQL语句异常"),
  NULL_POINT_ERROR(106, ERROR_MESSAGE, "空指针异常"),
  SYSTEM_ERROR(109, ERROR_MESSAGE, "未分类的系统异常"),

  // 网关和权限：201~299
  AUTH_ILLEGAL_CERTIFICATE(201, "登录信息不正确，请确认后重试！", "用户名或密码错误！"),
  AUTH_ILLEGAL_TOKEN(202, "会话失效，请重新登录！", "TOKEN失效或非法TOKEN！"),
  AUTH_NO_AUTHORIZATION(203, "无操作权限，请联系管理员！", "禁止跨权限调用！"),

  // 参数：301~399
  PARAM_ILLEGAL(301, "请求参数错误，请确认后重试！", ""),
  UPLOAD_FILE_ERROR(320, "文件上传出错！", "文件上传出错！"),

  // 用户：901~999
  USER_NAME_OBTAIN(901, "不能注册保留账号！", "不能注册保留账号！"),
  USER_NAME_EXIST(902, "用户名已存在！", "用户名已存在"),
  NIKE_NAME_EXIST(903, "昵称已存在！", "昵称已存在"),
  LOGIN_ACCOUNT_ERROR(910, "账号或密码错误！", "账号或密码错误"),

  // 食途：1100~1199
  MATERIAL_GROUP_DATA_ERROR(1100, "食材分类数据异常", "食材分类数据异常"),
  ;

  /**
   * 异常编码
   */
  private final Integer code;
  /**
   * 展示给用户的提示信息
   */
  private final String message;
  /**
   * 异常详情描述
   */
  private String detail;

  EnumGlobalError(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  EnumGlobalError(Integer code, String message, String detail) {
    this.code = code;
    this.message = message;
    this.detail = detail;
  }

}
