# MyTool后端项目

# 部署

```shell
java -XX:+UnlockExperimentalVMOptions -XX:+UseZGC -Xms512m -Xmx1024m -Xlog:gc* -Xlog:gc:/Users/adolphor/IdeaProjects/flutter/flutter-flex-backend/logs/gc.log -jar ./target/flutter-flex-backend.jar
java -XX:+UnlockExperimentalVMOptions -XX:+UseZGC -Xms512m -Xmx1024m -Xlog:gc* -Xlog:gc:/root/home/logs/jvm/gc.log -jar ./target/flutter-flex-backend.jar
```

* -XX:+UnlockExperimentalVMOptions：解锁实验性的VM选项
* -XX:+UseZGC：使用ZGC垃圾回收器
* -Xms512m：设置堆的初始大小为512M
* -Xmx1024m：设置堆的最大大小为1024M
* -Xlog:gc*：输出GC的详细日志
* -Xlog:gc:/root/home/logs/jvm/gc.log：将GC日志输出到gc.log文件


