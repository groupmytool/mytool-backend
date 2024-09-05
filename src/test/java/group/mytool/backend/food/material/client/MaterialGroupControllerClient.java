package group.mytool.backend.food.material.client;

import org.springframework.cloud.openfeign.FeignClient;

import static group.mytool.backend.FlutterFlexBackendApplicationTests.TEST_URL;
import static group.mytool.backend.food.material.controller.MaterialGroupController.MATERIAL_GROUP_CONTROLLER;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@FeignClient(name = "MaterialGroupControllerClient", url = TEST_URL, path = MATERIAL_GROUP_CONTROLLER)
public interface MaterialGroupControllerClient extends MaterialGroupControllerInterface {
}