package group.mytool.backend.food.material.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import group.mytool.backend.core.dao.BaseDao;
import group.mytool.backend.food.material.entity.po.Material;
import group.mytool.backend.food.material.mapper.MaterialMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Repository
public class MaterialDao extends BaseDao<MaterialMapper, Material> {

  public List<Material> selectByGroupId(String groupId) {
    LambdaQueryWrapper<Material> queryWrapper = Wrappers.<Material>lambdaQuery()
        .eq(Material::getGroupId, groupId);
    return baseMapper.selectList(queryWrapper);
  }

  public List<Material> selectByGroupIds(List<String> groupIds) {
    LambdaQueryWrapper<Material> queryWrapper = Wrappers.<Material>lambdaQuery()
        .in(Material::getGroupId, groupIds);
    return baseMapper.selectList(queryWrapper);
  }

}
