package group.mytool.flutter.flex.backend.core.util;

import group.mytool.flutter.flex.backend.common.user.entity.po.SessionRecord;
import group.mytool.flutter.flex.backend.common.user.service.SessionRecordService;
import group.mytool.flutter.flex.backend.core.exception.EnumGlobalError;
import group.mytool.flutter.flex.backend.core.exception.SystemException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static group.mytool.flutter.flex.backend.core.util.Constant.TOKEN;

@Slf4j
@Component
public class SessionUtil {

    private static SessionRecordService sessionRecordService;

    public static String getToken() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        String token = request.getParameter(TOKEN);
        if (StringUtils.isNotEmpty(token)) {
            return token;
        }
        token = request.getHeader(TOKEN);
        if (StringUtils.isNotEmpty(token)) {
            return token;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (StringUtils.equalsIgnoreCase(cookie.getName(), TOKEN)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static String getUserId() {
        String token = getToken();
        SessionRecord record = sessionRecordService.getById(token);
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
        SessionRecord record = sessionRecordService.getById(token);
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
