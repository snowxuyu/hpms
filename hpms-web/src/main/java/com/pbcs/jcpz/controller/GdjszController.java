package com.pbcs.jcpz.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pbcs.jcpz.entity.PBCS_GDJQSZ;
import com.pbcs.jcpz.service.GdjszSer;

@Controller
@RequestMapping("/pbcs/gdjsz")
public class GdjszController {
	
	@Resource
	private GdjszSer ser;
	
	@RequestMapping
	public String showIndex(){
		return "pbcs/jcpz/gdjsz";
	}
	
	@RequestMapping(value="/queryAll", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryAll(HttpServletRequest request,HttpServletResponse response,int page,int rows){
		String nd = request.getParameter("nd");
		Map<String,Object> hashMap = new HashMap<String,Object>();;
		if(nd != null && nd != ""){
			hashMap.put("nd", nd);
		}
		Map<String, Object> map = ser.find(page, rows, hashMap);
		return map;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public String insert(PBCS_GDJQSZ item){
		try {
			long day = (item.getJzsj().getTime() - item.getKssj().getTime())/(24*60*60*1000);
			item.setSc((double) (day+1));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    String kssj = sdf.format(item.getKssj());
		    item.setNd(Integer.parseInt(kssj.substring(0,4)));
			
		    ser.add(item);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(PBCS_GDJQSZ item){
		try {
			long day = (item.getJzsj().getTime() - item.getKssj().getTime())/(24*60*60*1000);
			item.setSc((double) (day+1));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		    String kssj = sdf.format(item.getKssj());
		    item.setNd(Integer.parseInt(kssj.substring(0,4)));
			
			ser.update(item);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public String remove(String lsh) {
		try {
			ser.removeById(lsh);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
}
