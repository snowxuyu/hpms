package com.hpms.service;

import java.util.List;
import java.util.Map;

/**
 * 
 * 类名：BaseSer <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月8日 <br>
 * 描述：通用服务接口，主要处理单表操作 <br>
 */
public interface BaseSer<T> {

	public void add(T t);
	public void addBatch(List<T> list);
	public void update(T t);
	public void updateBatch(List<T> list);
	public void remove(T t);
	public void remove(int id);
	public void removeById(Object... id);
	public void removeBatch(List<T> list);
	public T findById(Object id);
	public List<T> find(String sqlId);
	public Map<String, Object> findByPage(String sqlId, int page, int rows, Map<String, Object> map);
	
}
