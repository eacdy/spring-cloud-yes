# 踩到的坑
* id过长，导致在ES的head插件上显示不完整（丢失精度）
  原因：JS的锅，ES没有问题，只是显示不完整而已。
  ref：<https://discuss.elastic.co/t/long-data-type-value-corruption/73614/4>

* ShiroOrder问题：自定义的Filter必须在创建后实例化，否则将会导致过滤器工作不正常。
  我遇到的现象是：例如我重写了authc这个过滤器，然后配置：

  ```
  /login=login
  /login/github=anon
  /**=authc
  ```

  当我请求/login/github时，却也会走自定义的authc过滤器。

* 参考：
  * <http://shiro-user.582556.n2.nabble.com/Shiro-integration-with-Spring-Boot-and-filter-chain-ordering-td7580288.html>
  * <http://richardadams606blog.blogspot.ca/2014/10/apache-shiro-and-spring-boot.html>







# TODO 
1. 标签云（即使做，也是单独一个页面。因为是采集站，标签肯定会很多，在首页展示的话会好长一坨。
2. [DONE] job管理页面后端的数据校验，非空啊什么的。
3. [DONE] 强化Job管理，例如：列表、动态删除等等
4. [怎么做?]Job状态监控、Job执行日志
5. 爬虫整合IP库，采集一些任务就换IP
6. [DONE]采集站点的配置
7. 研究ES的数据迁移，否则Job配置比较难部署
8. [DONE]管理页的认证
9. 人工编辑
10. 推荐算法
11. 支持Markdown
12. category暂时不做。先做站点分类。即使做category，也只考虑支持单个category，学习推酷。很多博客是多分类的，例如dongxicheng.org，取第一个就OK。
13. [DONE] 按站点URL列出文章。这样定位问题比较方便，不需要老是删除所有数据。article缺字段：siteUrl。考虑单独建立站点表。并且支持人工配置，不在采集时自动设置：理由：性能太差、而且站点的icon什么的反正要人工介入。
14. article flag：是否允许修改、是否已发布
15. [DONE] Quartz分布式（整合quartz-redis-jobstore实现）
16. 订阅[tag]、收藏[文章]、登录
17. 增量采集
18. 做大后考虑采集图片。目前很多地方要补全URL，感觉很麻烦。
19. **暂时不考虑原创**
20. 推荐【重中之重】
21. 清理CSS/JS、全部走本地，不引用CDN、其他网站上的CSS/JS
22. [DONE] 角色：eacdy为ADMIN，其他全部是USER
23. 后台首页 做个统计什么的
24. 不要过分纠结替换元素的方式不够优雅等问题。
25. 文章搜索【重中之重】


# 参考文档备忘：
<https://github.com/SpringDataElasticsearchDevs/spring-data-elasticsearch-sample-application> 目测里面挺多高级用法的，可以参考下。




# 定时任务参考：
 * http://www.cnblogs.com/darkwind/p/5447324.html
 * http://snailxr.iteye.com/blog/2076903
 * http://lihao312.iteye.com/blog/2329374