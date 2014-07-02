1.正确build该工程需要手动安装java memcached client,命令如下：
mvn install:install-file -Dfile=/path/to/java-memcached-release_2.6.2.jar -DpomFile=/path/to/java-memcached-2.6.2.pom 
java-memcached-release_2.6.2.jar和java-memcached-2.6.2.pom 在/lib目录下。