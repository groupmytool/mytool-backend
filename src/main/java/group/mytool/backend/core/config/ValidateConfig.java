package group.mytool.backend.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * 参数校验配置
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Configuration
public class ValidateConfig {

  /**
   * 快速失败模式，校验失败即停止，参考：<br/>
   * <a href="https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/#section-fail-fast">
   * Hibernate Validator: Fail fast mode
   * </a><br/>
   * 此配置针对项目运行期间的校验规则配置，单元测试的配置详见：{@link group.mytool.backend.common.BaseValidateTest#validator}
   *
   * @return LocalValidatorFactoryBean
   */
  @Bean
  public LocalValidatorFactoryBean getValidatorFactory() {
    LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
    localValidatorFactoryBean.getValidationPropertyMap().put("hibernate.validator.fail_fast", "true");
    return localValidatorFactoryBean;
  }

}
