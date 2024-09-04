package group.mytool.backend.core.dao;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;

import java.util.List;

/**
 * 扩展公共模版方法
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
public class CustomSqlInjector extends DefaultSqlInjector {

  private static final String DELETE_BY_ID_PHYSICAL = "deleteByIdPhysical";
  private static final String DELETE_BY_IDS_PHYSICAL = "deleteByIdsPhysical";

  @Override
  @SuppressWarnings("deprecation")
  public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
    List<AbstractMethod> methodList = super.getMethodList(mapperClass, tableInfo);
    methodList.add(new DeleteByIdPhysical(DELETE_BY_ID_PHYSICAL));
    methodList.add(new DeleteByIdsPhysical(DELETE_BY_IDS_PHYSICAL));
    return methodList;
  }

}

