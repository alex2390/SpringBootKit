package pan.springbootkit.mongodb.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * MongoDB id entity
 *
 * Created by panzhangbao on 2019-09-24 11:05:20
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MongoDBID extends MongoDBBaseModel implements Serializable {

	private static final long serialVersionUID = 2156444305167504739L;

	/**
	 * 集合名称
	 */
	private String collectionName;

	/**
	 *  下一 id
	 */
	private Integer nextId;
}
