package group.mytool.backend.food.material.controller;

import group.mytool.backend.core.entity.Result;
import group.mytool.backend.core.entity.ro.IdQuery;
import group.mytool.backend.core.entity.vo.Val;
import group.mytool.backend.core.util.validate.ValidationSequence;
import group.mytool.backend.food.material.client.AdminMaterialControllerInterface;
import group.mytool.backend.food.material.entity.ro.MaterialFormParam;
import group.mytool.backend.food.material.entity.ro.MaterialGroupFormParam;
import group.mytool.backend.food.material.service.MaterialGroupService;
import group.mytool.backend.food.material.service.MaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(AdminMaterialController.ADMIN_MATERIAL_CONTROLLER)
public class AdminMaterialController implements AdminMaterialControllerInterface {

  public static final String ADMIN_MATERIAL_CONTROLLER = "/admin/material";

  private final MaterialGroupService groupService;
  private final MaterialService service;

  @Override
  public Result<Val> saveOrUpdate(@RequestBody @Valid MaterialFormParam form) {
    return Result.ok(service.saveOrUpdate(form));
  }

  @Override
  public Result<Val> removeById(@RequestBody @Valid IdQuery idQuery) {
    return Result.ok(service.removeById(idQuery.getId()));
  }

  @Override
  public Result<Val> groupSaveOrUpdate(@RequestBody @Validated(ValidationSequence.class) MaterialGroupFormParam form) {
    return Result.ok(groupService.saveOrUpdate(form));
  }

  @Override
  public Result<Val> groupRemoveById(@RequestBody @Valid IdQuery idQuery) {
    return Result.ok(groupService.removeById(idQuery.getId()));
  }

}
