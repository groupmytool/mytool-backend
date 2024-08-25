package group.mytool.flutter.flex.backend.food.material.service;

import group.mytool.flutter.flex.backend.core.exception.SystemException;
import group.mytool.flutter.flex.backend.food.material.entity.convertor.MaterialGroupConvertor;
import group.mytool.flutter.flex.backend.food.material.entity.dto.MaterialGroupDto;
import group.mytool.flutter.flex.backend.food.material.entity.po.MaterialGroup;
import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialGroupTopVo;
import group.mytool.flutter.flex.backend.food.material.mapper.MaterialGroupMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static group.mytool.flutter.flex.backend.core.exception.EnumGlobalError.MATERIAL_GROUP_DATA_ERROR;
import static group.mytool.flutter.flex.backend.core.util.Constant.NODE_ROOT;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Service
public class MaterialGroupService {

  private final MaterialGroupConvertor convertor;
  private final MaterialGroupMapper mapper;

  public MaterialGroupService(MaterialGroupConvertor convertor,
                              MaterialGroupMapper materialGroupMapper) {
    this.convertor = convertor;
    this.mapper = materialGroupMapper;
  }

  /**
   * 获取顶层食材分组树
   *
   * @return
   */
  public List<MaterialGroupTopVo> getMaterialGroupTopTree() {
    List<MaterialGroupDto> rootList = new ArrayList<>();
    List<MaterialGroup> groupList = mapper.selectAll();
    List<MaterialGroupDto> groupVoList = convertor.doToDtoList(groupList);
    HashMap<String, MaterialGroupDto> tempMap = new HashMap<>();
    for (MaterialGroupDto groupVo : groupVoList) {
      if (!Objects.equals(NODE_ROOT, groupVo.getParentId())) {
        continue;
      }
      rootList.add(groupVo);
      tempMap.put(groupVo.getId(), groupVo);
      if (CollectionUtils.isEmpty(groupVo.getChildren())) {
        groupVo.setChildren(new ArrayList<>());
      }
    }
    for (MaterialGroupDto groupVo : groupVoList) {
      if (Objects.equals(NODE_ROOT, groupVo.getParentId())) {
        continue;
      }
      MaterialGroupDto parent = tempMap.get(groupVo.getParentId());
      if (parent == null) {
        throw SystemException.build(MATERIAL_GROUP_DATA_ERROR);
      }
      parent.getChildren().add(groupVo);
    }
    List<MaterialGroupTopVo> materialGroupTopVos = convertor.dtoToTopVoList(rootList);
    materialGroupTopVos.sort(Comparator.comparingInt(MaterialGroupTopVo::getSort));
    return materialGroupTopVos;
  }

}
