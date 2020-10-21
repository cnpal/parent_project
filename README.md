# parent_project
一个前后端分离的后端项目
系统后端接口部分，使用目前流行的SpringBoot+SpringCloud进行微服务架构，使用Feign、Gateway、Hystrix，以及阿里巴巴的Nacos等组件搭建了项目的基础环境。
项目中还使用MyBatisPlus进行持久层的操作，使用了OAuth2+JWT实现了分布式的访问，项目中整合了SpringSecurity进行了权限控制。
除此之外，项目中使用了阿里巴巴的EasyExcel实现对Excel的读写操作，使用了Redis进行首页数据的缓存，使用Git进行代码的版本控制


#使用maven安装aliyun-java-vod-upload
mvn install:install-file -DgroupId=com.aliyun -DartifactId=aliyun-sdk-vod-upload -Dversion=1.4.13 -Dpackaging=jar -Dfile=aliyun-java-vod-upload-1.4.13.jar