package group.mytool.flutter.flex.backend.common.user.service;

import group.mytool.flutter.flex.backend.common.user.dao.UserDao;
import group.mytool.flutter.flex.backend.common.user.entity.po.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserDao userDao;

  /**
   * 根据用户名查询用户是否存在
   *
   * @param username 用户名
   * @return 用户对象
   */
  public User queryByUserName(String username) {
    return userDao.selectByUsername(username);
  }

  public int deleteByUserNamePhysical(String username) {
    return userDao.deleteByUsername(username);
  }

}
