package group.mytool.flutter.flex.backend.common.user.service;

import group.mytool.flutter.flex.backend.common.user.dao.SessionRecordDao;
import group.mytool.flutter.flex.backend.common.user.entity.convertor.SessionRecordConvertor;
import group.mytool.flutter.flex.backend.common.user.entity.po.SessionRecord;
import group.mytool.flutter.flex.backend.common.user.entity.vo.LoginTokenVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Service
@RequiredArgsConstructor
public class SessionRecordService {

  private final SessionRecordConvertor convertor;
  private final SessionRecordDao dao;

  public LoginTokenVo createLoginToken(String clientId, String userId) {
    // 删除旧的会话记录
    dao.deleteByUserId(userId);
    // 生成新的会话记录
    SessionRecord sessionRecord = new SessionRecord();
    sessionRecord.setClientId(clientId);
    sessionRecord.setUserId(userId);
    // 设置过期时间：currentTime + 365天
    LocalDateTime currentTime = LocalDateTime.now();
    LocalDateTime expireTime = currentTime.plus(365, ChronoUnit.DAYS);
    sessionRecord.setExpireTime(expireTime);
    dao.save(sessionRecord);
    // 返回会话记录
    return convertor.poToLoginToken(sessionRecord);
  }

  public SessionRecord selectById(String id) {
    return dao.getById(id);
  }

  public boolean updateFreshTimeById(SessionRecord sessionRecord) {
    return dao.updateById(sessionRecord);
  }

}
