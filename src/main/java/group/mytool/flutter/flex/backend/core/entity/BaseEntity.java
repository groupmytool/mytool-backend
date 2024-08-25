package group.mytool.flutter.flex.backend.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Data
public class BaseEntity {

  @Id
  private String id;
  @Column(name = "was_del")
  private Boolean wasDel;
  @Column(name = "cnt_create")
  private LocalDate cntCreate;
  @Column(name = "cnt_modified")
  private LocalDate cntModified;
  @Column(name = "operate_info")
  private String operateInfo;

}
