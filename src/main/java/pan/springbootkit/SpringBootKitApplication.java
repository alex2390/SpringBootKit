package pan.springbootkit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

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
@ServletComponentScan
@SpringBootApplication
public class SpringBootKitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootKitApplication.class, args);
	}

}
