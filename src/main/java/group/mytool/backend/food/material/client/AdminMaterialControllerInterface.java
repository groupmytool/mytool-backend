package group.mytool.backend.food.material.client;

import group.mytool.backend.core.entity.Result;
import group.mytool.backend.core.entity.ro.IdQuery;
import group.mytool.backend.core.entity.vo.Val;
import group.mytool.backend.food.material.entity.ro.MaterialFormParam;
import group.mytool.backend.food.material.entity.ro.MaterialGroupFormParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
public interface AdminMaterialControllerInterface {

  @PostMapping("/save")
  Result<Val> saveOrUpdate(@RequestBody MaterialFormParam form);

  @PostMapping("/removeById")
  Result<Val> removeById(@RequestBody IdQuery idQuery);

  @PostMapping("/group/save")
  Result<Val> groupSaveOrUpdate(@RequestBody MaterialGroupFormParam form);

  @PostMapping("/group/removeById")
  Result<Val> groupRemoveById(@RequestBody IdQuery idQuery);

}
