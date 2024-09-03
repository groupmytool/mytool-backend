package group.mytool.backend.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;

public interface MyBaseMapper<T> extends BaseMapper<T> {

  /**
   * 物理删除
   *
   * @param id 主键ID
   * @return 影响行数
   */
  int deleteByIdPhysical(Serializable id);

  /**
   * 批量物理删除
   *
   * @param idList 主键ID列表
   * @return 影响行数
   */
  int deleteByIdsPhysical(@Param(Constants.COLL) Collection<?> idList);

}
