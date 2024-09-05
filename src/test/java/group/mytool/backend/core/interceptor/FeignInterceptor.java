package group.mytool.backend.core.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import group.mytool.backend.common.user.dao.UserDao;
import group.mytool.backend.common.user.entity.po.User;
import group.mytool.backend.common.user.entity.ro.LoginParam;
import group.mytool.backend.common.user.entity.ro.RegisterParam;
import group.mytool.backend.common.user.entity.vo.LoginTokenVo;
import group.mytool.backend.common.user.service.MemberService;
import group.mytool.backend.core.exception.EnumGlobalError;
import group.mytool.backend.core.exception.SystemException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static group.mytool.backend.core.util.Constant.MEMBER_CONTROLLER;
import static group.mytool.backend.core.util.Constant.TOKEN;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Component
@RequiredArgsConstructor
public class FeignInterceptor implements RequestInterceptor {

  private final MemberService memberService;
  private final UserDao userDao;

  private static final String CLIENT_ID = "junit-client";
  private static final String USERNAME = "junit";
  private static final String PASSWORD = "Junit!@#123";
  private String token;

  @Override
  public void apply(RequestTemplate requestTemplate) {
    synchronized (this) {
      // 放行端口不需要token
      if (requestTemplate.feignTarget().url().endsWith(MEMBER_CONTROLLER)) {
        return;
      }
      if (token == null) {
        LoginParam loginParam = new LoginParam();
        loginParam.setClientId(CLIENT_ID);
        loginParam.setUsername(USERNAME);
        loginParam.setPassword(PASSWORD);
        LoginTokenVo tokenVo;
        try {
          tokenVo = memberService.login(loginParam);
        } catch (SystemException e) {
          if (e.getCode() == EnumGlobalError.AUTH_ILLEGAL_CERTIFICATE.getCode()) {
            User user = userDao.selectByUsername(USERNAME);
            if (Objects.nonNull(user)) {
              int cnt = userDao.deleteByIdPhysical(user.getId());
              if (cnt < 1) {
                throw SystemException.build(EnumGlobalError.SYSTEM_ERROR);
              }
            }
            RegisterParam registerParam = new RegisterParam();
            registerParam.setUsername(USERNAME);
            registerParam.setPassword(PASSWORD);
            boolean register = memberService.register(registerParam);
            if (!register) {
              throw SystemException.build(EnumGlobalError.SYSTEM_ERROR);
            }
            tokenVo = memberService.login(loginParam);
          } else {
            throw SystemException.build(EnumGlobalError.SYSTEM_ERROR);
          }
        }
        token = tokenVo.getToken();
      }
      requestTemplate.header(TOKEN, token);
    }
  }

}
