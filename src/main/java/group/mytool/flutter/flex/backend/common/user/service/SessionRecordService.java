package group.mytool.flutter.flex.backend.common.user.service;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
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
public class SessionRecordService {

  private final SessionRecordConvertor convertor;
  private final SessionRecordMapper mapper;

  public SessionRecordService(SessionRecordConvertor convertor, SessionRecordMapper sessionRecordMapper) {
    this.convertor = convertor;
    this.mapper = sessionRecordMapper;
  }

  public LoginTokenVo createLoginToken(String clientId, String userId) {
    // 删除旧的会话记录
    mapper.deleteByUserId(userId);
    // 生成新的会话记录
    SessionRecord sessionRecord = new SessionRecord();
    sessionRecord.setClientId(clientId);
    sessionRecord.setUserId(userId);
    // 设置过期时间：currentTime + 365天
    LocalDateTime currentTime = LocalDateTime.now();
    LocalDateTime expireTime = LocalDateTimeUtil.offset(currentTime, 365, ChronoUnit.DAYS);
    sessionRecord.setExpireTime(expireTime);
    sessionRecord.setId(IdUtil.simpleUUID());
    mapper.login(sessionRecord);
    // 返回会话记录
    return convertor.poToLoginToken(sessionRecord);
  }

  public SessionRecord selectById(String id) {
    return mapper.selectById(id);
  }

  public int updateFreshTimeById(SessionRecord sessionRecord) {
    return mapper.updateFreshTimeById(sessionRecord);
  }

}
