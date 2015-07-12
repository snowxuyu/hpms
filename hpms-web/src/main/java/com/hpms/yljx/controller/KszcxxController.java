package com.hpms.yljx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.yljx.service.KszcxxSer;
import com.hpms.yljx.vo.Combobox;

/**
 * 
 * 类名：KszcxxController <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月10日 <br>
 * 描述：科室注册信息控制器 <br>
 */
@Controller
@RequestMapping("/yljx/ks")
public class KszcxxController {

	@Autowired
	private KszcxxSer ser;
	
	@RequestMapping("/query")
	@ResponseBody
	public List<Combobox> query() {
		List<Combobox> list = ser.findKS();
			list.add(0, new Combobox("", "---请选择---"));
		return list;
	}
}
