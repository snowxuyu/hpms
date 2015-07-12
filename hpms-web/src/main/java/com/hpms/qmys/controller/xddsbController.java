package com.hpms.qmys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.qmys.service.HlcxSer;
import com.hpms.qmys.service.xddssbSer;
import com.rongda.framework.util.StringUtil;

@Controller
@RequestMapping("/qmys/xddsbAction")
public class xddsbController {

	@Autowired
	private xddssbSer ser;
	
	@Autowired
	private HlcxSer Hser;
	
	@RequestMapping
	public String showIndex(@RequestParam String detail,Model model){
		String[] strs = detail.split(",");
		model.addAttribute("nd", strs[0]);
		model.addAttribute("yd", strs[1]);
		model.addAttribute("ksnm", strs[2]);
		model.addAttribute("ksmc",strs[3]);
		return "qmys/xddsb";
	}
	
	@RequestMapping(value="/queryAll", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryAll(HttpServletRequest request,HttpServletResponse response,int page,int rows){
		String ksnm = request.getParameter("ksnm");
		String nd = request.getParameter("nd");
		String yd = request.getParameter("yd");
		Map<String, Object> hashMap = new HashMap<String,Object>();
		if(!StringUtil.isNullAndSpace(ksnm)){
			hashMap.put("ksnm", ksnm);
		}
		if(!StringUtil.isNullAndSpace(nd)){
			hashMap.put("nd", nd);
		}
		if(!StringUtil.isNullAndSpace(yd)){
			hashMap.put("yd", yd);
		}
		
		return ser.selectAll(hashMap, page, rows);
	}
	
	@RequestMapping(value="/hlmx", method=RequestMethod.GET)
	public String queryHlxm(String detail,Model model) {
		String[] strs = detail.split(",");
		model.addAttribute("nd", strs[0]);
		model.addAttribute("yd", strs[1]);
		model.addAttribute("ksnm", strs[2]);
		model.addAttribute("ksmc", strs[3]);
		return "qmys/hlmx";
	}
	
	@RequestMapping(value="/zlmx", method=RequestMethod.GET)
	public String queryZlxm(String detail,Model model) {
		String[] strs = detail.split(",");
		model.addAttribute("nd", strs[0]);
		model.addAttribute("yd", strs[1]);
		model.addAttribute("ksnm", strs[2]);
		model.addAttribute("ksmc", strs[3]);
		return "qmys/zlmx";
	}
	
	@RequestMapping(value="/queryHl", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryHl(HttpServletRequest request,HttpServletResponse response,int page,int rows){
		String ksnm = request.getParameter("ksnm");
		String nd = request.getParameter("nd");
		String yd = request.getParameter("yd");
		String xmlbbm = request.getParameter("xmlbbm");
		Map<String, Object> hashMap = new HashMap<String,Object>(); 
		if(!StringUtil.isNullAndSpace(ksnm)){
			hashMap.put("ksnm", ksnm);
		}
		if(!StringUtil.isNullAndSpace(nd)){
			hashMap.put("nd", nd);
		}
		if(!StringUtil.isNullAndSpace(yd)){
			hashMap.put("yd", yd);
		}
		if(!StringUtil.isNullAndSpace(xmlbbm)){
			hashMap.put("xmlbbm", xmlbbm);
		}
		
		return Hser.selectHl(hashMap, page, rows);
	}
	
}
