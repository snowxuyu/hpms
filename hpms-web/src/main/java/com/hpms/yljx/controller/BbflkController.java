package com.hpms.yljx.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.yljx.entity.HPCS_BBFLK;
import com.hpms.yljx.service.BbflkSer;
import com.hpms.yljx.vo.Combobox;

/**
 * @author 高国祥 
 *
 * 创建时间：2015年1月11日-下午3:11:55
 *
 * 类名： 班别分类库控制器
 *
 * 描述:
 *
 */
@Controller
@RequestMapping("/yljx/bbfl")
public class BbflkController {

	@Resource
	private BbflkSer bbflkSer;
	//查询班别
		@RequestMapping(value="/query")
		@ResponseBody
		public List<Combobox> queryBb(){
			 List<Combobox> list = bbflkSer.listAllBb();
			 list.add(0, new Combobox("", "---请选择---"));
			 return list;
		}
		
		@RequestMapping(value="/queryAll")
		@ResponseBody
		public List<HPCS_BBFLK> queryAll(){
			 List<HPCS_BBFLK> list = bbflkSer.findAll();
			 return list;
		}
		
}
