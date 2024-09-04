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

## 参数校验
* 快速失败：一旦校验失败，立即返回错误信息
* 校验顺序：通过`ValidationSequence`注解，按照分组顺序校验参数

## 单元测试
* 为了保证native编译后的代码质量，采用启动应用后调用真实接口的方式进行项目质量测试
* 单元测试只负责接口测试覆盖不到逻辑的测试

