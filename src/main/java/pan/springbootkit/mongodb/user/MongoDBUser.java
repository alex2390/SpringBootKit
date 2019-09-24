package pan.springbootkit.mongodb.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import pan.springbootkit.mongodb.base.MongoDBBaseModel;

import java.io.Serializable;

/**
 * MongoDB 用户 entity
 *
 * Created by panzhangbao on 2019-09-24 11:05:20
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MongoDBUser extends MongoDBBaseModel implements Serializable {

	private static final long serialVersionUID = -4751962510421112233L;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String password;
}
