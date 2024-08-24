package group.mytool.flutter.flex.backend.core.interceptor;

import group.mytool.flutter.flex.backend.common.user.entity.po.SessionRecord;
import group.mytool.flutter.flex.backend.common.user.service.SessionRecordService;
import group.mytool.flutter.flex.backend.core.exception.SystemException;
import group.mytool.flutter.flex.backend.core.util.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

import static group.mytool.flutter.flex.backend.core.exception.EnumGlobalError.AUTH_ILLEGAL_TOKEN;

@Slf4j
@Component
@RequiredArgsConstructor
public class CookieSessionInterceptor implements HandlerInterceptor {

  private final SessionRecordService sessionRecordService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    // 开始校验token
    String token = SessionUtil.getToken();
    if (StringUtils.isEmpty(token)) {
      throw SystemException.build(AUTH_ILLEGAL_TOKEN);
    }
    // 不为空校验有效性
    SessionRecord session = sessionRecordService.getById(token);
    if (session == null) {
      throw SystemException.build(AUTH_ILLEGAL_TOKEN);
    }
    LocalDateTime expireTime = session.getExpireTime();
    // 判断token是否过期
    if (LocalDateTime.now().isAfter(expireTime)) {
      throw SystemException.build(AUTH_ILLEGAL_TOKEN);
    }
    // 更新最近访问时间
    SessionRecord freshSession = new SessionRecord();
    freshSession.setId(token);
    freshSession.setFreshTime(LocalDateTime.now());
    sessionRecordService.updateById(freshSession);
    return true;
  }

}
