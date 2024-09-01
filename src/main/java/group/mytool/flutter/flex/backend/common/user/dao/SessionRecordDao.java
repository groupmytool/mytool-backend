package group.mytool.flutter.flex.backend.common.user.dao;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import group.mytool.flutter.flex.backend.common.user.entity.po.SessionRecord;
import group.mytool.flutter.flex.backend.common.user.mapper.SessionRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Repository
@RequiredArgsConstructor
public class SessionRecordDao extends ServiceImpl<SessionRecordMapper, SessionRecord> {

  private final SessionRecordMapper mapper;

  public int deleteByUserId(String userId) {
    LambdaUpdateWrapper<SessionRecord> updateWrapper = Wrappers.<SessionRecord>lambdaUpdate()
        .set(SessionRecord::getDeleted, true)
        .eq(SessionRecord::getUserId, userId)
        .eq(SessionRecord::getDeleted, false);
    return mapper.update(updateWrapper);
  }

}
