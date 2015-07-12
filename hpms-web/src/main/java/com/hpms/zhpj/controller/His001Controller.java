package com.hpms.zhpj.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.zhpj.entity.BPC_HIS001;
import com.hpms.zhpj.service.His001Ser;

@Controller
@RequestMapping("/zhpj/hisjksjcx")
public class His001Controller {
	
	@Resource
	private His001Ser his001Ser; 
	
	@RequestMapping
	public String showIndex(){
		return "zhpj/hisjkcx";
	}
	
	@RequestMapping(value="/find", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> find(int page, int rows, BPC_HIS001 his){
//System.out.println(his.getNd()+"-"+his.getYd()+"-"+his.getDxmc()+"-"+his.getXmmc());
		Map<String,Object> map = new HashMap<String, Object>();
		if (his.getNd()!=null) {
			map.put("nd", his.getNd());
		}
		if (his.getYd()!=null) {
			map.put("yd", his.getYd());
		}
		if (his.getDxmc()!=null && !"".equals(his.getDxmc())) {
			map.put("dxmc", his.getDxmc());
		}
		if (his.getXmmc()!=null && !"".equals(his.getXmmc())) {
			map.put("xmmc", his.getXmmc());
		}
		return his001Ser.find(page, rows, map);
	}
}
