package group.mytool.backend.common.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import group.mytool.backend.common.user.entity.po.SessionRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Mapper
public interface SessionRecordMapper extends BaseMapper<SessionRecord> {
}
