package group.mytool.backend.core.util;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
public class IdUtil {

  public static String fastUUID() {
    return java.util.UUID.randomUUID().toString();
  }

  public static String simpleUUID() {
    return java.util.UUID.randomUUID().toString().replace("-", "");
  }

}
