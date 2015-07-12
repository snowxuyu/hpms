package com.hpms.yljx.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hpms.yljx.entity.HPBH_BHGRMX;
import com.hpms.yljx.entity.HPBH_BHKSMX;
import com.hpms.yljx.service.HxxmcfblGrSer;
import com.hpms.yljx.service.HxxmcfblKsSer;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月19日-上午11:51:07
 *
 * 类名： 核心项目拆分补录控制器
 *
 * 描述:
 *
 */
@Controller
@RequestMapping("/yljx/hxxmcfbl")
public class HxxmcfblController {
	
	@Resource
	private HxxmcfblKsSer hxxmcfblKsSer;
	@Resource
	private HxxmcfblGrSer hxxmcfblGrSer;
	
	@RequestMapping
	public String showIndex(){
		return "yljx/hxxmcfbl";
	}
	
	/**
	 * 补录科室
	 * @param item
	 * @return
	 */
	@RequestMapping(value="/2", method=RequestMethod.POST)
	@ResponseBody
	public String addKs(HPBH_BHKSMX item) {
		try {
//System.out.println(item.toString());
			item.setLsh(null);
			item.setCjr("admin");
			item.setZt("1");
			hxxmcfblKsSer.add(item);
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}
	
	/**
	 * 补录个人
	 * @param item
	 * @return
	 */
	@RequestMapping(value="/1", method=RequestMethod.POST)
	@ResponseBody
	public String addGr(HPBH_BHGRMX item) {
		try {
//System.out.println(item.toString());
			item.setLsh(null);
			item.setCjr("admin");
			item.setZt("1");
			hxxmcfblGrSer.add(item);
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}
	
	@RequestMapping(value="/find", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> find(int page, int rows, HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		Integer nd = null;
		Integer yd = null;
		String _nd = request.getParameter("nd");
		String _yd = request.getParameter("yd");
		String ksmc = request.getParameter("ksmc");
		String ygmc = request.getParameter("ygmc");
		if (!"".equals(_nd) && null!=_nd) {
			nd = Integer.parseInt(_nd);
		}
		if (!"".equals(_yd) && null!=_yd) {
			yd = Integer.parseInt(_yd);
		}
//System.out.println(nd+","+yd+","+ksmc+","+ygmc);
		if (!"".equals(nd) && null!=nd)
			map.put("nd", nd);
		if (!"".equals(yd) && null!=yd)
			map.put("yd", yd);
		if (!"".equals(ksmc) && null!=ksmc)
			map.put("ksmc", ksmc);
		if (!"".equals(ygmc) && null!=ygmc)
			map.put("ygmc", ygmc);
		return hxxmcfblGrSer.find(page, rows, map);
	}
	
	@RequestMapping(value="/deleteById", method=RequestMethod.POST)
	@ResponseBody
	public String deleteById(String lsh, String lx){
		try{
//System.out.println(lsh+","+lx);
			if ("A".equals(lx)) {
				hxxmcfblGrSer.removeById(lsh);
			}
			if ("B".equals(lx)) {
				hxxmcfblKsSer.removeById(lsh);
			}
		} catch (Exception e){
			return "error";
		}
			return "success";
	}
	
	@RequestMapping(value="/import", method=RequestMethod.POST)
	@ResponseBody
	public String importCfbl(@RequestParam(value="file", required=false) MultipartFile file, HttpServletRequest request) {
		Integer nd = null;
		Integer yd = null;
		String ksnm = request.getParameter("ksnm");
		String _nd = request.getParameter("nd");
		String _yd = request.getParameter("yd");
		
		if (!"".equals(_nd) && null!=_nd) {
			nd = Integer.parseInt(_nd);
		}
		if (!"".equals(_yd) && null!=_yd) {
			yd = Integer.parseInt(_yd);
		}
		//hxxmcfblKsSer.importCfbl(file, ksnm, nd, yd);
		return "";
	}
}
