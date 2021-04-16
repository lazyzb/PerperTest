# PerperTest
for  my job

如果需要验证使用请下载master 分支的代码。

使用的包 log4j 1.2.17 用来做日志的打印（普通日志和error 类型的日志分开生成于E:\logs 目录下） 。

异步post 请求使用的是 org.apache.httpcomponents 下的 httpasyncclient包 使用的版本是4.1.1。

web层服务使用的是spring-boot-starter-web springboot集成的一个版本 springboot 的版本是2.4.4。


触发条件  在浏览器中输入http://127.0.0.1:8080/post.do?number=100 , number 的值表的就是需要发送的请求数，当number 不为数字时会发生异常。

由于我的idea 版本较低所以使用的是maven 3.3 与之适配

通过 RequestConfig 设置超时时间
