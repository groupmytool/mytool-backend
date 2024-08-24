package group.mytool.flutter.flex.backend.common.user.entity.convertor;

import group.mytool.flutter.flex.backend.common.user.entity.po.SessionRecord;
import group.mytool.flutter.flex.backend.common.user.entity.vo.LoginTokenVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Mapper
public interface SessionRecordConvertor {

  SessionRecordConvertor INSTANCE = Mappers.getMapper(SessionRecordConvertor.class);

  @Mapping(source = "id", target = "token")
  LoginTokenVo poToLoginToken(SessionRecord sessionRecord);

}
