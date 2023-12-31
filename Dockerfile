#这是基础镜像
FROM java:8
# 作者
MAINTAINER by
VOLUME /tmp
#复制jar包到镜像中，并且将名字改成app.jar     注意./target是相对路径(与Dockerfile文件存放位置有关（Dockerfile->docker_boot-0.0.1-SNAPSHOT.jar相对路径）)
ADD ./target/scengine-0.0.1-SNAPSHOT.jar scengine_docker.jar
#在容器启动的时候运行命令，来启动我们的项目（这其实就是一段Linux命令,该命令可以在服务启动时加一些参数）
ENTRYPOINT ["sh", "-c", "java -jar scengine_docker.jar"]
#暴露8080端口作为微服务
EXPOSE 8080