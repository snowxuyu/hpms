package com.hpms.zhpj.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.zhpj.entity.BPC_SM005;
import com.hpms.zhpj.service.CjsjcxSer;

@Controller
@RequestMapping("/zhpj/cjsjcx")
public class CjsjcxController {
	@Resource
	private CjsjcxSer cjsjcxser;
	@RequestMapping
	public String showIndex(){
		return "/zhpj/cjsjcx";
	}
	@RequestMapping(value="/query", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> find(int page, int rows, BPC_SM005 item){
		Map<String,Object> map = new HashMap<String, Object>();
		if (item.getNd()!=null) {
			map.put("nd", item.getNd());
		}
		if (item.getYd()!=null) {
			map.put("yd", item.getYd());
		}
		if (item.getDxbm()!=null && !"".equals(item.getDxbm())) {
			map.put("dxbm", item.getDxbm());
		}
		if (item.getDxmc()!=null && !"".equals(item.getDxmc())) {
			map.put("dxmc", item.getDxmc());
		}
		return cjsjcxser.findByPage(page, rows, map);
	}
}
