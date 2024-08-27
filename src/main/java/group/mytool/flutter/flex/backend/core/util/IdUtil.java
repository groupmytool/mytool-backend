package group.mytool.flutter.flex.backend.core.util;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
public class IdUtil {

  public static String fastUUID() {
    return java.util.UUID.randomUUID().toString();
  }

  public static String simpleUUID() {
    return java.util.UUID.randomUUID().toString().replace("-", "");
  }

}
