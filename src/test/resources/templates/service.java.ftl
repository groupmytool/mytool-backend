package ${package.Service};

import ${package.Parent}.dao.${table.entityName}Dao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* ${table.comment!} 业务逻辑层
*
* @author 麦途 <0haizhu0@gmail.com>
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class ${table.entityName}Service {

  private final ${table.entityName}Dao dao;

}