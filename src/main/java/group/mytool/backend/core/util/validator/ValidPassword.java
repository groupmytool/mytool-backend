package group.mytool.backend.core.util.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

  String defaultMessage = "密码不合法：必须包含大小写字母和数字";

  String message() default defaultMessage;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}