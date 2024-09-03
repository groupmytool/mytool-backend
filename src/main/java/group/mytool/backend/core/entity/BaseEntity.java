package group.mytool.backend.core.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
public class BaseEntity {

  @TableId(value = "id", type = IdType.ASSIGN_UUID)
  private String id;

  @TableField(value = "create_time", fill = FieldFill.INSERT)
  private LocalDateTime createTime;

  @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
  private LocalDateTime updateTime;

  @TableLogic(value = "0", delval = "1")
  @TableField("deleted")
  private Boolean deleted;

  @TableField(value = "operate_info", fill = FieldFill.INSERT_UPDATE)
  private String operateInfo;

}
