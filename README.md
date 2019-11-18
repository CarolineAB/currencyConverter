# currencyConverter
汇率换算（美元）

本项目依赖maven环境


 
 运行并测试的步骤:
 
 1.进入项目目录，并运行命令窗口
 
 运行：“mvn package”
 
 2.将1.txt及data.txt 复制到target目录下
 
 3.命令窗口进入target 目录
 
 运行 "java -jar currencyConverter-0.0.1-SNAPSHOT.jar"
 
 4.输入货币代码及金额（如：“CNY 200”注意：控制台输入数据格式为“汇率代码+空格+金额”）
 
 每间隔一分钟将打印一次数据
 
 结果如下：
 
 https://raw.githubusercontent.com/CarolineAB/currencyConverter/master/images/1573824136(1).jpg
 
 
 
文件说明：

1.txt 为初始数据

data.txt 为已记录的汇率代码及汇率

异常提示：

1.输入数据格式不正确（The format of data is not right !）

2.输入货币未记录（The Currency is not recorded !）

3.其他错误，服务停止（ERROR!,The services is stoped!）
