# omProject

## 项目介绍

- 简介
    - Ocean Mooc是一套轻量级的在线教育平台，产品主旨是：**人生的大不同，从珍惜碎片化小时间开始**
- 缘由
    - 本系统用于作者2018年毕业设计
    - 初衷是在毕业之际，希望给自己的学弟学妹们留下点东西。原计划4月底启动这个项目，后来由于参加比赛，时间比较紧，所以就一直搁置到现在。恰逢毕设选题，索性准备将这个项目用作毕设，跟我的指导教师沟通后，她也比较支持；所以，笔者准备花心思去做这么一件事。
- 目标
    - 全栈的搭建这个平台，包括：服务器端、Web端、Android端(后期移动端可能采用Flutter，届时也会有对应的IOS版本)。
- 更多
    - [请移步我的博客](http://39.105.73.27/launch-ocean-mooc/)

## 软件架构

- 该项目为作者毕设项目群中的Server端，采用模块化开发
- 开发环境
    - About IDE
    ![idea](https://note.youdao.com/yws/public/resource/5f15fd7b7db124976cdb1208cb9d1091/xmlnote/203C7BCEEECB42ACBD4F870CFCA27517/306)
    - JDK Version：10.0.1
    - Maven Version：3.3.9
    - About Mysql
    ![Mysql](https://note.youdao.com/yws/public/resource/5f15fd7b7db124976cdb1208cb9d1091/xmlnote/B29262326B9D4DD5A2008056587DE00C/308)
    - Spring Boot Version：2.0.4
- 技术栈：
    - 基础框架：Spring Boot
    - 视图层：Spring MVC
    - ORM层：Spring Data JPA
    - 持久层：Mysql
- 目录结构
![目录结构](https://note.youdao.com/yws/public/resource/5f15fd7b7db124976cdb1208cb9d1091/xmlnote/C1D01532C7AD45D2883D8176E9BD747D/314)
- 模块
    - omBase(公共基础模块)
    - omService(业务模块)
    - omApp(移动端Api模块)
    - omAdmin(后台管理模块)
- 依赖关系
    - omService -> omBase
    - omApp -> omService
    - omAdmin -> omService
    - 如图
    ![如图](https://note.youdao.com/yws/public/resource/5f15fd7b7db124976cdb1208cb9d1091/xmlnote/D6F5962FAEA54889AD89EBA05DB2DC99/282)
- 为什么这样做？
    - 我们试想，在未来随着用户量的增加，客户端访问压力势必会迎来更大的挑战
    - 而后台管理模块，只会有数量可控的运营人员使用，并不会带来并发压力
    - 以往的做法，所有的接口层都糅合在一个项目中，随着业务增加，会在硬件上做投入(加机器、做集群)，这样成本得不到充分利用
    - 所以，在早期做模块化拆分，中期针对有并发压力的模块做硬件投入，效益会更好
    - 其次，早期做模块化处理，也会更方便我们后期考虑的微服务架构演变


#### 安装教程

1. 创建数据库，Database Name:omdb，执行sql脚本
2. 配置application.properties的数据库连接参数
3. git clone https://gitee.com/xykjlcx/omProject.git

#### 使用说明

1. xxxx
2. xxxx
3. xxxx

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

### 开源协议