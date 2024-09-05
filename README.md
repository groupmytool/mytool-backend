# MyTool后端项目

# 项目架构

# 开发调试

* JDK版本要求：Liberica-NIK-24

> java -version

```
openjdk version "22.0.2" 2024-07-16
OpenJDK Runtime Environment Liberica-NIK-24.0.2-1 (build 22.0.2+11)
OpenJDK 64-Bit Server VM Liberica-NIK-24.0.2-1 (build 22.0.2+11, mixed mode, sharing)
```

## JVM
```shell
./mvnw clean package -DskipTests
java -jar ./target/mytool-backend.jar
```

## Native
```shell
./mvnw -Pnative clean -DskipTests native:compile
./target/mytool-backend
```

# Docker编译部署

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

### 加速编译

* 构建编译镜像
```shell
docker build --platform=linux/amd64 -f Dockerfile.cache.nik -t mytool-group-develop:jdk-22-nik-24.0.2-glibc-with-m2 .
``` 

* 使用包含Jar包缓存的编译镜像进行编译
```shell
docker build -f Dockerfile.cache.native -t mytool-backend-native:last .
``` 

# 方案设计

## MyBatis-Plus
* 为了避免 MyBatis-Plus 的 **`Wrapper`** 侵入 Service，增加了 **`Dao层`** 封装所有plus方法

## native build
* 使用native编译的时候，需要加上 **`-Dfile.encoding=UTF-8`** 和 **`-H:+AddAllCharsets`** 参数，不然会出现数据库存储中文的编码问题
* 需要手动添加两种类型的 **`native`** 配置类
  * 包含lambda表达式的dao类
  * 包含validate校验的类

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
  * 副作用：如果使用了 **`ValidationSequence`** 注解，那么所有需要校验的参数都必须添加 **`groups = {OrderXxxx.class}`** 参数，不然不会进行参数校验
  * 注意：**`groups = {OrderXxxx.class}`** 在同一个参数类中可以重复使用
  * 推荐：按照属性校验排序分配 **`{OrderXxxx.class}`**，这样可以保证属性的校验顺序；同一个属性中不同的校验规则再继续递增校验顺序

## 单元测试
* 为了保证native编译后的代码质量，采用启动应用后调用真实接口的方式进行项目质量测试
  * 编译native镜像并启动后，执行 `./mvnw clean test` 进行测试
* 单元测试只负责接口测试覆盖不到逻辑的测试
* 非目标接口测试，不需要调用controller层接口，直接调用service层或dao接口即可
* 原则上不应该为单元测试编写特定的方法，应该使用通用的模版方法

