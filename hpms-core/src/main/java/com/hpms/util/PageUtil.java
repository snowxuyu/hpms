package com.hpms.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.hpms.entity.Page;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;

/**
 * 分页工具
 *
 */
public class PageUtil {
	
	/**
	 * 获取总共的记录条数
	 * @param map 参数条件
	 * @param sql 执行的SQL
	 * @return 
	 */
	private static int getPageTotal(SqlResult sql){
		List<Page> list =  DBUtil.find("select count(1) total from (" + sql.getSql() + ")",sql.getParam(),Page.class);
		return list.get(0).getTotal();
	}
	
	/**
	 * 获取总共的记录条数
	 * @param page 当前页
	 * @param rows 当前行数
	 * @param clazz 参数类
	 * @param sql 执行的SQL
	 * @return 
	 */
	private static List<?> getPageList(int page,int rows, SqlResult sql,Class<?> clazz){
		int startNum = (page - 1) * rows;
		int endNum = page * rows;
		
		List<?> list=null;
		try {
			list = DBUtil.find("select t1.* from (select rownum rum ,t.* from ("
										+ sql.getSql() + ") t) t1 where rum >  " + startNum + " and rum <= " + endNum ,sql.getParam(),clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		List list =  DBUtil.find("(select * from ( " + sql.getSql() + ") t where rownum <= " + endNum +")"
					 	+ " minus (select * from ( " + sql.getSql() + ") t where rownum <= " + startNum + ")" ,sql.getParam(),clazz);
		*/
		return list;
	}
	
	/**
	 * 封装当前的Page
	 * @param page
	 * @param rows
	 * @param sql
	 * @param clazz
	 * @return
	 */
	public static Page getPageData(int page,int rows, SqlResult sql,Class<?> clazz){
		
		Page curPage = new Page();
		curPage.setTotal(getPageTotal(sql));
		curPage.setList(getPageList(page, rows, sql, clazz));
		return curPage;
	}

	/**
	 * 封装当前的Page
	 * @param page
	 * @param rows
	 * @param sql
	 * @param clazz
	 * @return
	 */
	public static Map<String, Object> getPageDataJson(int page,int rows, SqlResult sql,Class<?> clazz){
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", getPageTotal(sql));// total键 存放总记录数，必须的
		jsonMap.put("rows", getPageList(page, rows, sql, clazz));// rows键 存放每页记录 list
		return jsonMap;
	}
}
