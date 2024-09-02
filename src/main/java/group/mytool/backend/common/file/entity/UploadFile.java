package group.mytool.backend.common.file.entity;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Data
public class UploadFile {

  /** 文件名 */
  private ArrayList<String> files;

}
