package group.mytool.flutter.flex.backend.food.material.service;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import group.mytool.flutter.flex.backend.core.exception.BusinessException;
import group.mytool.flutter.flex.backend.food.convertor.MaterialGroupConvertor;
import group.mytool.flutter.flex.backend.food.material.entity.model.MaterialGroup;
import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialGroupVo;
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
    public List<MaterialGroupVo> getMaterialGroupTree() {
        List<MaterialGroupVo> rootList = new ArrayList<>();
        List<MaterialGroup> groupList = mapper.selectAll();
        List<MaterialGroupVo> groupVoList = MaterialGroupConvertor.INSTANCE.doToVoList(groupList);
        HashMap<String, MaterialGroupVo> tempMap = new HashMap<>();
        for (MaterialGroupVo groupVo : groupVoList) {
            if (!Objects.equals(MATERIAL_GROUP_ROOT, groupVo.getParentId())) {
                continue;
            }
            rootList.add(groupVo);
            tempMap.put(groupVo.getId(), groupVo);
            if (CollectionUtils.isEmpty(groupVo.getChildren())) {
                groupVo.setChildren(new ArrayList<>());
            }
        }
        for (MaterialGroupVo groupVo : groupVoList) {
            if (Objects.equals(MATERIAL_GROUP_ROOT, groupVo.getParentId())) {
                continue;
            }
            MaterialGroupVo parent = tempMap.get(groupVo.getParentId());
            if (parent == null) {
                throw BusinessException.build(MATERIAL_GROUP_DATA_ERROR);
            }
            parent.getChildren().add(groupVo);
        }
        rootList.sort(Comparator.comparingInt(MaterialGroupVo::getSort));
        return rootList;
    }

}
