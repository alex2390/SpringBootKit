package pan.springbootkit.generalmapper.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import pan.springbootkit.generalmapper.base.BaseMapper;
import pan.springbootkit.generalmapper.model.Area;

/**
 * 区域 mapper
 *
 * Created by panzhangbao on 2019-10-04 00:08:40
 * Copyright © 2018 panzhangbao. All rights reserved.
 */
@Mapper
@Component
public interface AreaMapper extends BaseMapper<Area> {
}