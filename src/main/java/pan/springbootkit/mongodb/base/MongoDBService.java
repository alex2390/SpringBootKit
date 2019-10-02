//package pan.springbootkit.mongodb.base;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * MongoDB service
// *
// * Created by panzhangbao on 2019-09-22 10:30:20
// * Copyright © 2019 panzhangbao. All rights reserved.
// */
//public interface MongoDBService<T>{
//
//	/**
//	 * 根据集合名称获取下一 id
//	 *
//	 * @param collectionName 集合名称
//	 * @return java.lang.Integer
//	 * @date 2019-09-24 14:49
//	 * @author panzhangbao
//	 */
//	Integer getNextId(String collectionName);
//
//	/**
//	 * 创建一个集合
//	 * 		同一个集合中可以存入多个不同类型的对象，我们为了方便维护和提升性能，
//	 * 	 	后续将限制一个集合中存入的对象类型，即一个集合只能存放一个类型的数据
//	 *
//	 * @param collectionName 集合名称，相当于传统数据库的表名
//	 * @return void
//	 * @date 2019-09-22 10:52
//	 * @author panzhangbao
//	 */
//	void createCollection(String collectionName);
//
//	/**
//	 * 删除一个集合
//	 *
//	 * @param collectionName 集合名称
//	 * @return void
//	 * @date 2019-09-24 14:52
//	 * @author panzhangbao
//	 */
//	void dropCollection(String collectionName);
//
//	/**
//	 * 根据 id 查询信息
//	 *
//	 * @param collectionName 集合名称
//	 * @param id 主键
//	 * @param clazz 对象类型
//	 * @return T
//	 * @date 2019-09-24 09:13
//	 * @author panzhangbao
//	 */
//	T get(String collectionName, Integer id, Class<T> clazz);
//
//	/**
//	 * 根据条件查询信息
//	 *
//	 * @param collectionName 集合名称
//	 * @param paramsMap 查询条件
//	 * @param clazz 对象类型
//	 * @return T
//	 * @date 2019-09-24 09:13
//	 * @author panzhangbao
//	 */
//	T getByParameters(String collectionName, Map<String, String> paramsMap, Class<T> clazz);
//
//	/**
//	 * 查询列表
//	 *
//	 * @param collectionName 集合名
//	 * @param clazz 对象类型
//	 * @return java.util.List<T>
//	 * @date 2019-09-24 09:42
//	 * @author panzhangbao
//	 */
//	List<T> list(String collectionName, Class<T> clazz);
//
//	/**
//	 * 根据条件查询集合 支持模糊查询
//	 *
//	 * @param collectionName 集合名称
//	 * @param paramsMap 查询条件 Map
//	 * @param clazz 对象类型
//	 * @return java.util.List<T>
//	 * @date 2019-09-24 09:52
//	 * @author panzhangbao
//	 */
//	List<T> listByParameters(String collectionName, Map<String, String> paramsMap,Class<T> clazz);
//
//	/**
//	 * 查询分页列表
//	 *
//	 * @param collectionName 集合名
//	 * @param clazz 对象类型
//	 * @param pageNum 页码
//	 * @param pageSize 页码大小
//	 * @return java.util.List<T>
//	 * @date 2019-09-24 09:42
//	 * @author panzhangbao
//	 */
//	List<T> listByPage(String collectionName, Class<T> clazz, Integer pageNum, Integer pageSize);
//
//	/**
//	 * 根据条件和分页查询集合 支持模糊查询
//	 *
//	 * @param collectionName 集合名称
//	 * @param paramsMap 查询条件，目前查询条件处理的比较简单，仅仅做了相等匹配，没有做模糊查询等复杂匹配
//	 * @param clazz 对象类型
//	 * @param pageNum 页码
//	 * @param pageSize 页码大小
//	 * @return java.util.List<T>
//	 * @date 2019-09-24 09:08
//	 * @author panzhangbao
//	 */
//	List<T> listByParametersAndPage(String collectionName,
//									Map<String, String> paramsMap,
//									Class<T> clazz,
//									Integer pageNum,
//									Integer pageSize);
//
//	/**
//	 * 创建唯一索引		索引是顺序排列，且唯一的索引
//	 *
//	 * @param collectionName 集合名称，相当于关系型数据库中的表名
//	 * @param filedName 对象中的某个属性名
//	 * @return java.lang.String
//	 * @date 2019-09-24 09:09
//	 * @author panzhangbao
//	 */
//	String createUniqueIndex(String collectionName, String filedName);
//
//	/**
//	 * 删除索引
//	 *
//	 * @param collectionName 集合名称，相当于关系型数据库中的表名
//	 * @param filedName 对象中的某个属性名
//	 * @return java.lang.String
//	 * @date 2019-09-24 09:09
//	 * @author panzhangbao
//	 */
//	void dropIndex(String collectionName, String filedName);
//
//	/**
//	 * 获取当前集合对应的所有索引的名称
//	 *
//	 * @param collectionName 集合名称
//	 * @return java.util.List<java.lang.String>
//	 * @date 2019-09-24 09:10
//	 * @author panzhangbao
//	 */
//	List<String> getAllIndexes(String collectionName);
//
//	/**
//	 * 插入
//	 *
//	 * @param collectionName 集合名称
//	 * @param entity 存储对象
//	 * @return T 存储对象
//	 * @date 2019-09-24 09:27
//	 * @author panzhangbao
//	 */
//	void insert(String collectionName, T entity);
//
//	/**
//	 * 批量插入		注意批量的数据中不要包含重复的id
//	 *
//	 * @param collectionName 集合名称
//	 * @param entityList 对象列表
//	 * @return void
//	 * @date 2019-09-24 09:25
//	 * @author panzhangbao
//	 */
//	void batchInsert(String collectionName, List<T> entityList);
//
//	/**
//	 * 更新
//	 *
//	 * @param collectionName 集合名称
//	 * @param entity 更新对象
//	 * @return void
//	 * @date 2019-09-24 09:37
//	 * @author panzhangbao
//	 */
//	void update(String collectionName, T entity);
//
//	/**
//	 * 删除
//	 *
//	 * @param collectionName 集合名称
//	 * @param id 主键 id
//	 * @param clazz 集合中对象的类型
//	 * @return void
//	 * @date 2019-09-24 09:38
//	 * @author panzhangbao
//	 */
//	void delete(String collectionName, Integer id, Class<T> clazz);
//}
