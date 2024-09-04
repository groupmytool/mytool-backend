package group.mytool.backend.common.user.entity.util.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

  private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]+$";

  @Override
  public boolean isValid(String username, ConstraintValidatorContext context) {
    if (username == null) {
      return false;
    }
    return username.matches(USERNAME_PATTERN);
  }

}