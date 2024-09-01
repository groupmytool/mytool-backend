package group.mytool.flutter.flex.backend.food.material.service;

import group.mytool.flutter.flex.backend.food.material.dao.MaterialDao;
import group.mytool.flutter.flex.backend.food.material.dao.MaterialGroupDao;
import group.mytool.flutter.flex.backend.food.material.entity.convertor.MaterialConvertor;
import group.mytool.flutter.flex.backend.food.material.entity.convertor.MaterialGroupConvertor;
import group.mytool.flutter.flex.backend.food.material.entity.po.Material;
import group.mytool.flutter.flex.backend.food.material.entity.po.MaterialGroup;
import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialGroupChildVo;
import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialGroupTopVo;
import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static group.mytool.flutter.flex.backend.core.util.Constant.NODE_ROOT;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MaterialGroupService {

  private final MaterialGroupConvertor convertor;
  private final MaterialConvertor materialConvertor;
  private final MaterialGroupDao dao;
  private final MaterialDao materialDao;

  public boolean save(MaterialGroup group) {
    return dao.save(group);
  }

  /**
   * 获取顶层食材分组列表
   *
   * @return
   */
  public List<MaterialGroupTopVo> getRootMaterialGroup() {
    List<MaterialGroup> groupList = dao.selectByParentId(NODE_ROOT);
    List<MaterialGroupTopVo> materialGroupTopVos = convertor.poToTopVoList(groupList);
    materialGroupTopVos.sort(Comparator.comparingInt(MaterialGroupTopVo::getSort));
    return materialGroupTopVos;
  }

  /**
   * 获取二级食材分组以及分组下的食材列表
   *
   * @return
   */
  public List<MaterialGroupChildVo> getChildMaterialGroup(String parentId) {
    List<MaterialGroup> groupList = dao.selectByParentId(parentId);
    List<MaterialGroupChildVo> materialGroupTopVos = convertor.poToChildVoList(groupList);
    if (materialGroupTopVos.isEmpty()) {
      return materialGroupTopVos;
    }
    List<String> groupIds = materialGroupTopVos.stream().map(MaterialGroupChildVo::getId).toList();
    List<Material> allMaterials = materialDao.selectByGroupIds(groupIds);
    // 实现如下逻辑：materials按照groupId分组，返回Map<String, List<Material>>
    Map<String, List<Material>> tempMap = allMaterials.stream().collect(Collectors.groupingBy(Material::getGroupId));
    for (MaterialGroupChildVo materialGroupChildVo : materialGroupTopVos) {
      List<Material> groupMaterial = tempMap.get(materialGroupChildVo.getId());
      if (CollectionUtils.isEmpty(groupMaterial)) {
        continue;
      }
      List<MaterialVo> materialVos = materialConvertor.doToVoList(groupMaterial);
      materialGroupChildVo.setMaterials(materialVos);
    }
    materialGroupTopVos.sort(Comparator.comparingInt(MaterialGroupChildVo::getSort));
    return materialGroupTopVos;
  }

}
