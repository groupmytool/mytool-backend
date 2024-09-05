package group.mytool.backend.food.material.client;

import org.springframework.cloud.openfeign.FeignClient;

import static group.mytool.backend.FlutterFlexBackendApplicationTests.TEST_URL;
import static group.mytool.backend.food.material.controller.AdminMaterialController.ADMIN_MATERIAL_CONTROLLER;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@FeignClient(name = "AdminMaterialControllerClient", url = TEST_URL, path = ADMIN_MATERIAL_CONTROLLER)
public interface AdminMaterialControllerClient extends AdminMaterialControllerInterface {
}