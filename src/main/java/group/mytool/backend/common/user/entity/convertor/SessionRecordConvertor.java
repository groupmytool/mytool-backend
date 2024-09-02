package group.mytool.backend.common.user.entity.convertor;

import group.mytool.backend.common.user.entity.po.SessionRecord;
import group.mytool.backend.common.user.entity.vo.LoginTokenVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Mapper(componentModel = SPRING)
public interface SessionRecordConvertor {

  @Mapping(source = "id", target = "token")
  LoginTokenVo poToLoginToken(SessionRecord sessionRecord);

}
