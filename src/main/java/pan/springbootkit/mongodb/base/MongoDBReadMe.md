### MongoDB 使用篇
1. 引入 ```spring-boot-starter-data-mongodb```依赖
2. 直接调用 ```MongoDBService``` 即可，不过新的集合，需要先创建才可使用
3. 这里默认数据库名为 test，无需创建和配置，直接使用即可

---

### MongoDB 优缺点
MongoDB 优点：
- 1.性能优越:快速！在适量级的内存的 MongoDB 的性能是非常迅速的，它将热数据存储在物理内存中，使得热数据的读写变得十分快，
- 2.高扩展:第三方支持丰富(这是与其他的 No SQL 相比，MongoDB 也具有的优势)
- 3.自身的 Failover 机制！
- 4.弱一致性（最终一致），更能保证用户的访问速度
- 5.文档结构的存储方式，能够更便捷的获取数据: json 的存储格式
- 6.支持大容量的存储,内置 GridFS
- 7.内置 Sharding

MongoDB 缺点：——主要是无事物机制！
- 1.MongoDB 不支持事务操作(最主要的缺点)
- 2.MongoDB 占用空间过大
- 3.MongoDB 没有如 MySQL 那样成熟的维护工具，这对于开发和IT运营都是个值得注意的地方