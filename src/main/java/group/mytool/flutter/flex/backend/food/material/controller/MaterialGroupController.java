package group.mytool.flutter.flex.backend.food.material.controller;

import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialGroupTopVo;
import group.mytool.flutter.flex.backend.food.material.service.MaterialGroupService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Validated
@RestController
@RequestMapping("/materialGroup")
public class MaterialGroupController {

  private final MaterialGroupService groupService;

  public MaterialGroupController(MaterialGroupService groupService) {
    this.groupService = groupService;
  }

  @PostMapping("/top/tree")
  public List<MaterialGroupTopVo> getMaterialGroupTopTree() {
    return groupService.getMaterialGroupTopTree();
  }


}
