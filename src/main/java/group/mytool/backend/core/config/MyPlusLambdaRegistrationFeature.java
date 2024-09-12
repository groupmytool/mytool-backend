package group.mytool.backend.core.config;

import group.mytool.backend.MyToolBackendApplication;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.hosted.RuntimeSerialization;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import java.lang.reflect.Modifier;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 自动注册使用 lambda 表达式的成员类<br/>
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Slf4j
public class MyPlusLambdaRegistrationFeature implements Feature {

  @SneakyThrows
  @Override
  public void duringSetup(DuringSetupAccess access) {
    ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
    // 添加包含 @SpringBootNativeApplication 注解的过滤器
    provider.addIncludeFilter(new AssignableTypeFilter(MyToolBackendApplication.class));
    Set<BeanDefinition> candidateComponents = provider.findCandidateComponents("");
    for (BeanDefinition bean : candidateComponents) {
      Class<?> clazz = Class.forName(bean.getBeanClassName());
      if (clazz.isAssignableFrom(MyToolBackendApplication.class)) {
        String packageName = clazz.getPackage().getName();
        log.debug("Found {} class, package: {}", clazz.getName(), packageName);

        log.debug("register lambda capturing classes start...");
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
        final Set<BeanDefinition> springBasePackageClass = provider.findCandidateComponents(packageName);
        for (BeanDefinition customClassDefinition : springBasePackageClass) {
          Class<?> customClass = Class.forName(customClassDefinition.getBeanClassName());
          if (!Modifier.isAbstract(customClass.getModifiers()) && !customClass.isInterface()) {
            log.debug("register lambda capturing class: {}", customClass.getName());
            RuntimeSerialization.registerLambdaCapturingClass(customClass);
          }
        }
        log.debug("register lambda capturing classes end...");
      }
    }
  }

}
