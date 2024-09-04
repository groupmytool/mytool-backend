package group.mytool.backend.core.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 通用返回值对象
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class Val {

  private Serializable value;

  public static Val build(Serializable value) {
    return new Val(value);
  }

}
