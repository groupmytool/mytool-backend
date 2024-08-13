package group.mytool.flutter.flex.backend.core.exception;

import lombok.Getter;

@Getter
public enum EnumGlobalError implements BaseEnumError {

    SUCCESS(0, "success", "success"),

    /** 系统异常：100~199 */
    METHOD_ERROR(101, "系统内部错误，请联系管理员!", "方法调用异常"),
    MEDIA_ERROR(103, "系统内部错误，请联系管理员!", "方法多媒体类型参数异常"),
    SQL_ERROR(104, "系统内部错误，请联系管理员!", "数据库操作SQL语句异常"),
    NULL_POINT_ERROR(106, "系统内部错误，请联系管理员!", "空指针异常"),
    SYSTEM_ERROR(109, "系统内部错误，请联系管理员!", "未分类的系统异常"),

    /** 网关和权限：201~299 */
    AUTH_ILLEGAL_CERTIFICATE(201, "登录信息不正确，请确认后重试！", "用户名或密码错误！"),
    AUTH_ILLEGAL_TOKEN(202, "会话失效，请重新登录！", "TOKEN失效或非法TOKEN！"),
    AUTH_NO_AUTHORIZATION(203, "无操作权限，请联系管理员！", "禁止跨权限调用！"),
    USER_NAME_EXIST(204, "用户名已存在！", "用户名已存在"),
    NIKE_NAME_EXIST(205, "昵称已存在！", "昵称已存在"),

    /** 参数：301~399 */
    PARAM_ILLEGAL(301, "请求参数错误，请确认后重试！", ""),
    PARAM_TOO_LONG(302, "请求参数过长，请删减后重试！", ""),
    PARAM_NULL_VALUE(303, "必填参数不能为空！", ""),
    DATA_USED_ERROR(304, "数据已被使用，无法删除！"),
    UPLOAD_FILE_ERROR(320, "文件上传出错！", "文件上传出错！"),

    /** 业务异常：401~499 */
    BUSINESS_ERROR(499, "系统内部错误，请联系管理员!", "业务异常"),

    /** 食途：1100~1199 */
    MATERIAL_GROUP_DATA_ERROR(1100, "食材分类数据异常", "食材分类数据异常"),
    ;

    /**
     * 异常编码
     */
    private final Integer code;
    /**
     * 展示给用户的提示信息
     */
    private final String msg;
    /**
     * 异常详情描述
     */
    private String detail;

    EnumGlobalError(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    EnumGlobalError(Integer code, String msg, String detail) {
        this.code = code;
        this.msg = msg;
        this.detail = detail;
    }

}
