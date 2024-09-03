package group.mytool.backend.core.dao;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class DeleteByIdPhysical extends AbstractMethod {

  protected DeleteByIdPhysical(String methodName) {
    super(methodName);
  }

  @Override
  public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
    SqlMethod sqlMethod = SqlMethod.DELETE_BY_ID;
    String sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), tableInfo.getKeyColumn(), tableInfo.getKeyProperty());
    SqlSource sqlSource = this.languageDriver.createSqlSource(this.configuration, sql, Object.class);
    return this.addDeleteMappedStatement(mapperClass, methodName, sqlSource);
  }

}
