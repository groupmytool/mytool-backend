package group.mytool.backend.common.user.entity.util.convertor;

import group.mytool.backend.common.user.entity.ro.RegisterParam;
import group.mytool.backend.common.user.entity.po.User;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Mapper(componentModel = SPRING)
public interface UserConvertor {

  User regParamToPo(RegisterParam regParam);

}
