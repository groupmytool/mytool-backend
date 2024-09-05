package group.mytool.backend.core.util;

import org.springframework.lang.Nullable;

import java.util.Collection;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
public class CollectionUtil {

  public static boolean isEmpty(@Nullable Collection<?> collection) {
    return (collection == null || collection.isEmpty());
  }

  public static boolean isNotEmpty(@Nullable Collection<?> collection) {
    return !isEmpty(collection);
  }

}
