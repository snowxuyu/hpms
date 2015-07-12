package com.hpms.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.hpms.service.BaseSer;
import com.hpms.util.PageUtil;
import com.hpms.util.UtilStr;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

/**
 * 
 * 类名：BaseSerImpl <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月8日 <br>
 * 描述：通用服务接口实现类，主要处理单表操作 <br>
 */
public class BaseSerImpl<T> implements BaseSer<T> {
	
	private Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public BaseSerImpl() {
		Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass =  (Class<T>)params[0];
	}

	@Override
	public void add(T t) {
		DBUtil.create(t);
		
	}

	@Override
	public void addBatch(List<T> list) {
		DBUtil.createBatch(list);
		
	}

	@Override
	public void update(T t) {
		DBUtil.update(t);
		
	}

	@Override
	public void updateBatch(List<T> list) {
		DBUtil.updateBatch(list);
		
	}

	@Override
	public void remove(T t) {
		DBUtil.delete(t);
		
	}

	@Override
	public void remove(int id) {
		DBUtil.deleteById(entityClass, id);
		
	}

	@Override
	public void removeBatch(List<T> list) {
		DBUtil.deleteBatch(list);
		
	}

	@Override
	public T findById(Object id) {
		return DBUtil.findById(entityClass, id);
	}

	@Override
	public List<T> find(String sqlId) {
		SqlResult sql = SqlUtil.getSql(sqlId, null);
		return DBUtil.find(sql.getSql(),entityClass);
	}

	@Override
	public Map<String, Object> findByPage(String sqlId, int page, int rows,
			Map<String, Object> map) {
		for (String key : map.keySet()) {
			if (UtilStr.isNull(map.get(key))) {
				map.remove(key);
			}
		}
		SqlResult sql = SqlUtil.getSql(sqlId, map);
		return PageUtil.getPageDataJson(page, rows, sql, entityClass);
	}

	@Override
	public void removeById(Object... id) {
		DBUtil.deleteById(entityClass, id);
		
	}

}
