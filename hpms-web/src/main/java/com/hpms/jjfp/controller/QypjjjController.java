package com.hpms.jjfp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.jjfp.entity.HPCS_QYPJJJ;
import com.hpms.jjfp.service.QypjjjSer;


@Controller
@RequestMapping("/jjfp/qypjjj")
public class QypjjjController {
	@Resource
	private QypjjjSer qypjjjSer;
	@RequestMapping
	public String show() {
		return "/jjfp/qypjjj";
	}
	@RequestMapping(value="/query",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query(@RequestParam int page, @RequestParam int rows,
			@RequestParam(required=false) Integer nd,@RequestParam(required=false) Integer yd){
		Map<String,Object> map = new HashMap<String, Object>();
		if (null!=nd)
			map.put("nd", nd);
		if (null!=yd)
			map.put("yd", yd);
		return qypjjjSer.findByPage(page, rows, map);
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HPCS_QYPJJJ item) {
		try {
			item.setLsh(null);
			item.setCjr("admin");
			item.setZt("1");
			qypjjjSer.add(item);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HPCS_QYPJJJ item) {
		System.out.println("item"+item.getLsh());
		try {
			HPCS_QYPJJJ qypj = qypjjjSer.findById(item.getLsh());
			qypj.setNd(item.getNd());
			qypj.setYd(item.getYd());
			qypj.setKsnm(item.getKsnm());
			qypj.setKspjj(item.getKspjj());
			qypjjjSer.update(qypj);
		} catch (Exception e) {	
			return "fail";
		}
		return "success";
	}
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	@ResponseBody
	public String update(String lsh) {
		
		try {
			qypjjjSer.removeById(lsh);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
}