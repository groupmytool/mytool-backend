package group.mytool.backend.food.material.client;

import group.mytool.backend.core.entity.Result;
import group.mytool.backend.food.material.entity.ro.ChildMaterialGroupQuery;
import group.mytool.backend.food.material.entity.vo.MaterialGroupChildVo;
import group.mytool.backend.food.material.entity.vo.MaterialGroupTopVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
public interface MaterialGroupControllerInterface {

  @PostMapping("/top/group")
  Result<List<MaterialGroupTopVo>> getRootMaterialGroup();

  @PostMapping("/child/group")
  Result<List<MaterialGroupChildVo>> getChildMaterialGroup(@RequestBody ChildMaterialGroupQuery query);

}
