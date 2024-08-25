package group.mytool.flutter.flex.backend.common.user.service;

import group.mytool.flutter.flex.backend.common.user.entity.po.User;
import group.mytool.flutter.flex.backend.common.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Service
public class UserService {

  private final UserMapper mapper;

  public UserService(UserMapper userMapper) {
    this.mapper = userMapper;
  }

  /**
   * 根据用户名查询用户是否存在
   *
   * @param username 用户名
   * @return 用户对象
   */
  public User queryByUserName(String username) {
    return mapper.selectByUsername(username);
  }

  public int deleteByUserNamePhysical(String username) {
    return mapper.deleteByUserNamePhysical(username);
  }

}
