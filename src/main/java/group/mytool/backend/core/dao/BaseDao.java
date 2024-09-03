package group.mytool.backend.core.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Collection;

/**
 * 基础数据持久化模版方法类
 *
 * @param <M>
 * @param <T>
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Slf4j
public abstract class BaseDao<M extends MyBaseMapper<T>, T> extends ServiceImpl<MyBaseMapper<T>, T> {

  public int deleteByIdPhysical(Serializable id) {
    return baseMapper.deleteByIdPhysical(id);
  }

  public int deleteByIdsPhysical(Collection<Serializable> ids) {
    return baseMapper.deleteByIdsPhysical(ids);
  }

}
