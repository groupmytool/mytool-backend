package ${package.Service};

import ${package.Dao}.${tableExt.daoName};
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
public class ${tableExt.serviceName} {

  private final ${tableExt.daoName} dao;

}