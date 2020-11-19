learn-english

[Api文档](http://192.168.1.102:8080/swagger-ui/index.html)

# docker操作：
1.Dockerfile和jar包复制到ubuntu服务器指定目录下
2.使用docker命令制作镜像
    
    查看容器：`sudo docker ps -a`
    删除容器：`sudo docker rm -f [容器id]`
    
    查看镜像：`sudo docker images`
    删除镜像：`sudo docker rmi [镜像id]`
    
    制作镜像：`sudo docker build -t learn-english:0.0.1 .`【注意：末尾有个点 ·】

    创建容器并运行：`sudo docker run -d -p 80:80 learn-english:0.0.1`