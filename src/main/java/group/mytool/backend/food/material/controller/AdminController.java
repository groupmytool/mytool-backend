package group.mytool.backend.food.material.controller;

import group.mytool.backend.core.entity.Result;
import group.mytool.backend.food.material.entity.req.MaterialGroupFromParam;
import group.mytool.backend.food.material.service.MaterialGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

  private final MaterialGroupService groupService;

  @PostMapping("/material/save")
  public Result<Boolean> save(@RequestBody MaterialGroupFromParam form) {
    return Result.ok(groupService.save(form));
  }

}
