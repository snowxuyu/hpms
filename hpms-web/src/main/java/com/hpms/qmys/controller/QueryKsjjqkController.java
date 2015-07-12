package com.hpms.qmys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.qmys.entity.HPXC_XCKSJS;
import com.hpms.qmys.service.QueryKsjjqkSer;
import com.hpms.qmys.vo.TempVO;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年2月6日-下午4:00:16
 *
 * 类名： 科室奖金情况查询
 *
 * 描述:
 *
 */
@Controller
@RequestMapping("/qmys/queryksjjqk")
public class QueryKsjjqkController {
	@Resource
	private QueryKsjjqkSer queryksjjqkSer;
	
	@RequestMapping
	public String showIndex(String detail,HttpServletRequest request, Model model) {
		String[] strs = detail.split(",");
		/*String ksmc = request.getParameter("ksmc");
		String ksnm = request.getParameter("ksnm");
		String nd = request.getParameter("nd");
		String yd = request.getParameter("yd");*/
		model.addAttribute("ksmc", strs[3]);
		model.addAttribute("ksnm", strs[2]);
		model.addAttribute("nd", strs[0]);
		model.addAttribute("yd", strs[1]);
		if (strs[2] == null || strs[2].equals("null")) {
			return "qmys/queryksjjqkAll";
		}
		return "qmys/queryksjjqk";
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
		
		if (!"".equals(_nd) && null!=_nd){
			nd = Integer.parseInt(_nd);
		}
		if (!"".equals(_yd) && null!=_yd){
			yd = Integer.parseInt(_yd);
		}
		
//System.out.println(nd+","+yd+","+ksnm+","+ygxm);
		if (!"".equals(nd) && null!=nd) map.put("nd", nd);
		if (!"".equals(yd) && null!=yd) map.put("yd", yd);
		if (!"".equals(ksnm) && null!=ksnm) map.put("ksnm", ksnm);
		
		return queryksjjqkSer.find(page, rows, map);
	}
	
	/**
	 * 页面数据的查询方法
	 * @param request
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/findAll", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findAll(HttpServletRequest request, int page, int rows){
		Map<String,Object> map = new HashMap<String, Object>();
		String nd = request.getParameter("nd");
		String yd = request.getParameter("yd");
		String ksnm = request.getParameter("ksnm");
		
		if (!"".equals(nd) && null!=nd){
			map.put("nd", nd);
		}
		if (!"".equals(yd) && null!=yd){
			map.put("yd", yd);
		}
		TempVO bean = queryksjjqkSer.findAll(map);
		if (!"".equals(ksnm) && null!=ksnm) {
			map.put("ksnm", ksnm);
		}
		
		Map<String, Object> map2 = queryksjjqkSer.findAll(page, rows, map);
		
		HashMap<String, Object> footMap = new HashMap<String, Object>();
		ArrayList<Object> list = new ArrayList<Object>();
		footMap.put("ksmc", "合计");
		footMap.put("cl", bean.getP1());
		footMap.put("grzxjj", bean.getP2());
		footMap.put("kszxjj", bean.getP3());
		footMap.put("bhgzl", bean.getP4());
		footMap.put("fpjj", bean.getP5());
		list.add(footMap);
		map2.put("footer", list);
		return map2;
	}
	
	@RequestMapping(value="/charts", method=RequestMethod.POST)
	@ResponseBody
	public List<HPXC_XCKSJS> charts(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer nd = null;
		Integer yd = null;
		String _nd = request.getParameter("nd");
		String _yd = request.getParameter("yd");
		String ksnm = request.getParameter("ksnm");
		
		if (!"".equals(_nd) && null!=_nd){
			nd = Integer.parseInt(_nd);
		}
		if (!"".equals(_yd) && null!=_yd){
			yd = Integer.parseInt(_yd);
		}
		
		if (!"".equals(nd) && null!=nd) {
			map.put("nd1", nd);
			map.put("nd2", nd-1);
		}
		if (!"".equals(yd) && null!=yd) {
			map.put("yd1", yd);
			map.put("yd2", yd);
		}
		if (!"".equals(ksnm) && null!=ksnm) {
			map.put("ksnm", ksnm);
		}
		
		return queryksjjqkSer.findCharts(map);
	}
}
