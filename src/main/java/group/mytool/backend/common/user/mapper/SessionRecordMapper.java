package group.mytool.backend.common.user.mapper;

import group.mytool.backend.common.user.entity.po.SessionRecord;
import group.mytool.backend.core.dao.MyBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Mapper
public interface SessionRecordMapper extends MyBaseMapper<SessionRecord> {
}
