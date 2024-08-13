package group.mytool.flutter.flex.backend.core.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Data
public class BaseEntity {

    @Id(value = KeyGenerators.uuid, keyType = KeyType.Generator)
    private String id;
    @Column(value = "was_del", isLogicDelete = true)
    private Integer wasDel;
    @Column(value = "cnt_create")
    private LocalDate cntCreate;
    @Column(value = "cnt_modified")
    private LocalDate cntModified;
    @Column(value = "operate_info", isLarge = true)
    private String operateInfo;

}
