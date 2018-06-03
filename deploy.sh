#项目自动化发布脚本
echo "===========进入git项目SpringBootDemo目录============="
cd /data/developer/git-repository/SpringBootDemo
echo "==========git切换分之到branch1==============="
git checkout branch1
echo "==================git fetch(检查更新)======================"
git fetch
echo "==================git pull(拉取更新)======================"
git pull
echo "===========编译并跳过单元测试===================="
mvn clean package -Dmaven.test.skip=true
echo "======拷贝编译出来的jar包到/data/developer/git-jar/SpringBootDemo======="
cp /data/developer/git-repository/SpringBootDemo/target/SpringBootMongoDB-1.0-SNAPSHOT.jar /data/developer/git-jar/SpringBootDemo
echo "============进入/data/developer/git-jar/SpringBootDemo目录"
cd /data/developer/git-jar/SpringBootDemo
echo "============后台运行Jar包============="
(nohup java -jar SpringBootMongoDB-1.0-SNAPSHOT.jar &>consoleMsg.log &)
echo "============监控日志================="
tail -f consoleMsg.log