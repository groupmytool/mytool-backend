package group.mytool.backend.food.material.client;

import group.mytool.backend.core.entity.Result;
import group.mytool.backend.food.material.entity.ro.MaterialGroupChildQuery;
import group.mytool.backend.food.material.entity.vo.MaterialGroupChildVo;
import group.mytool.backend.food.material.entity.vo.MaterialGroupRootVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
public interface MaterialGroupControllerInterface {

  @PostMapping("/top/group")
  Result<List<MaterialGroupRootVo>> getRootMaterialGroup();

  @PostMapping("/child/group")
  Result<List<MaterialGroupChildVo>> getChildMaterialGroup(@RequestBody MaterialGroupChildQuery query);

}
