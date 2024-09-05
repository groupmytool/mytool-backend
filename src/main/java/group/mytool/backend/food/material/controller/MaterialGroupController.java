package group.mytool.backend.food.material.controller;

import group.mytool.backend.core.entity.Result;
import group.mytool.backend.food.material.client.MaterialGroupControllerInterface;
import group.mytool.backend.food.material.entity.ro.ChildMaterialGroupQuery;
import group.mytool.backend.food.material.entity.vo.MaterialGroupChildVo;
import group.mytool.backend.food.material.entity.vo.MaterialGroupTopVo;
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
@RequestMapping("/materialGroup")
public class MaterialGroupController implements MaterialGroupControllerInterface {

  private final MaterialGroupService groupService;

  @Override
  public Result<List<MaterialGroupTopVo>> getRootMaterialGroup() {
    return Result.ok(groupService.getRootMaterialGroup());
  }

  @Override
  public Result<List<MaterialGroupChildVo>> getChildMaterialGroup(@RequestBody @Valid ChildMaterialGroupQuery query) {
    return Result.ok(groupService.getChildMaterialGroup(query.getParentId()));
  }


}
