package group.mytool.flutter.flex.backend.core.interceptor;

import group.mytool.flutter.flex.backend.common.session.entity.SessionRecord;
import group.mytool.flutter.flex.backend.common.session.service.SessionRecordService;
import group.mytool.flutter.flex.backend.core.exception.BaseRuntimeException;
import group.mytool.flutter.flex.backend.core.util.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;
import java.time.ZoneId;

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
            throw new BaseRuntimeException(AUTH_ILLEGAL_TOKEN);
        }
        // 不为空校验有效性
        SessionRecord session = sessionRecordService.getById(token);
        if (session == null) {
            throw new BaseRuntimeException(AUTH_ILLEGAL_TOKEN);
        }
        LocalDateTime freshTime = session.getFreshTime();
        long freshTimeLong = freshTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long curTimeLong = System.currentTimeMillis();
        // 会话过期时间：30分钟
        if ((curTimeLong - freshTimeLong) > session.getExpireTime() * 1000) {
            throw new BaseRuntimeException(AUTH_ILLEGAL_TOKEN);
        } else {
            // 更新刷新时间
            SessionRecord freshSession = new SessionRecord();
            freshSession.setId(token);
            freshSession.setFreshTime(LocalDateTime.now());
            sessionRecordService.updateById(freshSession);
            return true;
        }
    }

}
