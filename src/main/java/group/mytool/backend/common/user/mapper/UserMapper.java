package group.mytool.backend.common.user.mapper;

import group.mytool.backend.common.user.entity.po.User;
import group.mytool.backend.core.dao.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Mapper
public interface UserMapper extends MyBaseMapper<User> {
}
