package group.mytool.flutter.flex.backend.food.material.service;

import group.mytool.flutter.flex.backend.food.material.mapper.MaterialMapper;
import org.springframework.stereotype.Service;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Service
public class MaterialService {

  private final MaterialMapper mapper;

  public MaterialService(MaterialMapper materialMapper) {
    this.mapper = materialMapper;
  }

}
