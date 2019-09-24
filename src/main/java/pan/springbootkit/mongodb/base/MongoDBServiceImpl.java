package pan.springbootkit.mongodb.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pan.springbootkit.utils.PanDateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MongoDB serviceImpl
 *
 * Created by panzhangbao on 2019-09-22 10:32:20
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Service
public class MongoDBServiceImpl<T extends MongoDBBaseModel>  implements MongoDBService<T> {

	@Resource
	private MongoTemplate mongoTemplate;


	/**
	 * 根据集合名称获取下一 id
	 *
	 * @param collectionName 集合名称
	 * @return java.lang.Integer
	 * @date 2019-09-24 14:49
	 * @author panzhangbao
	 */
	@Override
	public Integer getNextId(String collectionName) {
		Integer id = null;

		Map paramsMap = new HashMap();
		paramsMap.put("collectionName", collectionName);

		MongoDBID mongoDBID = (MongoDBID) getByParameters(MongoDBCollectionNameEnum.ID.getCollectionName(),
				paramsMap,
				(Class<T>) MongoDBID.class);
		if (null == mongoDBID) {
			return id;
		}

		id = mongoDBID.getNextId();

		mongoDBID.setNextId(id + 1);
		update(MongoDBCollectionNameEnum.ID.getCollectionName(), (T) mongoDBID);

		return id;
	}

	/**
	 * 创建一个集合
	 * 		同一个集合中可以存入多个不同类型的对象，我们为了方便维护和提升性能，
	 * 	 	后续将限制一个集合中存入的对象类型，即一个集合只能存放一个类型的数据
	 *
	 * @param collectionName 集合名称，相当于传统数据库的表名
	 * @return void
	 * @date 2019-09-22 10:52
	 * @author panzhangbao
	 */
	@Override
	public void createCollection(String collectionName) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(collectionName)) {
			return;
		}

		/**
		 * 判断集合名称是否存在，若存在则返回
		 */
		if (mongoTemplate.collectionExists(collectionName)) {
			return;
		}

		mongoTemplate.createCollection(collectionName);
	}

	/**
	 * 删除一个集合
	 *
	 * @param collectionName 集合名称
	 * @return void
	 * @date 2019-09-24 14:52
	 * @author panzhangbao
	 */
	@Override
	public void dropCollection(String collectionName) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(collectionName)) {
			return;
		}

		mongoTemplate.dropCollection(collectionName);
	}

	/**
	 * 根据 id 查询信息
	 *
	 * @param collectionName 集合名称
	 * @param id 主键
	 * @param clazz 对象类型
	 * @return T
	 * @date 2019-09-24 09:13
	 * @author panzhangbao
	 */
	@Override
	public T get(String collectionName, Integer id, Class<T> clazz) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(collectionName) || null == id || null == clazz) {
			return null;
		}

		// 查询对象的时候，不仅需要传入 id 这个唯一键，还需要传入对象的类型，以及集合的名称
		return mongoTemplate.findById(id, clazz, collectionName);
	}

	/**
	 * 根据条件查询信息	支持模糊查询
	 *
	 * @param collectionName 集合名称
	 * @param paramsMap 查询条件
	 * @param clazz 对象类型
	 * @return T
	 * @date 2019-09-24 09:13
	 * @author panzhangbao
	 */
	@Override
	public T getByParameters(String collectionName, Map<String, String> paramsMap, Class<T> clazz) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(collectionName) || null == clazz) {
			return null;
		}

		Query query = new Query();

		// 往query中注入查询条件
		if (!CollectionUtils.isEmpty(paramsMap)) {
			paramsMap.forEach((key, value) -> {
				query.addCriteria(Criteria.where(key).regex(value));
			});
		}

		return mongoTemplate.findOne(query, clazz, collectionName);
	}

	/**
	 * 查询列表
	 *
	 * @param collectionName 集合名
	 * @param clazz 对象类型
	 * @return java.util.List<T>
	 * @date 2019-09-24 09:42
	 * @author panzhangbao
	 */
	@Override
	public List<T> list(String collectionName, Class<T> clazz) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(collectionName) || null == clazz) {
			return null;
		}

		return listByParametersAndPage(collectionName, null, clazz, null, null);
	}

	/**
	 * 根据条件查询集合 支持模糊查询
	 *
	 * @param collectionName 集合名称
	 * @param paramsMap 查询条件 Map
	 * @param clazz 对象类型
	 * @return java.util.List<T>
	 * @date 2019-09-24 09:52
	 * @author panzhangbao
	 */
	@Override
	public List<T> listByParameters(String collectionName, Map<String, String> paramsMap,Class<T> clazz) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(collectionName) || null == clazz) {
			return null;
		}

		return listByParametersAndPage(collectionName, paramsMap, clazz, null, null);
	}

	/**
	 * 查询分页列表
	 *
	 * @param collectionName 集合名
	 * @param clazz 对象类型
	 * @param pageNum 页码
	 * @param pageSize 页码大小
	 * @return java.util.List<T>
	 * @date 2019-09-24 09:42
	 * @author panzhangbao
	 */
	@Override
	public List<T> listByPage(String collectionName, Class<T> clazz, Integer pageNum, Integer pageSize) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(collectionName) || null == clazz) {
			return null;
		}

		return listByParametersAndPage(collectionName, null, clazz, pageNum, pageSize);
	}

	/**
	 * 根据条件和分页查询集合 支持模糊查询
	 *
	 * @param collectionName 集合名称
	 * @param paramsMap 查询条件，目前查询条件处理的比较简单，仅仅做了相等匹配，没有做模糊查询等复杂匹配
	 * @param clazz 对象类型
	 * @param pageNum 页码
	 * @param pageSize 页码大小
	 * @return java.util.List<T>
	 * @date 2019-09-24 09:08
	 * @author panzhangbao
	 */
	@Override
	public List<T> listByParametersAndPage(String collectionName,
										   Map<String, String> paramsMap,
										   Class<T> clazz,
										   Integer pageNum,
										   Integer pageSize) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(collectionName) || null == clazz) {
			return null;
		}

		Query query = new Query();

		// 设置分页信息
		if (null != pageNum && null != pageSize) {
			query.limit(pageSize);
			query.skip(pageSize * (pageNum - 1));
		}

		// 往query中注入查询条件
		if (!CollectionUtils.isEmpty(paramsMap)) {
			paramsMap.forEach((key, value) -> {
				// 封装模糊查询条件
				query.addCriteria(Criteria.where(key).regex(value));
			});
		}

		return mongoTemplate.find(query, clazz, collectionName);
	}

	/**
	 * 创建唯一索引		索引是顺序排列，且唯一的索引
	 *
	 * @param collectionName 集合名称，相当于关系型数据库中的表名
	 * @param filedName 对象中的某个属性名
	 * @return java.lang.String
	 * @date 2019-09-24 09:09
	 * @author panzhangbao
	 */
	@Override
	public String createUniqueIndex(String collectionName, String filedName) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(collectionName) || StringUtils.isBlank(filedName)) {
			return null;
		}

		// 配置索引选项,设置为唯一
		IndexOptions options = new IndexOptions();
		options.unique(true);

		// 创建按 filedName 升序排的索引
		return mongoTemplate.getCollection(collectionName).createIndex(Indexes.ascending(filedName), options);
	}

	/**
	 * 删除索引
	 *
	 * @param collectionName 集合名称，相当于关系型数据库中的表名
	 * @param filedName 对象中的某个属性名
	 * @return java.lang.String
	 * @date 2019-09-24 09:09
	 * @author panzhangbao
	 */
	@Override
	public void dropIndex(String collectionName, String filedName) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(collectionName) || StringUtils.isBlank(filedName)) {
			return;
		}

		mongoTemplate.getCollection(collectionName).dropIndex(filedName);
	}

	/**
	 * 获取当前集合对应的所有索引的名称
	 *
	 * @param collectionName 集合名称
	 * @return java.util.List<java.lang.String>
	 * @date 2019-09-24 09:10
	 * @author panzhangbao
	 */
	@Override
	public List<String> getAllIndexes(String collectionName) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(collectionName)) {
			return null;
		}

		ListIndexesIterable<Document> list = mongoTemplate.getCollection(collectionName).listIndexes();

		// 上面的list不能直接获取size，因此初始化 arrayList 就不设置初始化大小了
		List<String> indexList = new ArrayList<>();
		for (Document document : list) {
			document.entrySet().forEach((key) -> {
				// 提取出索引的名称
				if (key.getKey().equals("name")) {
					indexList.add(key.getValue().toString());
				}
			});
		}

		return indexList;
	}

	/**
	 * 插入
	 *
	 * @param collectionName 集合名称
	 * @param entity 存储对象
	 * @return T 存储对象
	 * @date 2019-09-24 09:27
	 * @author panzhangbao
	 */
	@Override
	public void insert(String collectionName, T entity) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(collectionName) || null == entity) {
			return;
		}

		if (null == entity.getId()) {
			entity.setId(getNextId(collectionName));
		}
		entity.setCreateTime(PanDateUtil.getNowDateTime());
		entity.setUpdateTime(PanDateUtil.getNowDateTime());

		mongoTemplate.insert(entity, collectionName);
	}

	/**
	 * 批量插入		注意批量的数据中不要包含重复的 id
	 *
	 * @param collectionName 集合名称
	 * @param entityList 对象列表
	 * @return void
	 * @date 2019-09-24 09:25
	 * @author panzhangbao
	 */
	@Override
	public void batchInsert(String collectionName, List<T> entityList) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(collectionName) || CollectionUtils.isEmpty(entityList)) {
			return;
		}

		for (T entity : entityList) {
			if (null == entity.getId()) {
				entity.setId(getNextId(collectionName));
			}
			entity.setCreateTime(PanDateUtil.getNowDateTime());
			entity.setUpdateTime(PanDateUtil.getNowDateTime());
		}

		mongoTemplate.insert(entityList, collectionName);
	}

	/**
	 * 更新
	 *
	 * @param collectionName 集合名称
	 * @param entity 更新对象
	 * @return void
	 * @date 2019-09-24 09:37
	 * @author panzhangbao
	 */
	@Override
	public void update(String collectionName, T entity) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(collectionName) ||  null == entity || null == entity.getId()) {
			return;
		}

		entity.setUpdateTime(PanDateUtil.getNowDateTime());

		Query query = new Query(Criteria.where("id").is(entity.getId()));

		Update update = new Update();
		String str = JSON.toJSONString(entity);
		JSONObject jQuery = JSON.parseObject(str);
		jQuery.forEach((key, value) -> {
			// 因为 id 相当于传统数据库中的主键，这里使用时就不支持更新，所以需要剔除掉
			if (!"id".equals(key) || !"createTime".equals(key)) {
				update.set(key, value);
			}
		});

		mongoTemplate.updateMulti(query, update, entity.getClass(), collectionName);
	}

	/**
	 * 删除
	 *
	 * @param collectionName 集合名称
	 * @param id 主键 id
	 * @param clazz 集合中对象的类型
	 * @return void
	 * @date 2019-09-24 09:38
	 * @author panzhangbao
	 */
	@Override
	public void delete(String collectionName, Integer id, Class<T> clazz) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(collectionName) || null == id || null == clazz) {
			return;
		}

		// 设置查询条件，当id=#{id}
		Query query = new Query(Criteria.where("id").is(id));
		// mongodb在删除对象的时候会判断对象类型，如果你不传入对象类型，只传入了集合名称，它是找不到的
		// 上面我们为了方便管理和提升后续处理的性能，将一个集合限制了一个对象类型，所以需要自行管理一下对象类型
		// 在接口传入时需要同时传入对象类型
		mongoTemplate.remove(query, clazz, collectionName);
	}
}
