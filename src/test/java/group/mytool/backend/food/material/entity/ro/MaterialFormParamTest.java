package group.mytool.backend.food.material.entity.ro;

import group.mytool.backend.common.BaseValidateTest;
import group.mytool.backend.core.util.IdUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

class MaterialFormParamTest extends BaseValidateTest {

  @Test
  void validateSaveParam() {
    MaterialFormParam saveParam = new MaterialFormParam();
    // success
    saveParam.setGroupId(IdUtil.simpleUUID());
    saveParam.setName("番茄");
    saveParam.setUnit("个");
    saveParam.setImageUrl("https://mytool.group/test.jpg");
    Assertions.assertTrue(validate(saveParam).isEmpty());
    // groupId
    MaterialFormParam groupIdParam = new MaterialFormParam();
    BeanUtils.copyProperties(saveParam, groupIdParam);
    groupIdParam.setGroupId(null);
    Assertions.assertEquals(validateMessage(groupIdParam), MaterialFormParam.groupIdNotNullMessage);
    groupIdParam.setGroupId("shortSize");
    Assertions.assertEquals(validateMessage(groupIdParam), MaterialFormParam.groupIdSizeMessage);
    // name
    MaterialFormParam nameParam = new MaterialFormParam();
    BeanUtils.copyProperties(saveParam, nameParam);
    nameParam.setName(null);
    Assertions.assertEquals(validateMessage(nameParam), MaterialFormParam.nameNotNullMessage);
    nameParam.setName(IdUtil.simpleUUID() + "123");
    Assertions.assertEquals(validateMessage(nameParam), MaterialFormParam.nameSizeMessage);
    // name
    MaterialFormParam unitParam = new MaterialFormParam();
    BeanUtils.copyProperties(saveParam, unitParam);
    unitParam.setUnit(null);
    Assertions.assertEquals(validateMessage(unitParam), MaterialFormParam.unitNotNullMessage);
    unitParam.setUnit(IdUtil.simpleUUID());
    Assertions.assertEquals(validateMessage(unitParam), MaterialFormParam.unitSizeMessage);
    // imageUrl
    MaterialFormParam imageUrlParam = new MaterialFormParam();
    BeanUtils.copyProperties(saveParam, imageUrlParam);
    imageUrlParam.setImageUrl(null);
    Assertions.assertEquals(validateMessage(imageUrlParam), MaterialFormParam.imageUrlNotNullMessage);
    imageUrlParam.setImageUrl(IdUtil.fastUUID() + IdUtil.fastUUID() + IdUtil.fastUUID() + IdUtil.fastUUID());
    Assertions.assertEquals(validateMessage(imageUrlParam), MaterialFormParam.imageUrlSizeMessage);
  }


}