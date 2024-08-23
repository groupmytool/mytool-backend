package group.mytool.flutter.flex.backend.food.material.service;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import group.mytool.flutter.flex.backend.core.exception.BusinessException;
import group.mytool.flutter.flex.backend.food.convertor.MaterialGroupConvertor;
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
import static group.mytool.flutter.flex.backend.core.util.Constant.MATERIAL_GROUP_ROOT;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Service
public class MaterialGroupService extends ServiceImpl<MaterialGroupMapper, MaterialGroup> {

    /**
     * 获取顶层食材分组树
     *
     * @return
     */
    public List<MaterialGroupTopVo> getMaterialGroupTopTree() {
        List<MaterialGroupDto> rootList = new ArrayList<>();
        List<MaterialGroup> groupList = mapper.selectAll();
        List<MaterialGroupDto> groupVoList = MaterialGroupConvertor.INSTANCE.doToDtoList(groupList);
        HashMap<String, MaterialGroupDto> tempMap = new HashMap<>();
        for (MaterialGroupDto groupVo : groupVoList) {
            if (!Objects.equals(MATERIAL_GROUP_ROOT, groupVo.getParentId())) {
                continue;
            }
            rootList.add(groupVo);
            tempMap.put(groupVo.getId(), groupVo);
            if (CollectionUtils.isEmpty(groupVo.getChildren())) {
                groupVo.setChildren(new ArrayList<>());
            }
        }
        for (MaterialGroupDto groupVo : groupVoList) {
            if (Objects.equals(MATERIAL_GROUP_ROOT, groupVo.getParentId())) {
                continue;
            }
            MaterialGroupDto parent = tempMap.get(groupVo.getParentId());
            if (parent == null) {
                throw BusinessException.build(MATERIAL_GROUP_DATA_ERROR);
            }
            parent.getChildren().add(groupVo);
        }
        List<MaterialGroupTopVo> materialGroupTopVos = MaterialGroupConvertor.INSTANCE.dtoToTopVoList(rootList);
        materialGroupTopVos.sort(Comparator.comparingInt(MaterialGroupTopVo::getSort));
        return materialGroupTopVos;
    }

}
