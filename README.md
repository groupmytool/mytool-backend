# MyTool后端项目

# 项目架构

# 编译

## JVM Docker 镜像
```shell
# 编译
docker build -f Dockerfile.jvm -t mytool-backend-jvm:last .
# 运行
docker run --name mytool-backend-jvm -d -p 8080:8080 --restart unless-stopped mytool-backend-jvm:last
```

## Native Docker 镜像
```shell
# 编译
docker build -f Dockerfile.native -t mytool-backend-native:last .
# 运行
docker run --name mytool-backend-native -d -p 8080:8080 --restart unless-stopped mytool-backend-native:last
```

# 方案设计

## MyBatis-Plus
* 为了避免 MyBatis-Plus 的 **`Wrapper`** 侵入 Service，增加了 **`Dao层`** 封装所有plus方法

## 模版方法扩展
* 扩展通用的物理删除模版方法：
  * **`deleteByIdPhysical`** 
  * **`deleteByIdsPhysical`**

## 方法命名
* 使用 Mybatis-Plus 的命名方式进行命名，保持命名规则统一

## entity设计
* **`ro（request object）`**：特定dto，请求接口的参数对象，继承 **`po`**
  * **`Query`**：查询参数
  * **`Param`**：新增和修改参数
* **`vo（view/value object）`**：特定dto，前端渲染对象
* **`po（persistent Object）`**：数据库交互对象，继承 **`BaseEntity`**
* **`bo（business object）`**：业务对象，当po和vo不能满足业务逻辑处理的时候，才增加bo对象

## 参数校验
* 快速失败：一旦校验失败，立即返回错误信息
* 校验顺序：通过 **`ValidationSequence`** 注解，按照分组顺序校验参数

## 单元测试
* 为了保证native编译后的代码质量，采用启动应用后调用真实接口的方式进行项目质量测试
* 单元测试只负责接口测试覆盖不到逻辑的测试

