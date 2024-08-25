package group.mytool.flutter.flex.backend.core.util;

import java.util.Arrays;
import java.util.List;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
public class Constant {

  private Constant() {
  }

  public static final String FILE_PREFIX = "/file/image/";
  public static final String MEMBER_CONTROLLER = "/member";

  public static final String ERROR_MESSAGE = "系统内部错误，请联系管理员!";

  public static final Long TOKEN_EXPIRE_TIME = 365 * 24 * 60 * 60 * 1000L;

  /**
   * 保留账号
   */
  public static final List<String> USER_OBTAIN = Arrays.asList("admin", "mytool");

  /**
   * token请求头名称
   */
  public static final String TOKEN = "token";

  /**
   * 跟节点编码
   */
  public static final String NODE_ROOT = "root";

}
