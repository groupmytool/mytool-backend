package group.mytool.backend.food.material.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import group.mytool.backend.common.BaseValidateTest;
import group.mytool.backend.core.entity.Result;
import group.mytool.backend.core.entity.ro.IdQuery;
import group.mytool.backend.core.entity.vo.Val;
import group.mytool.backend.core.util.IdUtil;
import group.mytool.backend.food.material.client.AdminMaterialControllerClient;
import group.mytool.backend.food.material.client.MaterialGroupControllerClient;
import group.mytool.backend.food.material.dao.MaterialDao;
import group.mytool.backend.food.material.dao.MaterialGroupDao;
import group.mytool.backend.food.material.entity.po.Material;
import group.mytool.backend.food.material.entity.po.MaterialGroup;
import group.mytool.backend.food.material.entity.ro.MaterialGroupFormParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static group.mytool.backend.core.exception.EnumGlobalError.DATA_EXIST;
import static group.mytool.backend.core.exception.EnumGlobalError.SUCCESS;
import static group.mytool.backend.core.exception.EnumGlobalError.USED_CANNOT_DELETE;
import static group.mytool.backend.core.util.Constant.NODE_ROOT;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class AdminMaterialControllerTest extends BaseValidateTest {

  @Autowired
  private AdminMaterialControllerClient adminMaterialControllerClient;
  @Autowired
  private MaterialGroupControllerClient materialGroupControllerClient;
  @Autowired
  private MaterialGroupDao groupDao;
  @Autowired
  private MaterialDao materialDao;

  public static final String GROUP_ROOT_NAME = "junit-海鲜";
  public static final String GROUP_CHILD_NAME = "junit-螃蟹";
  public static final String MATERIAL_NAME = "junit-大闸蟹";
  public static final Integer SORT = 1;

  @BeforeEach
  void init() {
    // 环境准备
    MaterialGroup rootQuery = new MaterialGroup();
    rootQuery.setName(GROUP_ROOT_NAME);
    QueryWrapper<MaterialGroup> queryWrapper = new QueryWrapper<>(rootQuery);
    List<MaterialGroup> rootList = groupDao.list(queryWrapper);
    for (MaterialGroup root : rootList) {
      int cnt = groupDao.deleteByIdPhysical(root.getId());
      Assertions.assertTrue(cnt > 0);
    }
    // 子节点
    MaterialGroup childQuery = new MaterialGroup();
    childQuery.setName(GROUP_CHILD_NAME);
    QueryWrapper<MaterialGroup> wrapper = new QueryWrapper<>(childQuery);
    List<MaterialGroup> list = groupDao.list(wrapper);
    for (MaterialGroup child : list) {
      int cnt = groupDao.deleteByIdPhysical(child.getId());
      Assertions.assertTrue(cnt > 0);
    }
    // 食材
    Material materialQuery = new Material();
    materialQuery.setName(MATERIAL_NAME);
    QueryWrapper<Material> materialWrapper = new QueryWrapper<>(materialQuery);
    List<Material> materialList = materialDao.list(materialWrapper);
    for (Material material : materialList) {
      int cnt = materialDao.deleteByIdPhysical(material.getId());
      Assertions.assertTrue(cnt > 0);
    }
  }

  @Test
  void saveOrUpdate() {
  }

  @Test
  void removeById() {
  }

  @Test
  void groupSaveOrUpdate() {
    // 根节点
    MaterialGroupFormParam rootParam = new MaterialGroupFormParam();
    rootParam.setSort(SORT);
    // name not null
    rootParam.setName(null);
    Result<Val> valResult = adminMaterialControllerClient.groupSaveOrUpdate(rootParam);
    Assertions.assertEquals(MaterialGroupFormParam.nameNotNullMessage, valResult.getMessage());
    // name too long
    rootParam.setName(IdUtil.simpleUUID());
    valResult = adminMaterialControllerClient.groupSaveOrUpdate(rootParam);
    Assertions.assertEquals(MaterialGroupFormParam.nameSizeMessage, valResult.getMessage());
    // sort too big
    rootParam.setName(GROUP_ROOT_NAME);
    rootParam.setSort(1000);
    valResult = adminMaterialControllerClient.groupSaveOrUpdate(rootParam);
    Assertions.assertEquals(MaterialGroupFormParam.sortMaxMessage, valResult.getMessage());
    // success
    rootParam.setName(GROUP_ROOT_NAME);
    rootParam.setSort(SORT);
    valResult = adminMaterialControllerClient.groupSaveOrUpdate(rootParam);
    Assertions.assertEquals(SUCCESS.getMessage(), valResult.getMessage());
    // 不能添加重名分组
    valResult = adminMaterialControllerClient.groupSaveOrUpdate(rootParam);
    Assertions.assertEquals(DATA_EXIST.getMessage(), valResult.getMessage());
    // 查询根节点
    MaterialGroup rootQuery = new MaterialGroup();
    rootQuery.setParentId(NODE_ROOT);
    rootQuery.setName(GROUP_ROOT_NAME);
    QueryWrapper<MaterialGroup> rootWrapper = new QueryWrapper<>(rootQuery);
    List<MaterialGroup> rootList = groupDao.list(rootWrapper);
    Assertions.assertTrue(rootList.size() == 1);
    MaterialGroup rootGroup = rootList.get(0);
    Assertions.assertEquals(GROUP_ROOT_NAME, rootGroup.getName());
    // 子节点
    MaterialGroupFormParam childParam = new MaterialGroupFormParam();
    childParam.setParentId(rootGroup.getId());
    childParam.setName(GROUP_CHILD_NAME);
    childParam.setSort(SORT);
    valResult = adminMaterialControllerClient.groupSaveOrUpdate(childParam);
    Assertions.assertEquals(SUCCESS.getMessage(), valResult.getMessage());
    // 查询子节点
    MaterialGroup childQuery = new MaterialGroup();
    childQuery.setParentId(NODE_ROOT);
    childQuery.setName(GROUP_ROOT_NAME);
    QueryWrapper<MaterialGroup> childWrapper = new QueryWrapper<>(childQuery);
    List<MaterialGroup> childList = groupDao.list(childWrapper);
    Assertions.assertTrue(childList.size() == 1);
  }

  @Test
  void groupRemoveById() {
    // 根节点
    MaterialGroup rootGroup = new MaterialGroup();
    rootGroup.setName(GROUP_ROOT_NAME);
    rootGroup.setSort(1);
    boolean save = groupDao.save(rootGroup);
    Assertions.assertTrue(save);
    // 子节点
    MaterialGroup childGroup = new MaterialGroup();
    childGroup.setParentId(rootGroup.getId());
    childGroup.setName(GROUP_CHILD_NAME);
    childGroup.setSort(1);
    save = groupDao.save(childGroup);
    Assertions.assertTrue(save);
    // 食材
    Material material = new Material();
    material.setGroupId(childGroup.getId());
    material.setName(MATERIAL_NAME);
    material.setUnit("只");
    material.setImageUrl("https://mytool.group/junit.jpg");
    save = materialDao.save(material);
    Assertions.assertTrue(save);

    // 参数错误
    IdQuery rootIdQuery = new IdQuery();
    Result<Val> valResult = adminMaterialControllerClient.groupRemoveById(rootIdQuery);
    Assertions.assertEquals(IdQuery.idNotNullMessage, valResult.getMessage());
    // 已被使用无法删除
    rootIdQuery.setId(rootGroup.getId());
    valResult = adminMaterialControllerClient.groupRemoveById(rootIdQuery);
    Assertions.assertEquals(USED_CANNOT_DELETE.getMessage(), valResult.getMessage());
    IdQuery childIdQuery = new IdQuery();
    childIdQuery.setId(childGroup.getId());
    valResult = adminMaterialControllerClient.groupRemoveById(childIdQuery);
    Assertions.assertEquals(USED_CANNOT_DELETE.getMessage(), valResult.getMessage());
    // 删除食材和子节点后可以正常删除
    // 删除食材
    save = materialDao.removeById(material.getId());
    Assertions.assertTrue(save);
    // 删除子节点
    valResult = adminMaterialControllerClient.groupRemoveById(childIdQuery);
    Assertions.assertEquals(SUCCESS.getMessage(), valResult.getMessage());
    // 删除根节点
    valResult = adminMaterialControllerClient.groupRemoveById(rootIdQuery);
    Assertions.assertEquals(SUCCESS.getMessage(), valResult.getMessage());

  }

}