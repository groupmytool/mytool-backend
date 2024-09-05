package group.mytool.backend.food.material.controller;

import group.mytool.backend.food.material.client.MaterialControllerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/material")
public class MaterialController implements MaterialControllerInterface {
}
