package group.mytool.flutter.flex.backend.common.file.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Data
public class UploadFile {

  @Schema(description = "文件名")
  private ArrayList<String> files;

}
