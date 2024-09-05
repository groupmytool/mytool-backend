package ${package.Controller};

import ${package.Client}.${tableExt.clientName};
import ${package.Service}.${tableExt.serviceName};
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${table.comment!} 控制实现层
 *
 * @author 麦途 <0haizhu0@gmail.com>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/${table.entityPath}")
public class ${table.controllerName} implements ${tableExt.clientName} {

  private final ${tableExt.serviceName} service;

}