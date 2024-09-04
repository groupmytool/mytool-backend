package group.mytool.backend.common.user.client;

import org.springframework.cloud.openfeign.FeignClient;

import static group.mytool.backend.FlutterFlexBackendApplicationTests.TEST_SERVICE;
import static group.mytool.backend.FlutterFlexBackendApplicationTests.TEST_URL;
import static group.mytool.backend.core.util.Constant.MEMBER_CONTROLLER;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@FeignClient(name = TEST_SERVICE, url = TEST_URL, path = MEMBER_CONTROLLER)
public interface MemberControllerClient extends MemberControllerInterface {
}
