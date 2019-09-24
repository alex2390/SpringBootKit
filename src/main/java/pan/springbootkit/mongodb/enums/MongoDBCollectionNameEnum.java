package pan.springbootkit.mongodb.enums;

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
	ID("id", null, "id"),
	USER("user", 1, "用户"),
	;

	/**
	 * 集合名称
	 */
	private String collectionName;

	/**
	 * 集合名称对应的 tb_id 里面的主键 id
	 */
	private Integer id;

	/**
	 * 说明
	 */
	private String desc;
}
