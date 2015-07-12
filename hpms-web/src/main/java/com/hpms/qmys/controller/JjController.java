package com.hpms.qmys.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.qmys.service.JjSer;
import com.hpms.qmys.vo.BhgzlCondition;

/**
 * 
 * 类名：BhgzlController <br>
 * 作者：吴陶君 <br>
 * 日期：2015年3月19日 <br>
 * 描述：奖金控制器 <br>
 */
@Controller
@RequestMapping("/qmys/jj")
public class JjController {

	@Autowired
	private JjSer ser;
	
	@RequestMapping(value="/gr", method=RequestMethod.GET)
	public String queryGr(String detail,Model model) {
		String[] strs = detail.split(",");
		model.addAttribute("nd", strs[0]);
		model.addAttribute("yd", strs[1]);
		model.addAttribute("ksbh", strs[2]);
		model.addAttribute("ksmc", strs[3]);
		return "/qmys/jj_gr";
	}
	
	@RequestMapping(value="/gr", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryByGr(@RequestParam("page") int page, @RequestParam("rows") int rows, String nd, String yd, String ksbh) {
		BhgzlCondition condition = new BhgzlCondition();
		condition.setNd(nd);
		condition.setYd(yd);
		condition.setKsnm(ksbh);
		return ser.findByGr(page, rows, condition.getMap());
	}
	
	@RequestMapping(value="/gr2", method=RequestMethod.GET)
	public String queryGr2(String detail,Model model) {
		String[] strs = detail.split(",");
		model.addAttribute("nd", strs[0]);
		model.addAttribute("yd", strs[1]);
		model.addAttribute("ksbh", strs[2]);
		model.addAttribute("ksmc", strs[3]);
		return "/qmys/jj_gr2";
	}
	
	@RequestMapping(value="/gr2", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryByGr2(@RequestParam("page") int page, @RequestParam("rows") int rows, String nd, String yd, String ksbh) {
		BhgzlCondition condition = new BhgzlCondition();
		condition.setNd(nd);
		condition.setYd(yd);
		condition.setKsnm(ksbh);
		return ser.findByGr2(page, rows, condition.getMap());
	}
	
}
