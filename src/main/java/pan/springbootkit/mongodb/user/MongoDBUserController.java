package pan.springbootkit.mongodb.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import pan.springbootkit.base.BaseResult;
import pan.springbootkit.mongodb.base.MongoDBService;
import pan.springbootkit.mongodb.base.MongoDBCollectionNameEnum;
import pan.springbootkit.mongodb.base.MongoDBID;
import pan.springbootkit.mongodb.user.MongoDBUser;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * MongoDB 用户 controller
 *
 * Created by panzhangbao on 2019-09-24 11:07:11
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@RestController
@RequestMapping("api/db/mongodb/user")
public class MongoDBUserController {
		@Resource
		private MongoDBService mongoDBService;

		@GetMapping("list")
		public BaseResult list(@RequestParam(value = "userName", required = false) String userName) {
			String collectionName = MongoDBCollectionNameEnum.USER.getCollectionName();
			Map paramsMap = new HashMap();
			if (StringUtils.isNotBlank(userName)) {
				paramsMap.put("userName", userName);
			}

			return BaseResult.success(mongoDBService.listByParametersAndPage(collectionName,
					paramsMap,
					MongoDBUser.class,
					1,
					10));
		}

		@GetMapping
		public BaseResult get() {
			String collectionName = MongoDBCollectionNameEnum.USER.getCollectionName();

			return BaseResult.success(mongoDBService.get(collectionName, 1, MongoDBUser.class));
		}

		@PostMapping
		public BaseResult post() {
//			/**
//			 * 首次使用
//			 */
//			setDefaultMongoDB();

			String collectionName = MongoDBCollectionNameEnum.USER.getCollectionName();

			MongoDBUser user = new MongoDBUser();
//			user.setUserName("Jason Pan");
//			user.setPassword("666666");
//			mongoDBService.insert(collectionName, user);

			user = new MongoDBUser();
			user.setUserName("Neil Pan");
			user.setPassword("52166");
			mongoDBService.insert(collectionName, user);

			return BaseResult.success();
		}

		@PutMapping
		public BaseResult put() {
			String collectionName = MongoDBCollectionNameEnum.USER.getCollectionName();

			MongoDBUser user = new MongoDBUser();
			user.setId(1);
			user.setUserName("Dear Kong");
			user.setPassword("7777777");
			mongoDBService.update(collectionName, user);

			return BaseResult.success();
		}

		@DeleteMapping
		public BaseResult delete() {
			String collectionName = MongoDBCollectionNameEnum.USER.getCollectionName();
			mongoDBService.delete(collectionName, 1, MongoDBUser.class);

			return BaseResult.success();
		}

		/**
		 * 首次使用 mongoDB 和创建 user 表时使用
		 *
		 * @param
		 * @return void
		 * @date 2019-09-24 14:54
		 * @author panzhangbao
		 */
		private void setDefaultMongoDB() {
			String collectionName = MongoDBCollectionNameEnum.ID.getCollectionName();
			// 新增 id 表，用于存储最新的 id
			mongoDBService.createCollection(collectionName);

			// 新增用户表前标记最新 id
			MongoDBID mongoDBID = new MongoDBID();
			mongoDBID.setCollectionName(collectionName);
			mongoDBID.setId(1);
			mongoDBID.setNextId(1);
			mongoDBService.insert(collectionName, mongoDBID);

			// 新增用户表
			collectionName = MongoDBCollectionNameEnum.USER.getCollectionName();
			mongoDBService.createCollection(collectionName);
		}
}
