package group.mytool.backend.core.util.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

  private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {
    if (password == null) {
      return false;
    }
    return password.matches(PASSWORD_PATTERN);
  }

}