package group.mytool.backend.food.material.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import group.mytool.backend.core.dao.BaseDao;
import group.mytool.backend.core.util.CollectionUtil;
import group.mytool.backend.food.material.entity.po.MaterialGroup;
import group.mytool.backend.food.material.mapper.MaterialGroupMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Repository
public class MaterialGroupDao extends BaseDao<MaterialGroupMapper, MaterialGroup> {

  public boolean existCheck(String parentId, String name) {
    Assert.notNull(parentId, "父节点分组不能为空");
    Assert.notNull(name, "分组名称不能为空");
    LambdaQueryWrapper<MaterialGroup> queryWrapper = Wrappers.<MaterialGroup>lambdaQuery()
        .eq(MaterialGroup::getParentId, parentId)
        .eq(MaterialGroup::getName, name)
        .select(MaterialGroup::getId);
    List<MaterialGroup> materialGroups = baseMapper.selectList(queryWrapper);
    return CollectionUtil.isNotEmpty(materialGroups);
  }

  public List<MaterialGroup> selectChildList(String parentId) {
    Assert.notNull(parentId, "父节点分组不能为空");
    LambdaQueryWrapper<MaterialGroup> queryWrapper = Wrappers.<MaterialGroup>lambdaQuery()
        .eq(MaterialGroup::getParentId, parentId);
    return baseMapper.selectList(queryWrapper);
  }

}
