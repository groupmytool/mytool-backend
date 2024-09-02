package group.mytool.backend.core.util;

import group.mytool.backend.common.user.entity.po.SessionRecord;
import group.mytool.backend.common.user.service.SessionRecordService;
import group.mytool.backend.core.exception.EnumGlobalError;
import group.mytool.backend.core.exception.SystemException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

import static group.mytool.backend.core.util.Constant.TOKEN;

@Component
public class SessionUtil {

  private static final Logger logger = LoggerFactory.getLogger(SessionUtil.class);

  private static SessionRecordService sessionRecordService;

  public static String getToken() {
    HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
    String token = request.getParameter(TOKEN);
    if (StringUtils.hasText(token)) {
      return token;
    }
    token = request.getHeader(TOKEN);
    if (StringUtils.hasText(token)) {
      return token;
    }
    Cookie[] cookies = request.getCookies();
    if (cookies != null && cookies.length > 0) {
      for (Cookie cookie : cookies) {
        if (Objects.equals(cookie.getName().toLowerCase(), TOKEN)) {
          return cookie.getValue();
        }
      }
    }
    return null;
  }

  public static String getUserId() {
    String token = getToken();
    SessionRecord record = sessionRecordService.selectById(token);
    if (record == null) {
      throw SystemException.build(EnumGlobalError.AUTH_ILLEGAL_TOKEN);
    }
    String userId = record.getUserId();
    if (StringUtils.isEmpty(userId)) {
      throw SystemException.build(EnumGlobalError.AUTH_ILLEGAL_TOKEN);
    }
    return userId;
  }

  public static String getUserIdIfPresent() {
    String token = getToken();
    if (token == null) {
      return null;
    }
    SessionRecord record = sessionRecordService.selectById(token);
    if (record == null) {
      return null;
    }
    return record.getUserId();
  }

  @Autowired
  private void setSessionRecordService(SessionRecordService sessionRecordService) {
    SessionUtil.sessionRecordService = sessionRecordService;
  }

}
