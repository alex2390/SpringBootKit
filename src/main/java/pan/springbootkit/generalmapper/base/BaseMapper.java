package pan.springbootkit.generalmapper.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.base.BaseSelectMapper;

/**
 * 基类 mapper
 *
 * Created by panzhangbao on 2018-12-23 14:39:02
 * Copyright © 2018 panzhangbao. All rights reserved.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T>, BaseSelectMapper<T> {
}
