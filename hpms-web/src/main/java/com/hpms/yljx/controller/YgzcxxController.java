package com.hpms.yljx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.yljx.entity.HPXT_YGZCXX;
import com.hpms.yljx.service.YgzcxxSer;

/**
 * 
 * 类名：YgzcxxController <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月15日 <br>
 * 描述：员工注册信息控制器 <br>
 */
@Controller
@RequestMapping("/yljx/yg")
public class YgzcxxController {
	
	@Autowired
	private YgzcxxSer ser;

	@RequestMapping
	public String show() {
		return "/yljx/yg";
	}
	
	/**
	 * 根据员工编号或者员工姓名进行模糊检索
	 * @param q
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public List<HPXT_YGZCXX> query(String q) {
		return ser.findByQ(q);
	}
}
