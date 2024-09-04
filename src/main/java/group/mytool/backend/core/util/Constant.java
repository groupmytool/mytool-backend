package group.mytool.backend.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@NoArgsConstructor(access = AccessLevel.NONE)
public class Constant {

  public static final String FILE_PREFIX = "/file/image/";
  public static final String MEMBER_CONTROLLER = "/member";
  public static final String ERROR_MESSAGE = "系统内部错误，请联系管理员!";
  public static final Long TOKEN_EXPIRE_TIME = 365 * 24 * 60 * 60 * 1000L;

  public static final String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
  public static final String NORM_TIME_PATTERN = "HH:mm:ss";
  public static final String NORM_DATE_PATTERN = "yyyy-MM-dd";

  /** 保留账号 */
  public static final List<String> USER_OBTAIN = Arrays.asList("admin", "mytool");
  /** token请求头名称 */
  public static final String TOKEN = "token";
  /** 跟节点编码 */
  public static final String NODE_ROOT = "root";

}
