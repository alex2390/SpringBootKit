package pan.springbootkit.mongodb.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * MongoDB 基类
 *
 * Created by panzhangbao on 2019-09-22 10:41:45
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Data
@NoArgsConstructor
public class MongoDBBaseModel implements Serializable {

	private static final long serialVersionUID = 8484586102211132185L;

	/**
	 * 主键
	 */
	private Integer id;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 更新时间
	 */
	private String updateTime;
}
