package group.mytool.backend.core.dao;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class DeleteByIdsPhysical extends AbstractMethod {

  protected DeleteByIdsPhysical(String methodName) {
    super(methodName);
  }

  @Override
  public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
    SqlMethod sqlMethod = SqlMethod.DELETE_BY_IDS;
    String convertChoose = SqlScriptUtils.convertForeach(SqlScriptUtils.convertChoose("@org.apache.ibatis.type.SimpleTypeRegistry@isSimpleType(item.getClass())", "#{item}", "#{item." + tableInfo.getKeyProperty() + "}"), "coll", (String) null, "item", ",");
    String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), tableInfo.getKeyColumn(), convertChoose);
    SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, Object.class);
    return this.addDeleteMappedStatement(mapperClass, methodName, sqlSource);
  }

}
