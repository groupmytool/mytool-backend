package group.mytool.backend.generator;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
public class EnhanceFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

  public static final String voPackage = "entity.vo";
  public static final String poPackage = "entity.po";
  public static final String roPackage = "entity.ro";
  public static final String convertorPackage = "entity.convertor";
  public static final String daoPackage = "dao";
  public static final String servicePackage = "service";
  public static final String controllerPackage = "controller";
  public static final String clientPackage = "client";
  public static final String mapperPackage = "mapper";

  public static final String voName = "Vo";
  public static final String poName = "";
  public static final String paramName = "FormParam";
  public static final String queryName = "Query";
  public static final String convertorName = "Convertor";
  public static final String daoName = "Dao";
  public static final String serviceName = "Service";
  public static final String controllerName = "Controller";
  public static final String clientName = "ControllerInterface";
  public static final String mapperName = "Mapper";

  public static final String dotJava = ".java";
  public static final String dotXml = ".xml";

  @Override
  public void writer(Map<String, Object> objectMap, String templatePath, File outputFile) throws Exception {
    HashMap newObjectMap = new HashMap();
    HashMap<Object, Object> newPackageMap = new HashMap<>();
    TableInfoExt tableInfoExt = new TableInfoExt();
    // class package
    Object packageObject = objectMap.get("package");
    if (packageObject instanceof Map packageMap) {
      newPackageMap.putAll(packageMap);
      Object parent = packageMap.get("Parent");
      newPackageMap.put("Vo", parent + "." + voPackage);
      newPackageMap.put("Po", parent + "." + poPackage);
      newPackageMap.put("Ro", parent + "." + roPackage);
      newPackageMap.put("Convertor", parent + "." + convertorPackage);
      newPackageMap.put("Dao", parent + "." + daoPackage);
      newPackageMap.put("Service", parent + "." + servicePackage);
      newPackageMap.put("Client", parent + "." + clientPackage);
      newPackageMap.put("Xml", parent + "." + mapperPackage);
    }
    // class name
    Object tableObject = objectMap.get("table");
    if (tableObject instanceof TableInfo tableInfo) {
      String entityName = tableInfo.getEntityName();
      tableInfoExt.setParamName(entityName + paramName);
      tableInfoExt.setQueryName(entityName + queryName);
      tableInfoExt.setVoName(entityName + voName);
      tableInfoExt.setConvertorName(entityName + convertorName);
      tableInfoExt.setDaoName(entityName + daoName);
      tableInfoExt.setServiceName(entityName + serviceName);
      tableInfoExt.setClientName(entityName + clientName);
    }

    newObjectMap.putAll(objectMap);
    newObjectMap.put("tableExt", tableInfoExt);
    newObjectMap.put("package", newPackageMap);

    super.writer(newObjectMap, templatePath, outputFile);
  }

}
