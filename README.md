## 宜立方商城

### 技术栈

- 项目环境采用`IDEA` + `MAVEN` + `Tomcat` + `MySQL`，数据库连接池采用`Druid`，采用`SOA` 架构

- 后台采用SSM，使用`MP(Mybatis-plus)`替代`MyBatis`

- 服务中间件：`Dubbo` + `Zookeeper`

- 消息中间件：`ActiveMQ`

- 图片服务：`腾讯云`

- 搜索服务：`Solr`

- 缓存服务：`Redis`

### 运行项目

首先需要搭建相关环境：

（1）Tomcat
- [Linux搭建JavaWeb开发环境（Java、Tomcat、MySQL）](https://blog.csdn.net/yuanlaijike/article/details/78877830)
- [Linux部署多台Tomcat](https://blog.csdn.net/yuanlaijike/article/details/79692794)
 
（2）Zookeeper、ZookeeperCluster
- [Dubbo + Zookeeper入门初探](https://blog.csdn.net/yuanlaijike/article/details/79654183)
- [Zookeper集群搭建](https://blog.csdn.net/yuanlaijike/article/details/79916792)

（3）Redis
- [Redis初探（1）——Redis的安装](https://blog.csdn.net/yuanlaijike/article/details/79383242)
- [Redis初探（6）——Redis集群](https://blog.csdn.net/yuanlaijike/article/details/79860099)

（4）Solr、SolrCloud
- [Solr初探（1）——Solr介绍（基于Solr 6.6.2）](https://blog.csdn.net/yuanlaijike/article/details/79465627)
- [Solr初探（6）——SolrCloud](https://blog.csdn.net/yuanlaijike/article/details/79919301)

（5）ActiveMQ
- [ActiveMQ介绍、使用并与Spring整合](https://blog.csdn.net/yuanlaijike/article/details/79950330)

然后修改数据库、Zookeeper、Redis、Solr、ActiveMQ、腾讯云COS的配置信息


### 系统架构

- **后台管理系统**：管理商品、订单、类目、商品规格属性、用户管理以及内容发布等功能。

- **前台系统**：用户可以在前台系统中进行注册、登录、浏览商品、首页、下单等操作。

- **会员系统**：用户可以在该系统中查询已下的订单、收藏的商品、我的优惠券、团购等信息。

- **订单系统**：提供下单、查询订单、修改订单状态、定时处理订单。

- **搜索系统**：提供商品的搜索功能。

- **单点登录系统**：为多个系统之间提供用户登录凭证以及查询登录用户的信息。

![](https://raw.githubusercontent.com/ZzXxL1994/e3mall/master/jiagou.png)