package com.hpms.jjfp.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.hpms.jjfp.entity.HPZX_GRZXJC;
import com.hpms.service.BaseSer;
import com.hpms.yljx.vo.Combobox;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月19日-下午3:28:53
 *
 * 类名： 个人专项奖惩业务层接口
 *
 * 描述:
 *
 */
public interface GrzxjcSer extends BaseSer<HPZX_GRZXJC> {
	/**
	 * 个人专项奖惩分页查询
	 * @param page
	 * @param rows
	 * @param map
	 * @return
	 */
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
	
	/**
	 * 点击新增，修改   页面出选择人员按钮后弹出的查询实现
	 * @param page
	 * @param rows
	 * @param map
	 * @return
	 */
	public Map<String, Object> findYgxx(int page, int rows, Map<String, Object> map);
	
	/**
	 * 查询员工列表
	 * @return
	 */
	public List<Combobox> queryYg();
	
	/**
	 * 个人专项奖金导入
	 * @param file
	 * @param nd
	 * @param yd
	 * @return
	 */
	public String importGrzxjj(MultipartFile file, Integer nd, Integer yd);
	
	/**
	 * 个人专项奖金模板的导出
	 * @return
	 */
	public HSSFWorkbook exportGrzxjj(String ksnm, Integer nd, Integer yd);
}
