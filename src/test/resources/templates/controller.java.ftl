package ${package.Controller};

import ${package.Parent}.service.${table.entityName}Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* ${table.comment!} 控制层
*
* @author 麦途 <0haizhu0@gmail.com>
*/
@RestController
@RequiredArgsConstructor
@RequestMapping("/${table.entityPath}")
public class ${table.controllerName} {

  private final ${table.entityName}Service ${table.entityPath}Service;

}