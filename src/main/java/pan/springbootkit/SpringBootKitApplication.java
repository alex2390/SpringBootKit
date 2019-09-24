package pan.springbootkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.MultipartConfigElement;

/**
 * 启动应用
 * 		Web开发使用 Controller 基本上可以完成大部分需求，但是我们还可能会用到 Servlet、Filter、Listener、Interceptor 等等。
 *
 * 		在spring boot中添加自己的 Servlet 有两种方法，代码注册Servlet和注解自动注册（Filter和Listener也是如此）。
 *  		一、代码注册通过ServletRegistrationBean、 FilterRegistrationBean 和 ServletListenerRegistrationBean 获得控制。
 *      	也可以通过实现 ServletContextInitializer 接口直接注册。
 *
 *  		二、在 SpringBootApplication 上使用@ServletComponentScan 注解后，
 *      	Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码。
 *
 *  	执行顺序：listener > filter > interceptor > servlet
 *
 * Created by panzhangbao on 2019-09-12 08:38:46
 * Copyright © 2018 panzhangbao. All rights reserved.
 */
//@ServletComponentScan
@SpringBootApplication
public class SpringBootKitApplication {

	/**
	 * 解决 web 前端跨域问题
	 */
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		// 允许cookies跨域
		config.setAllowCredentials(true);
		// #允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
		// #允许访问的头信息,*表示全部
		config.addAllowedHeader("*");
		config.addAllowedOrigin("*");
		// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
		config.setMaxAge(18000L);
		// 允许提交请求的方法，*表示全部允许
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKitApplication.class, args);
	}

}
