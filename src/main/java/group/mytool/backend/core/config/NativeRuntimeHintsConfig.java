package group.mytool.backend.core.config;

import group.mytool.backend.MyToolBackendApplication;
import jakarta.validation.ConstraintValidator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.reflect.Modifier;
import java.util.Set;

/**
 * 自动注册反射成员类：<br/>
 * 1、ConstraintValidator<br/>
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Slf4j
@Configuration
@ImportRuntimeHints(NativeRuntimeHintsConfig.HintsRegistrar.class)
public class NativeRuntimeHintsConfig {

  static class HintsRegistrar implements RuntimeHintsRegistrar {

    @SneakyThrows
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
      ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
      // 添加包含 @SpringBootNativeApplication 注解的过滤器
      provider.addIncludeFilter(new AssignableTypeFilter(MyToolBackendApplication.class));
      Set<BeanDefinition> candidateComponents = provider.findCandidateComponents("");
      for (BeanDefinition bean : candidateComponents) {
        Class<?> clazz = Class.forName(bean.getBeanClassName());
        if (clazz.isAssignableFrom(MyToolBackendApplication.class)) {
          String packageName = clazz.getPackage().getName();
          log.debug("Found {} class, package: {}", clazz.getName(), packageName);

          log.debug("register validator reflection classes start...");
          provider.addIncludeFilter((metadataReader, metadataReaderFactory) -> {
            try {
              Class<?> validatorClass = Class.forName(metadataReader.getClassMetadata().getClassName());
              return ConstraintValidator.class.isAssignableFrom(validatorClass);
            } catch (ClassNotFoundException e) {
              return false;
            }
          });
          final Set<BeanDefinition> springBasePackageClass = provider.findCandidateComponents(packageName);
          for (BeanDefinition customClassDefinition : springBasePackageClass) {
            Class<?> customClass = Class.forName(customClassDefinition.getBeanClassName());
            if (!Modifier.isAbstract(customClass.getModifiers()) && !customClass.isInterface()) {
              log.debug("register validator reflection class: {}", customClass.getName());
              hints.reflection().registerType(customClass, MemberCategory.values());
            }
          }
          log.debug("register validator reflection classes end...");
        }
      }
    }
  }
}
