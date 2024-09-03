package group.mytool.backend.common.user.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import group.mytool.backend.common.user.entity.po.User;
import group.mytool.backend.common.user.mapper.UserMapper;
import group.mytool.backend.core.dao.BaseDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Repository
@RequiredArgsConstructor
public class UserDao extends BaseDao<UserMapper, User> {

  public User selectByUsername(String username) {
    LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery().eq(User::getUsername, username);
    return baseMapper.selectOne(queryWrapper);
  }

}
