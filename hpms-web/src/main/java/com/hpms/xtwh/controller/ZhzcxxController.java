package com.hpms.xtwh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.xtwh.entity.HPXT_ZHZCXX;
import com.hpms.xtwh.service.ZhzcxxSer;

@Controller
@RequestMapping("/xtwh/zhzcxx/")
public class ZhzcxxController {

	@Autowired
	private ZhzcxxSer ser;
	
	@ResponseBody
	@RequestMapping("/ck")
	public String ck(@RequestParam String username, @RequestParam String password, HttpSession session) {
		boolean b = ser.exist(username, password);
		if (b) {
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			HPXT_ZHZCXX zh = ser.findByZH(username, password).get(0);
			session.setAttribute("zhfz", zh.getZhfz());
			return "success";
		}
		return "fail";
	}
}
