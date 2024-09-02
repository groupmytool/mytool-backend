package group.mytool.backend.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
public class BaseEntity {

  @TableId(value = "id", type = IdType.ASSIGN_ID)
  private String id;

  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  @TableField("deleted")
  private Boolean deleted;

  @TableField(value = "operate_info", fill = FieldFill.INSERT_UPDATE)
  private String operateInfo;

}
