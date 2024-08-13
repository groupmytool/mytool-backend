package group.mytool.flutter.flex.backend.core.exception;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
public class ParamException extends BaseRuntimeException {

    public ParamException(String message) {
        super(EnumGlobalError.PARAM_ILLEGAL.getCode(), message);
    }

    public static ParamException build(String message) {
        return new ParamException(message);
    }

}
