package com.wjt.zam.common.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public abstract interface BaseService<T,ID extends Serializable> {
	  public abstract int insert(T t);
	  
	  public abstract int update(T t);
	  
	  public abstract int delete(T t);
	  
	  public abstract int batchDelete(ID[] ids);
	  
	  public abstract int deleteById(ID t);
	  
	  public abstract T findById(ID t);
	  
	  public abstract List<T> findAll();
	  
	  public abstract List<T> findList(Map<String, Object> t);
	  
	  public abstract List<T> findList(T t);
	  
	  @SuppressWarnings("unchecked")
	  public abstract List<T> findList(ID... paramVarArgs);
	  
	  public abstract boolean exists(ID t);
	  
}
