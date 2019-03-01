# XTP JAVA API
本项目是中泰证券XTP极速交易JAVA接口的开源实现，供客户在量化交易中使用JAVA接口快速接入XTP系统。
中泰证券XTP是为股票交易而生的极速交易系统，延时<60微妙，为投资者提供极速交易、极速行情、Level2行情。

如何使用：
方式一：直接使用成品
1）手工拷贝动态库到系统目录
   在项目根目录下：
   linux：cp cpp/linux/* /usr/local/lib/
          cp libtradeplugin.so、libquoteplugin.so
   mac：libglog.dylib、libxtptraderapi.dylib、libxtpquoteapi.dylib、libtradeplugin.dylib、libquoteplugin.dylib
   win：libglog.dll、libxtptraderapi.dll、libxtpquoteapi.dll、libtradeplugin.dll、libquoteplugin.dll

方式二：源码编译后使用（用于当方式一无法正常使用时）
1）安装C++编译工具
   linux：yum install -y gcc 或apt-get install -y gcc
   mac: xcode-select --install 或 安装brew并用brew install gcc@7
   win：安装visual stuido 2015
2) 安装cmake
   linux：yum install -y cmake 或apt-get install -y cmake
   mac:  http://www.cmake.org/download/下载cmake-3.13.2.tar.gz 解压tar-zxvf cmake-3.13.2.tar.gz，进入解压目录后，sudo ./bootstrap && sudo make && sudo make install
   win：下载安装https://github.com/Kitware/CMake/releases/download/v3.13.2/cmake-3.13.2-win32-x86.msi
3) 安装jdk8
   根据系统安装对应jdk：https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
4）在工程根目录下的cpp目录执行cmake&&make&&make install 则在/usr/local/lib(或c:\windows\system32\)下会新增5个动态库：
    linux：libglog.so、libxtptraderapi.so、libxtpquoteapi.so、libtradeplugin.so、libquoteplugin.so
    mac：libglog.dylib、libxtptraderapi.dylib、libxtpquoteapi.dylib、libtradeplugin.dylib、libquoteplugin.dylib
    win：libglog.dll、libxtptraderapi.dll、libxtpquoteapi.dll、libtradeplugin.dll、libquoteplugin.dll
5）安装gradle
6）在工程根目录下执行./gradlew build 执行成功后在项目根目录生成build/libs/xtpapi-1.1.18.13.jar
7）在量化交易java代码中引入xtpapi-1.1.18.13.jar，参考项目根目录下xtp_api_demo/src/main/java/com/zts/xtp/demo/Application.java的API使用demo
   以及src/test/java/com.zts.xtp/trade/TradeApiTest.java以及src/test/java/com.zts.xtp/quote/QuoteApiTest.java的单元测试中的用法
 
JAVA API文档：
