package group.mytool.flutter.flex.backend.food.material.controller;

import group.mytool.flutter.flex.backend.food.material.entity.vo.MaterialGroupVo;
import group.mytool.flutter.flex.backend.food.material.service.MaterialGroupService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/materialGroup")
public class MaterialGroupController {

    private final MaterialGroupService groupService;

    @PostMapping("/tree")
    public List<MaterialGroupVo> getMaterialGroupTree() {
        return groupService.getMaterialGroupTree();
    }


}
