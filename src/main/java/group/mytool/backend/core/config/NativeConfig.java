package group.mytool.backend.core.config;

import group.mytool.backend.common.user.entity.util.validator.PasswordValidator;
import group.mytool.backend.common.user.entity.util.validator.UsernameValidator;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;

import java.util.stream.Stream;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Configuration
@ImportRuntimeHints(NativeConfig.MyToolRuntimeHintsRegistrar.class)
public class NativeConfig {

  static class MyToolRuntimeHintsRegistrar implements RuntimeHintsRegistrar {

    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
      Stream.of(
          UsernameValidator.class,
          PasswordValidator.class
      ).forEach(clazz -> hints.reflection().registerType(clazz, MemberCategory.values()));
    }
  }


}
