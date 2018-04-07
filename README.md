## 宜立方商城

### 技术栈

- 项目环境采用`IDEA` + `MAVEN` + `Tomcat` + `MySQL`，数据库连接池采用`Druid`

- 后台采用SSM，使用`MP(Mybatis-plus)`替代`MyBatis`

- 采用`SOA` 架构，使用`Zookeeper`进行服务治理

- 图片服务器使用`腾讯云`替代`FastDFS`

#### 运行项目

1. 首先需要一台Linux服务器或虚拟机，并在上面搭建Zookeeper服务，搭建可以参考文章：[Dubbo + Zookeeper入门初探](https://blog.csdn.net/yuanlaijike/article/details/79654183)

2. 等待添加

### 系统架构

- **后台管理系统**：管理商品、订单、类目、商品规格属性、用户管理以及内容发布等功能。

- **前台系统**：用户可以在前台系统中进行注册、登录、浏览商品、首页、下单等操作。

- **会员系统**：用户可以在该系统中查询已下的订单、收藏的商品、我的优惠券、团购等信息。

- **订单系统**：提供下单、查询订单、修改订单状态、定时处理订单。

- **搜索系统**：提供商品的搜索功能。

- **单点登录系统**：为多个系统之间提供用户登录凭证以及查询登录用户的信息。

![](https://raw.githubusercontent.com/ZzXxL1994/e3mall/master/jiagou.png)