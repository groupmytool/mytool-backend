package group.mytool.flutter.flex.backend.core.exception;

import lombok.Getter;

@Getter
public enum EnumGlobalError implements BaseEnumError {

    /**
     * 系统异常：0~9
     */
    SUCCESS("0", "success", "success"),
    METHOD_ERROR("002", "系统内部错误，请联系管理员!", "方法调用异常"),
    MEDIA_ERROR("003", "系统内部错误，请联系管理员!", "方法多媒体类型参数异常"),
    SQL_ERROR("004", "系统内部错误，请联系管理员!", "数据库操作SQL语句异常"),
    NULL_POINT_ERROR("006", "系统内部错误，请联系管理员!", "空指针异常"),
    BUSINESS_ERROR("009", "系统内部错误，请联系管理员!", "业务异常"),
    SYSTEM_ERROR("999", "系统内部错误，请联系管理员!", "未分类的系统异常"),

    /** 网关和权限：101~199 */
    AUTH_ILLEGAL_CERTIFICATE("101", "登录信息不正确，请确认后重试！", "用户名或密码错误！"),
    AUTH_ILLEGAL_TOKEN("102", "会话失效，请重新登录！", "TOKEN失效或非法TOKEN！"),
    AUTH_NO_AUTHORIZATION("103", "无操作权限，请联系管理员！", "禁止跨权限调用！"),
    USER_NAME_EXIST("104", "用户名已存在！", "用户名已存在"),
    NIKE_NAME_EXIST("104", "昵称已存在！", "昵称已存在"),

    /** 参数：201~299 */
    PARAM_ILLEGAL("201", "请求参数错误，请确认后重试！", ""),
    PARAM_TOO_LONG("202", "请求参数过长，请删减后重试！", ""),
    PARAM_NULL_VALUE("203", "必填参数不能为空！", ""),
    DATA_USED_ERROR("204", "数据已被使用，无法删除！"),
    UPLOAD_FILE_ERROR("290", "文件上传出错！", "文件上传出错！"),

    ;

    /**
     * 异常编码
     */
    private final String code;
    /**
     * 展示给用户的提示信息
     */
    private String msg;
    /**
     * 异常详情描述
     */
    private String detail;

    EnumGlobalError(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    EnumGlobalError(String code, String msg, String detail) {
        this.code = code;
        this.msg = msg;
        this.detail = detail;
    }

}
