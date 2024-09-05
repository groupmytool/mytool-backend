package group.mytool.backend.generator;

import lombok.Getter;
import lombok.Setter;

/**
 * @author adolphor <0haizhu0@gmail.com>
 */
@Setter
@Getter
public class TableInfoExt {

  private String paramName;
  private String queryName;
  private String voName;
  private String convertorName;
  private String daoName;
  private String clientName;
  private String serviceName;

}
