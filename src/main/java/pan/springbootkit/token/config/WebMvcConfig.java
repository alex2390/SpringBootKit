package pan.springbootkit.token.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 * 拦截器 配置
 *
 * by panzhangbao on 2019-09-30 17:39:38
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

	@Resource
	private TokenInterceptor tokenInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		// addPathPatterns 用于添加拦截规则
		// excludePathPatterns 用户排除拦截
		registry.addInterceptor(tokenInterceptor).addPathPatterns( "/api/test/**" );

		super.addInterceptors(registry);
	}
}
