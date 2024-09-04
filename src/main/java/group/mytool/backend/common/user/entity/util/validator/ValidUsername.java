package group.mytool.backend.common.user.entity.util.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Constraint(validatedBy = UsernameValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUsername {

  String defaultMessage = "用户名不能包含特殊字符";

  String message() default defaultMessage;

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}