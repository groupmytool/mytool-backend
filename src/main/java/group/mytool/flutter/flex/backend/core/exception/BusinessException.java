package group.mytool.flutter.flex.backend.core.exception;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
public class BusinessException extends BaseRuntimeException {

    public BusinessException(String message) {
        super(EnumGlobalError.BUSINESS_ERROR.getCode(), message);
    }

    public static BusinessException build(String message) {
        return new BusinessException(message);
    }

}
