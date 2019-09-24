//package pan.springbootkit.mongodb.config;
//
//import com.mongodb.MongoClient;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
//import org.springframework.stereotype.Component;
//
///**
// * MongoDB 配置
// *
// * Created by panzhangbao on 2019-09-22 10:05:56
// * Copyright © 2019 panzhangbao. All rights reserved.
// */
//@Getter
//@Setter
////@Component
////@ConfigurationProperties("spring.data.mongodb")
//@Configuration
//public class MongoDBConfig {
//	/**
//	 * 服务器 ip
//	 */
//	private String host = "localhost";
//
//	/**
//	 * 端口
//	 */
//	private Integer port = 27017;
//
//	/**
//	 * 数据库名
//	 */
//	private String database = "panTest";
//
//	/**
//	 * 根据自己创建的工厂初始化一个template
//	 *
//	 * @param
//	 * @return org.springframework.data.mongodb.core.MongoTemplate
//	 * @date 2019-09-22 10:28
//	 * @author panzhangbao
//	 */
//	@Bean
//	@Primary
//	public MongoTemplate template() {
//		return new MongoTemplate(factory());
//	}
//
//	/**
//	 * 创建数据库名称对应的工厂
//	 *
//	 * @param
//	 * @return org.springframework.data.mongodb.MongoDbFactory
//	 * @date 2019-09-22 10:27
//	 * @author panzhangbao
//	 */
//	@Bean("mongoDbFactory")
//	public MongoDbFactory factory() {
//		return new SimpleMongoDbFactory(client(), database);
//	}
//
//	/**
//	 * 配置 client
//	 *
//	 * @param
//	 * @return com.mongodb.MongoClient
//	 * @date 2019-09-22 10:07
//	 * @author panzhangbao
//	 */
//	@Bean("mongoClient")
//	public MongoClient client() {
//		return new MongoClient(host, port);
//	}
//
//}
