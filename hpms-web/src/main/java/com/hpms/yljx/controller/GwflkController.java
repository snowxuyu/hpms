package com.hpms.yljx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.yljx.service.GwflkSer;
import com.hpms.yljx.vo.Combobox;

/**
 * 
 * 类名：GwflkController <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月10日 <br>
 * 描述：岗位分类库控制器 <br>
 */

@Controller
@RequestMapping("/yljx/gw")
public class GwflkController {

	@Autowired
	private GwflkSer ser;
	
	@RequestMapping("/query")
	@ResponseBody
	public List<Combobox> query() {
		List<Combobox> list = ser.findGW();
		list.add(0, new Combobox("", "请选择岗位"));
		return list;
	}
	
}
