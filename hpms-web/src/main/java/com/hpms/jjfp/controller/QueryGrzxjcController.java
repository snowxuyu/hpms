package com.hpms.jjfp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hpms.jjfp.entity.HPZX_GRZXJC;
import com.hpms.jjfp.service.GrzxjcSer;
import com.hpms.jjfp.service.KszxjcSer;
import com.hpms.yljx.vo.Combobox;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月19日-下午1:57:53
 *
 * 类名： 查询个人专项奖惩控制器
 *
 * 描述:
 *
 */
@Controller
@RequestMapping("/jjfp/querygrzxjc")
public class QueryGrzxjcController {
	
	@Resource
	private GrzxjcSer grzxjcSer;
	@Resource
	private KszxjcSer kszxjcSer;
	
	@RequestMapping
	public String showIndex(HttpServletRequest request, Model model){
		String ksmc = request.getParameter("ksmc");
		String ksnm = request.getParameter("ksnm");
		String nd = request.getParameter("nd");
		String yd = request.getParameter("yd");
		model.addAttribute("ksmc", ksmc);
		model.addAttribute("ksnm", ksnm);
		model.addAttribute("nd", nd);
		model.addAttribute("yd", yd);
		return "jjfp/querygrzxjc";
	}
	
	/**
	 * 页面数据的查询方法
	 * @param request
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/findGr", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findGr(HttpServletRequest request, int page, int rows){
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> map2 = new HashMap<String, Object>();
		Integer nd = null;
		Integer yd = null;
		String _nd = request.getParameter("nd");
		String _yd = request.getParameter("yd");
		String ksnm = request.getParameter("ksnm");
		String ygxm = request.getParameter("ygxm");
		
		if (!"".equals(_nd) && null!=_nd){
			nd = Integer.parseInt(_nd);
		}
		if (!"".equals(_yd) && null!=_yd){
			yd = Integer.parseInt(_yd);
		}
		
		if (!"".equals(nd) && null!=nd) map2.put("nd", nd);
		if (!"".equals(yd) && null!=yd) map2.put("yd", yd);
		if (!"".equals(ksnm) && null!=ksnm) map2.put("ksnm", ksnm);
		if (!"".equals(ygxm) && null!=ygxm) map2.put("ygxm", ygxm);
		map =  grzxjcSer.find(page, rows, map2);
		return map;
	}
	
	/**
	 * 页面数据的查询方法
	 * @param request
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/findKs", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findKs(HttpServletRequest request, int page, int rows){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer nd = null;
		Integer yd = null;
		String _nd = request.getParameter("nd");
		String _yd = request.getParameter("yd");
		String ksnm = request.getParameter("ksnm");
		if (!"".equals(_nd) && null!=_nd)
		{
			nd = Integer.parseInt(_nd);
		}
		if (!"".equals(_yd) && null!=_yd)
		{
			yd = Integer.parseInt(_yd);
		}
		if (!"".equals(nd) && null!=nd) map.put("nd", nd);
		if (!"".equals(yd) && null!=yd) map.put("yd", yd);
		if (!"".equals(ksnm) && null!=ksnm) map.put("ksnm", ksnm);
		return kszxjcSer.find(page, rows, map);
	}

	
}
