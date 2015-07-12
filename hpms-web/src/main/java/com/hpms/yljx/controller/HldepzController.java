package com.hpms.yljx.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.yljx.entity.HPCS_HLDEPZ;
import com.hpms.yljx.service.HldepzSer;

@Controller
@RequestMapping("/yljx/hldepz")
public class HldepzController {
	@Resource
	private HldepzSer hldepzser;
	@RequestMapping
	public String show() {
		return "/yljx/hldepz";
	}
	@RequestMapping(value="/query", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query(int page, int rows, @RequestParam(required=false) String ksnm) {
		return hldepzser.findByPage(page, rows, ksnm);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HPCS_HLDEPZ item) {
		try {
			item.setLsh(null);
			item.setCjr("admin");
			item.setZt("1");
			hldepzser.add(item);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HPCS_HLDEPZ item) {
		try {
			HPCS_HLDEPZ hl = hldepzser.findById(item.getLsh());
			hl.setKsnm(item.getKsnm());;
			hl.setHlssde(item.getHlssde());
			hl.setHlxsde(item.getHlxsde());
			hl.setHldsde(item.getHldsde());
			hl.setHlhdry(item.getHlhdry());
			hldepzser.update(hl);
		} catch (Exception e) {	
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public String update(String lsh) {
		try {
			hldepzser.removeById(lsh);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
}
