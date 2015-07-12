package com.pbcs.jcpz.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.yljx.entity.HPCS_BBFLK;
import com.hpms.yljx.vo.Combobox;
import com.pbcs.jcpz.service.PbzdszSer;

@Controller
@RequestMapping("/pbcs/pbzdsz")
public class PbzdszController {

	@Resource
	private PbzdszSer ser;
	
	@RequestMapping
	public String showIndex(){
		return "pbcs/jcpz/pbzdsz";
	}
	
	@RequestMapping(value="/queryAll", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryAll(HttpServletRequest request,HttpServletResponse response,int page,int rows){
		String bbnm = request.getParameter("bbnm");
		Map<String,Object> hashMap = new HashMap<String,Object>();;
		if(bbnm != null && bbnm != ""){
			hashMap.put("bbnm", bbnm);
		}
		Map<String, Object> map = ser.find(page, rows, hashMap);
		return map;
	}
	
	@RequestMapping(value="/queryList", method=RequestMethod.POST)
	@ResponseBody
	public List<Combobox> queryList(HttpServletRequest request,HttpServletResponse response){
		List<Combobox> list = ser.getList();
		return list;
 	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(HPCS_BBFLK item){
		try {
			if (ser.findById(item.getBbbm()) != null) {
				return "exist";
			}
			ser.add(item);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(HPCS_BBFLK item){
		try {
			ser.update(item);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public String remove(String bbbm) {
		try {
			if(ser.existBBBM(bbbm)){
				return "exist";
			}
			ser.removeById(bbbm);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
}
