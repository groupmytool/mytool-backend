package group.mytool.flutter.flex.backend.food.material.mapper;


import group.mytool.flutter.flex.backend.food.material.entity.po.MaterialGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Mapper
public interface MaterialGroupMapper {

  @Select("""
          select id, parent_id, group_name, group_sort
          from food_material_group
          where was_del = false
          order by group_sort
      """)
  List<MaterialGroup> selectAll();

}
