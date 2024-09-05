package group.mytool.backend.food.material.service;

import group.mytool.backend.core.exception.SystemException;
import group.mytool.backend.core.util.CollectionUtil;
import group.mytool.backend.core.util.StringUtil;
import group.mytool.backend.food.material.dao.MaterialDao;
import group.mytool.backend.food.material.dao.MaterialGroupDao;
import group.mytool.backend.food.material.entity.convertor.MaterialConvertor;
import group.mytool.backend.food.material.entity.convertor.MaterialGroupConvertor;
import group.mytool.backend.food.material.entity.po.Material;
import group.mytool.backend.food.material.entity.po.MaterialGroup;
import group.mytool.backend.food.material.entity.ro.MaterialGroupChildQuery;
import group.mytool.backend.food.material.entity.vo.MaterialGroupChildVo;
import group.mytool.backend.food.material.entity.vo.MaterialGroupRootVo;
import group.mytool.backend.food.material.entity.vo.MaterialVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static group.mytool.backend.core.exception.EnumGlobalError.DATA_EXIST;
import static group.mytool.backend.core.exception.EnumGlobalError.MATERIAL_GROUP_DATA_ERROR;
import static group.mytool.backend.core.exception.EnumGlobalError.USED_CANNOT_DELETE;
import static group.mytool.backend.core.util.Constant.NODE_ROOT;

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

  public Boolean saveOrUpdate(MaterialGroup group) {
    // 并发控制，避免添加重名分类
    synchronized (MaterialGroupService.class) {
      // 父节点：为空则添加为跟节点，不为空需校验数据库是否存在
      if (StringUtil.isEmpty(group.getId()) && StringUtil.isEmpty(group.getParentId())) {
        group.setParentId(NODE_ROOT);
      } else {
        Optional<MaterialGroup> materialGroup = dao.getOptById(group.getParentId());
        if (materialGroup.isEmpty()) {
          throw SystemException.build(MATERIAL_GROUP_DATA_ERROR);
        }
      }
      // 重名校验
      boolean exist = dao.existCheck(group.getParentId(), group.getName());
      if (exist) {
        throw SystemException.build(DATA_EXIST);
      }
      return dao.saveOrUpdate(group);
    }
  }

  public Boolean removeById(String id) {
    // 有子节点不能删除
    List<MaterialGroup> childList = dao.selectChildList(id);
    if (CollectionUtil.isNotEmpty(childList)) {
      throw SystemException.build(USED_CANNOT_DELETE);
    }
    // 被食材关联不能删除
    List<Material> materialList = materialDao.selectByGroupId(id);
    if (CollectionUtil.isNotEmpty(materialList)) {
      throw SystemException.build(USED_CANNOT_DELETE);
    }
    return dao.removeById(id);
  }

  /**
   * 获取顶层食材分组列表
   *
   * @return
   */
  public List<MaterialGroupRootVo> getRootMaterialGroup() {
    List<MaterialGroup> groupList = dao.selectChildList(NODE_ROOT);
    List<MaterialGroupRootVo> materialGroupRootVos = convertor.poToTopVoList(groupList);
    materialGroupRootVos.sort(Comparator.comparingInt(MaterialGroupRootVo::getSort));
    return materialGroupRootVos;
  }

  /**
   * 获取二级食材分组以及分组下的食材列表
   *
   * @return
   */
  public List<MaterialGroupChildVo> getChildMaterialGroup(MaterialGroupChildQuery query) {
    List<MaterialGroup> groupList = dao.selectChildList(query.getParentId());
    List<MaterialGroupChildVo> materialGroupChildVos = convertor.poToChildVoList(groupList);
    if (materialGroupChildVos.isEmpty()) {
      return materialGroupChildVos;
    }
    List<String> groupIds = materialGroupChildVos.stream().map(MaterialGroupChildVo::getId).toList();
    List<Material> allMaterials = materialDao.selectByGroupIds(groupIds);
    Map<String, List<Material>> tempMap = allMaterials.stream().collect(Collectors.groupingBy(Material::getGroupId));
    for (MaterialGroupChildVo materialGroupChildVo : materialGroupChildVos) {
      List<Material> groupMaterial = tempMap.get(materialGroupChildVo.getId());
      if (CollectionUtils.isEmpty(groupMaterial)) {
        continue;
      }
      List<MaterialVo> materialVos = materialConvertor.doToVoList(groupMaterial);
      materialGroupChildVo.setMaterials(materialVos);
    }
    materialGroupChildVos.sort(Comparator.comparingInt(MaterialGroupChildVo::getSort));
    return materialGroupChildVos;
  }

}
