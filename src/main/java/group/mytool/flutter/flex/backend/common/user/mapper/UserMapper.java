package group.mytool.flutter.flex.backend.common.user.mapper;

import group.mytool.flutter.flex.backend.common.user.entity.po.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Mapper
public interface UserMapper {

  @Insert("""
          insert into common_user ( id, username, svip, password, pswd_salt )
          values ( #{id}, #{username}, #{svip}, #{password}, #{pswdSalt} )
      """)
  void register(User user);

  @Select("""
          select id, username, svip, password, pswd_salt
          from common_user
          where username = #{username}
      """)
  User selectByUsername(String username);

  @Delete("""
          delete from common_user
          where username = #{username}
      """)
  int deleteByUserNamePhysical(String username);

}
