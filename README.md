# MyTool后端项目

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

