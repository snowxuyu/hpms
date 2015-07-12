package com.hpms.jjfp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.jjfp.service.JjfpSer;
import com.hpms.qmys.vo.BhgzlCondition;

@Controller
@RequestMapping("/jjfp/jj")
public class JjfpController {

	@Autowired
	private JjfpSer ser;
	
	@RequestMapping(value="/gr", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryByGr(@RequestParam("page") int page, @RequestParam("rows") int rows, String nd, String yd, String ygbh) {
		BhgzlCondition condition = new BhgzlCondition();
		condition.setNd(nd);
		condition.setYd(yd);
		condition.setYgbh(ygbh);
		return ser.findByGr(page, rows, condition.getMap());
	}
	
}
