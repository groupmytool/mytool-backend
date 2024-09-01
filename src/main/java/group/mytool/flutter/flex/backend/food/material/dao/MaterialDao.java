package group.mytool.flutter.flex.backend.food.material.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import group.mytool.flutter.flex.backend.food.material.entity.po.Material;
import group.mytool.flutter.flex.backend.food.material.mapper.MaterialMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Repository
public class MaterialDao extends ServiceImpl<MaterialMapper, Material> {

  public List<Material> selectByGroupIds(List<String> groupIds) {
    LambdaQueryWrapper<Material> queryWrapper = Wrappers.<Material>lambdaQuery()
        .in(Material::getGroupId, groupIds);
    return baseMapper.selectList(queryWrapper);
  }

}
