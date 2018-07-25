package com.wjt.zam.common.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;


public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {
	public abstract BaseMapper<T, ID> getBaseMapper();

	@Transactional
	public int insert(T baseObj) {
		return getBaseMapper().insert(baseObj);
	}

	@Transactional
	public int update(T baseObj) {
		return getBaseMapper().update(baseObj);
	}

	@Transactional
	public int delete(T baseObj) {
		return getBaseMapper().delete(baseObj);
	}

	@Transactional
	public int batchDelete(ID[] ids) {
		return getBaseMapper().batchDelete(ids);
	}

	@Transactional
	public int deleteById(ID id) {
		return delete(getBaseMapper().findById(id));
	}

	@Transactional(readOnly = true)
	public T findById(ID id) {
		return (T) getBaseMapper().findById(id);
	}

	@Transactional(readOnly = true)
	public List<T> findAll() {
		return getBaseMapper().findAll();
	}

	@Transactional(readOnly = true)
	public List<T> findList(Map<String, Object> params) {
		return getBaseMapper().findList(params);
	}
	
	@Transactional(readOnly = true)
	public List<T> findList(T t) {
		return getBaseMapper().findList(t);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(readOnly = true)
	public List<T> findList(ID... ids) {
		List<T> result = new ArrayList();
		if (ids != null) {
			Serializable[] arrayOfSerializable;
			int j = (arrayOfSerializable = ids).length;
			for (int i = 0; i < j; i++) {
				ID id = (ID) arrayOfSerializable[i];
				T entity = findById(id);
				if (entity != null) {
					result.add(entity);
				}
			}
		}
		return result;
	}

	@Transactional(readOnly = true)
	public boolean exists(ID id) {
		return getBaseMapper().findById(id) != null;
	}
}
