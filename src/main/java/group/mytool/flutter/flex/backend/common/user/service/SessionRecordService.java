package group.mytool.flutter.flex.backend.common.user.service;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import group.mytool.flutter.flex.backend.common.user.entity.convertor.SessionRecordConvertor;
import group.mytool.flutter.flex.backend.common.user.entity.po.SessionRecord;
import group.mytool.flutter.flex.backend.common.user.entity.vo.LoginTokenVo;
import group.mytool.flutter.flex.backend.common.user.mapper.SessionRecordMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Service
public class SessionRecordService extends ServiceImpl<SessionRecordMapper, SessionRecord> {

  public LoginTokenVo createLoginToken(String clientId, String userId) {
    // 删除旧的会话记录
    QueryWrapper delWrapper = this.query()
        .eq(SessionRecord::getUserId, userId);
    this.remove(delWrapper);
    // 生成新的会话记录
    SessionRecord sessionRecord = new SessionRecord();
    sessionRecord.setClientId(clientId);
    sessionRecord.setUserId(userId);
    // 设置过期时间：currentTime + 365天
    LocalDateTime currentTime = LocalDateTime.now();
    LocalDateTime expireTime = LocalDateTimeUtil.offset(currentTime, 365, ChronoUnit.DAYS);
    sessionRecord.setExpireTime(expireTime);
    this.save(sessionRecord);
    // 返回会话记录
    return SessionRecordConvertor.INSTANCE.poToLoginToken(sessionRecord);
  }

}
