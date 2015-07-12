package com.hpms.jjfp.service;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.hpms.jjfp.entity.HPZX_KSZXJC;
import com.hpms.service.BaseSer;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月22日-下午1:23:41
 *
 * 类名： 科室专项奖惩业务层接口
 *
 * 描述:
 *
 */
public interface KszxjcSer extends BaseSer<HPZX_KSZXJC> {
	
	/**
	 * 科室专项奖惩分页查询
	 * @param page
	 * @param rows
	 * @param map
	 * @return
	 */
	public Map<String, Object> find(int page, int rows, Map<String, Object> map);
	
	/**
	 * 点击新增，修改   页面点击选择科室按钮后弹出的查询实现
	 * @param page
	 * @param rows
	 * @param map
	 * @return
	 */
	public Map<String, Object> findKsxx(int page, int rows, Map<String, Object> map);
	
	/**
	 * 科室专项奖金导入
	 * @param file
	 * @param nd
	 * @param yd
	 * @return
	 */
	public String importKszxjj(MultipartFile file, Integer nd, Integer yd);
	
	/**
	 * 科室专项奖金模板的导出
	 * @return
	 */
	public HSSFWorkbook exportKszxjj(Integer nd, Integer yd);
}
