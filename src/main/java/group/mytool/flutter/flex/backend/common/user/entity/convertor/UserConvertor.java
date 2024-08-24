package group.mytool.flutter.flex.backend.common.user.entity.convertor;

import group.mytool.flutter.flex.backend.common.user.entity.po.User;
import group.mytool.flutter.flex.backend.common.user.entity.req.RegisterParam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Mapper
public interface UserConvertor {

  UserConvertor INSTANCE = Mappers.getMapper(UserConvertor.class);

  User regParamToPo(RegisterParam regParam);

}
