package pan.springbootkit.xml.pca;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 省
 *
 * Created by panzhangbao on 2019-10-03 16:52:58
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Data
@NoArgsConstructor
public class AreaDTO implements Serializable {

	private static final long serialVersionUID = 5098500292759131858L;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 链接地址
	 */
	private String href;
}
