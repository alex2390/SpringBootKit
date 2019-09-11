package pan.springbootkit.generalmapper.base;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 基类 serviceImpl
 *
 * Created by panzhangbao on 2018-12-23 23:04:19
 * Copyright © 2018 panzhangbao. All rights reserved.
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseModel> implements BaseService<T> {
    @Autowired
    private M mapper;

    public BaseServiceImpl() {
    }

	@Override
	public T getOrDelete(Long id, String requestMethod) {
		if (id == null) {
			return null;
		}

		T entity = this.mapper.selectByPrimaryKey(id);
		if (null == entity || entity.getIsDeleted().equals(BaseModel.DELETED_FALSE)) {
			return null;
		}

		if (RequestMethod.GET.name().equals(requestMethod)) {
			return entity;
		}

		entity.setIsDeleted(BaseModel.DELETED_TRUE);
		Integer result =  this.mapper.updateByPrimaryKeySelective(entity);
		if (null == result || 0 == result) {
			return null;
		}

		return entity;
	}

	@Override
	public T getByParameters(T entity) {
		if (null == entity) {
			return null;
		} else {
			entity.setIsDeleted(BaseModel.DELETED_FALSE);
			return this.mapper.selectOne(entity);
		}
	}

	@Override
	public T getAllByParameters(T entity) {
		return null == entity ? null : this.mapper.selectOne(entity);
	}

	private void preInsert(T entity) {
		if (null == entity) {
			return;
		}
		entity.setId(null);
		entity.setCreateTime(new Date());
		entity.setUpdateTime(new Date());
		entity.setIsDeleted(BaseModel.DELETED_FALSE);
	}

	private void preUpdate(T entity) {
		if (null == entity) {
			return;
		}
		
		entity.setUpdateTime(new Date());
		entity.setIsDeleted(BaseModel.DELETED_FALSE);
	}

    @Override
    public T insertOrUpdateSelective(T entity) {
        if (null == entity) {
            return null;
        }

		if (StringUtils.isEmpty(entity.getId())) {
			preInsert(entity);
			this.mapper.insertSelective(entity);

			return entity;
		}

		preUpdate(entity);
		this.mapper.updateByPrimaryKeySelective(entity);

		return entity;
    }

    @Override
    public T insertOrUpdateAll(T entity) {
		if (null == entity) {
			return null;
		}

		/**
		 * 插入
		 */
		if (null == entity.getId()) {
			preInsert(entity);
			this.mapper.insert(entity);
			return entity;
		}

		/**
		 * 更新
		 */
		preUpdate(entity);
		this.mapper.updateByPrimaryKey(entity);

		return entity;
	}


	@Override
	public Integer batchInsert(List<T> list) {
    	if (CollectionUtils.isEmpty(list)) {
    		return null;
		}
    	
    	List<T> resultList = new ArrayList<>();
    	list.forEach(e -> {
    		preInsert(e);
    		resultList.add(e);
		});

		return this.mapper.insertList(resultList);
	}

    @Override
    public Integer deleteSelective(T entity) {
        if (null == entity) {
            return null;
        }

		entity.setIsDeleted(BaseModel.DELETED_FALSE);
		T dbEntity = this.getByParameters(entity);
		if (dbEntity == null) {
			return null;
		}

		dbEntity.setIsDeleted(BaseModel.DELETED_TRUE);
		return this.mapper.updateByPrimaryKeySelective(dbEntity);
    }

    @Override
    public Integer realDelete(Long id) {
        return id == null ? null : this.mapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer realDeleteSelective(T entity) {
        return null == entity ? null : this.mapper.delete(entity);
    }

	@Override
	public BasePage list(T entity) {
    	entity.setIsDeleted(BaseModel.DELETED_FALSE);

    	return defaultList(entity, "UPDATE_TIME DESC");
	}

	@Override
	public BasePage list(T entity, String orderBy) {
    	entity.setIsDeleted(BaseModel.DELETED_FALSE);

    	return defaultList(entity, orderBy);
	}

	@Override
	public BasePage listAll(T entity) {
		return defaultList(entity, "UPDATE_TIME DESC");
	}

	@Override
	public BasePage listALL(T entity, String orderBy) {
		return defaultList(entity, orderBy);
	}

    private BasePage defaultList(T entity, String orderBy) {
        if (null == entity) {
            return null;
        }

		BasePage basePage = new BasePage();

		/**
		 * 不分页
		 */
        if (null == entity.getPageNum() || null == entity.getPageSize()) {
        	basePage.setList(this.mapper.select(entity));

        	return basePage;
		}

		if (null == entity) {
			return null;
		}

		/**
		 * 分页
		 */
		if (entity.getPageNum() <= 0) {
			entity.setPageNum(BaseModel.PAGE_NUM_DEFAULT);
		}
		if (entity.getPageSize() <= 0) {
			entity.setPageSize(BaseModel.PAGE_SIZE_DEFAULT);
		}

		Page<T> page = PageHelper.startPage(entity.getPageNum(), entity.getPageSize(), orderBy);
		this.mapper.select(entity);

		PageInfo<T> pageInfo = new PageInfo<>(page);
		if (null == pageInfo) {
			return basePage;
		}

		basePage.setTotal((int) pageInfo.getTotal());
		basePage.setList(pageInfo.getList());

		return basePage;
    }

    @Override
    public Integer count(T entity) {
        if (null == entity) {
            return 0;
        }

		entity.setIsDeleted(BaseModel.DELETED_FALSE);
		return this.mapper.selectCount(entity);
    }

    @Override
    public Integer countAll(T entity) {
        return null == entity ? 0 : this.mapper.selectCount(entity);
    }
}

