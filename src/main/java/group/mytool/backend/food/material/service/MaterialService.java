package group.mytool.backend.food.material.service;

import group.mytool.backend.food.material.dao.MaterialDao;
import group.mytool.backend.food.material.entity.ro.MaterialFormParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 麦途 <0haizhu0@gmail.com>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MaterialService {

  private final MaterialDao dao;

  public Boolean saveOrUpdate(MaterialFormParam form) {
    return dao.saveOrUpdate(form);
  }

  public Boolean removeById(String id) {
    return dao.removeById(id);
  }

}
