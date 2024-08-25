package group.mytool.flutter.flex.backend.common.file.controller;

import group.mytool.flutter.flex.backend.common.file.entity.UploadFile;
import group.mytool.flutter.flex.backend.core.exception.SystemException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;

import static group.mytool.flutter.flex.backend.core.exception.EnumGlobalError.UPLOAD_FILE_ERROR;
import static group.mytool.flutter.flex.backend.core.util.Constant.FILE_PREFIX;

@RestController
@RequestMapping("/file")
public class FileController {

  @Value("${mytool.upload.path}")
  private String uploadFilePath;

  @RequestMapping("/upload")
  public UploadFile httpUpload(@RequestParam("file") MultipartFile file[]) {
    UploadFile object = new UploadFile();
    ArrayList<String> names = new ArrayList<>();
    for (int i = 0; i < file.length; i++) {
      String imageName = file[i].getOriginalFilename();
      String[] split = imageName.split("\\.");
      String type = split[split.length - 1];
      String imageRename = System.currentTimeMillis() + "." + type;
      String filePath = uploadFilePath + '/' + imageRename;
      File dest = new File(filePath);
      if (!dest.getParentFile().exists()) {
        dest.getParentFile().mkdirs();
      }
      try {
        file[i].transferTo(dest);
        names.add(FILE_PREFIX + imageRename);
      } catch (Exception e) {
        throw SystemException.build(UPLOAD_FILE_ERROR);
      }
    }
    object.setFiles(names);
    return object;
  }

}