package pan.springbootkit.global;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 全局配置
 *
 * Created by panzhangbao on 2019-09-08 01:20:03
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "model.author")
public class GlobalConfig {
	/**
	 * 名字
	 */
	private String name;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 妻子 Map
	 */
	private Map<String, Object> wife;

	/**
	 * 书列表
	 */
	private List<String> books;
}
