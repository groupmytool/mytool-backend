package group.mytool.backend.food.material.controller;

import group.mytool.backend.core.entity.Result;
import group.mytool.backend.core.entity.req.IdQuery;
import group.mytool.backend.food.material.entity.req.MaterialFormParam;
import group.mytool.backend.food.material.entity.req.MaterialGroupFormParam;
import group.mytool.backend.food.material.service.MaterialGroupService;
import group.mytool.backend.food.material.service.MaterialService;
import jakarta.validation.Valid;
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
@RequestMapping("/admin/material")
public class AdminController {

  private final MaterialGroupService groupService;
  private final MaterialService service;

  @PostMapping("/save")
  public Result<Boolean> save(@RequestBody @Valid MaterialFormParam form) {
    return Result.ok(service.save(form));
  }

  @PostMapping("/removeById")
  public Result<Boolean> removeById(@RequestBody @Valid IdQuery idQuery) {
    return Result.ok(service.removeById(idQuery.getId()));
  }

  @PostMapping("/group/save")
  public Result<Boolean> groupSave(@RequestBody @Valid MaterialGroupFormParam form) {
    return Result.ok(groupService.save(form));
  }

  @PostMapping("/group/removeById")
  public Result<Boolean> groupRemoveById(@RequestBody @Valid IdQuery idQuery) {
    return Result.ok(groupService.removeById(idQuery.getId()));
  }

}
