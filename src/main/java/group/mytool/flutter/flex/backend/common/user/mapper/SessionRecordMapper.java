package group.mytool.flutter.flex.backend.common.user.mapper;

import group.mytool.flutter.flex.backend.common.user.entity.po.SessionRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Mapper
public interface SessionRecordMapper {

  @Insert("""
          insert into common_session_record ( id, client_id, user_id, expire_time )
          values ( #{id}, #{clientId}, #{userId}, #{expireTime} )
      """)
  void login(SessionRecord sessionRecord);

  @Update("""
          update common_session_record
          set expire_time = #{expireTime}
          where id = #{id}
      """)
  int updateFreshTimeById(SessionRecord sessionRecord);

  @Select("""
          select id, client_id, user_id, login_time, fresh_time, expire_time
          from common_session_record
          where id = #{id}
      """)
  SessionRecord selectById(String id);

  @Update("""
          update common_session_record
          set was_del = true
          where was_del = false 
          and user_id = #{userId}
      """)
  int deleteByUserId(String userId);

}
