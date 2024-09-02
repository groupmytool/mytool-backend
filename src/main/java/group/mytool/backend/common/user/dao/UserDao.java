package group.mytool.backend.common.user.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import group.mytool.backend.common.user.entity.po.User;
import group.mytool.backend.common.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Repository
@RequiredArgsConstructor
public class UserDao extends ServiceImpl<UserMapper, User> {

  private final UserMapper mapper;

  public User selectByUsername(String username) {
    LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery().eq(User::getUsername, username);
    return mapper.selectOne(queryWrapper);
  }

  public int deleteByUsername(String username) {
    LambdaUpdateWrapper<User> updateWrapper = Wrappers.<User>lambdaUpdate().eq(User::getUsername, username);
    return mapper.delete(updateWrapper);
  }

}
