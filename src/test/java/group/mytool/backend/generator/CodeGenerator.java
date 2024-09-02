package group.mytool.backend.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import group.mytool.backend.core.entity.BaseEntity;

import java.io.File;
import java.sql.Types;
import java.util.Arrays;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
public class CodeGenerator {

  public static final String APP_MODULE = "common";
  public static final String TABLE_NAME = "common_user";

  public static final String[] TABLE_PREFIX = {"common_", "food_"};
  public static final String PARENT_PACKAGE = "group.mytool.backend";
  public static final String TABLE_MODEL = Arrays.stream(TABLE_PREFIX).reduce(TABLE_NAME, (name, prefix) -> name.replaceFirst(prefix, ""));
  public static final String OUTPUT_BASE_DIR = System.getProperty("user.dir") + ".src.main.java" + "." + PARENT_PACKAGE;
  public static final String OUTPUT_MODULE_DIR = (OUTPUT_BASE_DIR + "." + APP_MODULE).replace(".", File.separator);

  public static final String JDBC_URL = "jdbc:mysql://192.168.68.8:3306/mytool?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowMultiQueries=true";
  public static final String JDBC_USERNAME = "MyTool";
  public static final String JDBC_PASSWORD = "MyTool!@#123";

  public static void main(String[] args) {

    // 使用lambda表达式将 TABLE_NAME 去掉 TABLE_PREFIX 中包含的前缀
    CustomFile entityPo = getCustomFile(".java", "entity.po", "/templates/entity.po.java.ftl");
    CustomFile entityVo = getCustomFile("Vo.java", "entity.vo", "/templates/entity.vo.java.ftl");
    CustomFile entityQuery = getCustomFile("Query.java", "entity.req", "/templates/entity.query.java.ftl");
    CustomFile entityForm = getCustomFile("FormParam.java", "entity.req", "/templates/entity.form.java.ftl");
    CustomFile entityConvertor = getCustomFile("Convertor.java", "entity.convertor", "/templates/entity.convertor.java.ftl");
    CustomFile mapper = getCustomFile("Mapper.java", "mapper", "/templates/mapper.java.ftl");
    CustomFile mapperXml = getCustomFile("Mapper.xml", "mapper", "/templates/mapper.xml.ftl");
    CustomFile dao = getCustomFile("Dao.java", "dao", "/templates/dao.java.ftl");
    CustomFile service = getCustomFile("Service.java", "service", "/templates/service.java.ftl");
    CustomFile controller = getCustomFile("Controller.java", "controller", "/templates/controller.java.ftl");

    FastAutoGenerator.create(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)
        .globalConfig(builder -> {
          builder.author("MyTool") // 设置作者
              .commentDate("yyyy-MM-dd"); // 注释日期   默认值: yyyy-MM-dd

        })
        .dataSourceConfig(builder ->
            builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
              int typeCode = metaInfo.getJdbcType().TYPE_CODE;
              if (typeCode == Types.SMALLINT) {
                // 自定义类型转换
                return DbColumnType.INTEGER;
              }
              return typeRegistry.getColumnType(metaInfo);
            })
        )
        .strategyConfig(builder ->
            builder.addInclude(TABLE_NAME) // 设置需要生成的表名
                .addTablePrefix(TABLE_PREFIX) // 设置过滤表前缀
                // controller 层代码生成策略
                .controllerBuilder().disable()
                // service 层代码生成策略
                .serviceBuilder().disable()
                // mapper 层代码生成策略
                .mapperBuilder().enableBaseResultMap().disable()
                // entity 层代码生成策略
                .entityBuilder().superClass(BaseEntity.class).disable()
        )
        .injectionConfig(consumer -> {
          consumer.customFile(entityPo);
          consumer.customFile(entityVo);
          consumer.customFile(entityQuery);
          consumer.customFile(entityForm);
          consumer.customFile(entityConvertor);
          consumer.customFile(mapper);
          consumer.customFile(mapperXml);
          consumer.customFile(dao);
          consumer.customFile(service);
          consumer.customFile(controller);
        })
        .packageConfig(builder ->
            builder.parent(PARENT_PACKAGE + "." + APP_MODULE) // 设置父包名
                .moduleName(TABLE_MODEL)
        )
        .templateEngine(new EnhanceFreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
        .execute();
  }

  private static CustomFile getCustomFile(String fileName, String packageName, String templatePath) {
    CustomFile.Builder baseBuilder = new CustomFile.Builder()
        .filePath(OUTPUT_MODULE_DIR + File.separator + TABLE_MODEL)
        .enableFileOverride();
    return baseBuilder.fileName(fileName).packageName(packageName).templatePath(templatePath).build();
  }

}
