package group.mytool.flutter.flex.backend.common.user.entity.convertor;

import group.mytool.flutter.flex.backend.common.user.entity.dto.RegisterParam;
import group.mytool.flutter.flex.backend.common.user.entity.po.User;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Mapper(componentModel = SPRING)
public interface UserConvertor {

  User regParamToPo(RegisterParam regParam);

}
