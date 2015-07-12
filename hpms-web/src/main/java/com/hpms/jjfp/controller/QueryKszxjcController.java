package com.hpms.jjfp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hpms.jjfp.entity.HPZX_KSZXJC;
import com.hpms.jjfp.service.KszxjcSer;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月22日-下午1:23:41
 *
 * 类名： 查询科室专项奖惩控制器
 *
 * 描述:
 *
 */
@Controller
@RequestMapping("/jjfp/querykszxjc")
public class QueryKszxjcController {
	
	@Resource
	private  KszxjcSer kszxjcSer;
	
	@RequestMapping
	public String showIndex(){
		return "jjfp/querykszxjc";
	}
	
	
	/**
	 * 页面数据的查询方法
	 * @param request
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/find", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request, int page, int rows){
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
//System.out.println(nd+","+yd+","+ksnm);
		if (!"".equals(nd) && null!=nd) map.put("nd", nd);
		if (!"".equals(yd) && null!=yd) map.put("yd", yd);
		if (!"".equals(ksnm) && null!=ksnm) map.put("ksnm", ksnm);
		return kszxjcSer.find(page, rows, map);
	}
	
}
