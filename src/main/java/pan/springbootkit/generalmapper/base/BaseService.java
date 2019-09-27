//package pan.springbootkit.generalmapper.base;
//
//import java.util.List;
//
///**
// * 基类 service
// *
// * Created by panzhangbao on 2018-12-23 22:56:03
// * Copyright © 2018 panzhangbao. All rights reserved.
// */
//public interface BaseService<T> {
//
//	/**
//	 * 查询
//	 */
//	T getOrDelete(Long id, String requestMethod);
//	T getByParameters(T entity);
//	T getAllByParameters(T entity);
//
//	/**
//	 * 插入或更新
//	 */
//    T insertOrUpdateSelective(T entity);
//    T insertOrUpdateAll(T entity);
//	Integer batchInsert(List<T> list);
//
//
//	/**
//	 * 删除
//	 */
//	Integer deleteSelective(T entity);
//    Integer realDelete(Long id);
//    Integer realDeleteSelective(T entity);
//
//    /**
//     * 查询列表
//     */
//	BasePage list(T entity);
//	BasePage list(T entity, String orderBy);
//	BasePage listAll(T entity);
//	BasePage listALL(T entity, String orderBy);
//
//	/**
//	 * 统计数量
//	 */
//    Integer count(T entity);
//    Integer countAll(T entity);
//}
//
