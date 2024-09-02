package group.mytool.backend.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
public class Md5Util {
  public static String md5(String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] messageDigest = md.digest(input.getBytes());
      return HexUtil.toHex(messageDigest);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }
}
