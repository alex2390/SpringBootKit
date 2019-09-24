package pan.springbootkit.mongodb.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * MongoDB 集合名称 enum
 *
 * Created by panzhangbao on 2019-09-24 11:08:43
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Getter
@AllArgsConstructor
public enum MongoDBCollectionNameEnum {
	ID("id", "id"),
	USER("user", "用户"),
	;

	/**
	 * 集合名称
	 */
	private String collectionName;

	/**
	 * 说明
	 */
	private String desc;
}
