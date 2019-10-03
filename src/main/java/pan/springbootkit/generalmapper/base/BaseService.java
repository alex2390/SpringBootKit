package pan.springbootkit.generalmapper.base;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 基类 service
 *
 * Created by panzhangbao on 2018-12-23 22:56:03
 * Copyright © 2018 panzhangbao. All rights reserved.
 */
public interface BaseService<T> {

	/**
	 * 查询
	 */
	T get(Long id);
	T getByParameters(T entity);
	T getAllByParameters(T entity);

	/**
	 * 插入或更新
	 */
	@Transactional(rollbackFor = Exception.class)
    T insertOrUpdateSelective(T entity);
	@Transactional(rollbackFor = Exception.class)
    T insertOrUpdateAll(T entity);
	@Transactional(rollbackFor = Exception.class)
	Integer batchInsert(List<T> list);


	/**
	 * 删除
	 */
	@Transactional(rollbackFor = Exception.class)
	T delete(Long id);
	@Transactional(rollbackFor = Exception.class)
	Integer deleteSelective(T entity);
	@Transactional(rollbackFor = Exception.class)
    Integer realDelete(Long id);
	@Transactional(rollbackFor = Exception.class)
    Integer realDeleteSelective(T entity);

    /**
     * 查询列表
     */
	BasePage list(T entity);
	BasePage list(T entity, String orderBy);
	BasePage listAll(T entity);
	BasePage listALL(T entity, String orderBy);

	/**
	 * 统计数量
	 */
    Integer count(T entity);
    Integer countAll(T entity);
}

