package group.mytool.backend.food.material.service;

import group.mytool.backend.food.material.mapper.MaterialMapper;
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

  private final MaterialMapper mapper;

}
