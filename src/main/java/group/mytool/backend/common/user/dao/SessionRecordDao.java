package group.mytool.backend.common.user.dao;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import group.mytool.backend.common.user.entity.po.SessionRecord;
import group.mytool.backend.common.user.mapper.SessionRecordMapper;
import group.mytool.backend.core.dao.BaseDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Repository
@RequiredArgsConstructor
public class SessionRecordDao extends BaseDao<SessionRecordMapper, SessionRecord> {

  private final SessionRecordMapper mapper;

  public int deleteByUserId(String userId) {
    LambdaUpdateWrapper<SessionRecord> updateWrapper = Wrappers.<SessionRecord>lambdaUpdate()
        .set(SessionRecord::getDeleted, true)
        .eq(SessionRecord::getUserId, userId)
        .eq(SessionRecord::getDeleted, false);
    return mapper.update(updateWrapper);
  }

}
