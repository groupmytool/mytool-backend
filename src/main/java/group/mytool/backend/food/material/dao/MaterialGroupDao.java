package group.mytool.backend.food.material.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import group.mytool.backend.core.dao.BaseDao;
import group.mytool.backend.food.material.entity.po.MaterialGroup;
import group.mytool.backend.food.material.mapper.MaterialGroupMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Repository
public class MaterialGroupDao extends BaseDao<MaterialGroupMapper, MaterialGroup> {

  public List<MaterialGroup> selectByParentId(String parentId) {
    LambdaQueryWrapper<MaterialGroup> queryWrapper = Wrappers.<MaterialGroup>lambdaQuery()
        .eq(MaterialGroup::getParentId, parentId);
    return baseMapper.selectList(queryWrapper);
  }

}
