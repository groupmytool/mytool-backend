package group.mytool.backend.core.interceptor;

import group.mytool.backend.common.user.entity.po.SessionRecord;
import group.mytool.backend.common.user.service.SessionRecordService;
import group.mytool.backend.core.exception.SystemException;
import group.mytool.backend.core.util.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

import static group.mytool.backend.core.exception.EnumGlobalError.AUTH_ILLEGAL_TOKEN;

@Slf4j
@Component
public class CookieSessionInterceptor implements HandlerInterceptor {

  private final SessionRecordService sessionRecordService;

  @Value("${mytool.session.freshTime:false}")
  private boolean freshTime;

  public CookieSessionInterceptor(SessionRecordService sessionRecordService) {
    this.sessionRecordService = sessionRecordService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    // 开始校验token
    String token = SessionUtil.getToken();
    if (!StringUtils.hasText(token)) {
      throw SystemException.build(AUTH_ILLEGAL_TOKEN);
    }
    // 不为空校验有效性
    SessionRecord session = sessionRecordService.selectById(token);
    if (session == null) {
      throw SystemException.build(AUTH_ILLEGAL_TOKEN);
    }
    LocalDateTime expireTime = session.getExpireTime();
    // 判断token是否过期
    if (LocalDateTime.now().isAfter(expireTime)) {
      throw SystemException.build(AUTH_ILLEGAL_TOKEN);
    }
    // 更新最近访问时间
    if (freshTime) {
      SessionRecord freshSession = new SessionRecord();
      freshSession.setId(token);
      freshSession.setFreshTime(LocalDateTime.now());
      sessionRecordService.updateFreshTimeById(freshSession);
    }
    return true;
  }

}
