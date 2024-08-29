package group.mytool.flutter.flex.backend.food.material.controller;

import group.mytool.flutter.flex.backend.core.entity.Result;
import group.mytool.flutter.flex.backend.food.material.entity.req.ChildMaterialGroupQuery;
import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialGroupChildVo;
import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialGroupTopVo;
import group.mytool.flutter.flex.backend.food.material.service.MaterialGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/materialGroup")
public class MaterialGroupController {

  private final MaterialGroupService groupService;

  @PostMapping("/top/group")
  public Result<List<MaterialGroupTopVo>> getRootMaterialGroup() {
    return Result.ok(groupService.getRootMaterialGroup());
  }

  @PostMapping("/child/group")
  public Result<List<MaterialGroupChildVo>> getChildMaterialGroup(@RequestBody ChildMaterialGroupQuery query) {
    return Result.ok(groupService.getChildMaterialGroup(query.getParentId()));
  }


}
