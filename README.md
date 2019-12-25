FastDFS简单使用说明
```
可以见 目录下面的操作：文档DFS分布式文件系统.docx
```

多线程创建方式
```
项目中config下面创建的方式是整合springboot进行创建的，还可以通过ThreadPoolExecutor方式进行创建，具体可以参考网址：
https://juejin.im/post/5b25b3d0f265da597d0a9803
合理配置线程池：
对于CPU密集型任务：线程池中线程个数应尽量少，不应大于CPU核心数；
对于IO密集型任务：由于IO操作速度远低于CPU速度，那么在运行这类任务时，CPU绝大多数时间处于空闲状态，那么线程池可以配置尽量多些的线程，以提高CPU利用率；
```
    

