package pan.springbootkit.utils.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by panzhangbao on 2019-09-01 13:35:57
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Data
@NoArgsConstructor
public class TestModel implements Serializable {

	private static final long serialVersionUID = 4661353704809733460L;

	private Integer id;

	private String name;

	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdTime;

	private TestModel innerModel;

	private Map innerMap;

	private List innerList;
}
