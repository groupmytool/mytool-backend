package group.mytool.flutter.flex.backend.common.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import group.mytool.flutter.flex.backend.common.user.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
