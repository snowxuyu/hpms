package com.hpms.hpxt.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.hpxt.service.XcxmSer;

@Controller
@RequestMapping("/hpxt/xcxmAction")
public class XcxmcxController {
	@Resource
	private XcxmSer xcxmSer;

	@RequestMapping
	public String show() {
		return "/hpxt/xcxmcx";
	}

	@RequestMapping(value = "/findByPage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> find(int page, int rows,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String time = request.getParameter("time");
		String ymc = request.getParameter("ymc");
		String ydx = request.getParameter("ydx");
		
		if ((!"".equals(time)) && time != null) {
			map.put("time", time);
		}
		if ((!"".equals(ymc)) && ymc != null) {
			map.put("ymc", ymc);
		}
		if ((!"".equals(ydx)) && ydx != null) {
			map.put("ydx", ydx);
		}
		return xcxmSer.find(page, rows, map);
	}
}
