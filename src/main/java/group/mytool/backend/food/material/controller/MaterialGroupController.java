package group.mytool.backend.food.material.controller;

import group.mytool.backend.core.entity.Result;
import group.mytool.backend.food.material.client.MaterialGroupControllerInterface;
import group.mytool.backend.food.material.entity.ro.MaterialGroupChildQuery;
import group.mytool.backend.food.material.entity.vo.MaterialGroupChildVo;
import group.mytool.backend.food.material.entity.vo.MaterialGroupRootVo;
import group.mytool.backend.food.material.service.MaterialGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(MaterialGroupController.MATERIAL_GROUP_CONTROLLER)
public class MaterialGroupController implements MaterialGroupControllerInterface {

  public static final String MATERIAL_GROUP_CONTROLLER = "/materialGroup";

  private final MaterialGroupService groupService;

  @Override
  public Result<List<MaterialGroupRootVo>> getRootMaterialGroup() {
    return Result.ok(groupService.getRootMaterialGroup());
  }

  @Override
  public Result<List<MaterialGroupChildVo>> getChildMaterialGroup(@RequestBody @Valid MaterialGroupChildQuery query) {
    return Result.ok(groupService.getChildMaterialGroup(query));
  }


}
