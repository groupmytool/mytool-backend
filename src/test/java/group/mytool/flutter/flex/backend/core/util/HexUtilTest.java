package group.mytool.flutter.flex.backend.core.util;

import org.junit.jupiter.api.Test;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
class HexUtilTest {

  @Test
  void test() {
    String text = "hello HexUtil";
    String md5text = Md5Util.md5(text);
    System.out.println(md5text);
  }

}