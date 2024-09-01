package group.mytool.flutter.flex.backend.generator;

import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.Map;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
public class EnhanceFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

    @Override
    public void writer(Map<String, Object> objectMap, String templatePath, File outputFile) throws Exception {
        System.out.println(objectMap);
        System.out.println(templatePath);
        System.out.println(outputFile);
        super.writer(objectMap, templatePath, outputFile);
    }

}
