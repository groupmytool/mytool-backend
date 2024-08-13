package group.mytool.flutter.flex.backend.core.exception;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
public class SystemException extends BaseRuntimeException {

    public SystemException(String message) {
        super(EnumGlobalError.SYSTEM_ERROR.getCode(), message);
    }

    public static SystemException build(String message) {
        return new SystemException(message);
    }

}
