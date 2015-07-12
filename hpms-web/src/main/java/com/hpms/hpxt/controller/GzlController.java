package com.hpms.hpxt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.hpxt.entity.HPXT_GZLMX;
import com.hpms.hpxt.service.GzkmxSer;
import com.rongda.framework.util.StringUtil;

@Controller
@RequestMapping("/hpxt/gzlAction")
public class GzlController {
	@Resource
	private GzkmxSer gzkmxSer;

	@RequestMapping
	public String show(@RequestParam String detail,Model model) {
		String[] strs = detail.split(",");
		model.addAttribute("nd", strs[0]);
		model.addAttribute("yd", strs[1]);
		model.addAttribute("ksnm", strs[2]);
		model.addAttribute("ksmc",strs[3]);
		model.addAttribute("ygbh", strs[4]);
		model.addAttribute("ygxm", strs[5]);
		return "/qmys/gzlmx";
	}

	@RequestMapping(value = "/findByPage", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> find(int page, int rows,
			HttpServletRequest request) {
		String nd = request.getParameter("nd");
		String yd = request.getParameter("yd");
		String ksnm = request.getParameter("ksnm");
		String ygbh = request.getParameter("ygbh");
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtil.isNullAndSpace(ksnm)){
			map.put("ksnm", ksnm);
		}
		if(!StringUtil.isNullAndSpace(nd)){
			map.put("nd", nd);
		}
		if(!StringUtil.isNullAndSpace(yd)){
			map.put("yd", yd);
		}
		if(!StringUtil.isNullAndSpace(ygbh)){
			map.put("ygbh", ygbh);
		}
		
		Map<String, Object> map2 = gzkmxSer.find(page, rows, map);
		Map<String, Object> map3 = new HashMap<String, Object>();
		List<HPXT_GZLMX> list2 = gzkmxSer.selectSum(map);
		map3.put("bbmc", "合计");
		map3.put("bcdf",list2.get(0).getHj());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map3);
		map2.put("footer", list);
		return map2;
	}
}
