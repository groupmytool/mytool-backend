package group.mytool.flutter.flex.backend.core.entity;

import lombok.Getter;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Getter
public enum EnumModule {
    FOOD("食途：麦途食材管理","food"),
    CLEANER("清理：麦途软件清理","cleaner"),
    ;

    private String name;
    private String code;

    EnumModule(String name,String code){
        this.name = name;
        this.code = code;
    }

}
