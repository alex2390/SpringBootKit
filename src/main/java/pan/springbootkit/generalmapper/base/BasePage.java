package pan.springbootkit.generalmapper.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 基类 page
 *
 * Created by panzhangbao on 2019-09-11 23:20:14
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Data
@NoArgsConstructor
public class BasePage<T> implements Serializable {

	private static final long serialVersionUID = -1119260082690897576L;

	/**
	 * 总数量
	 */
	private Integer total = 0;

	/**
	 * 数据列表
	 */
	private List<T> list;
}
