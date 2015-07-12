package com.hpms.jjfp.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.jjfp.entity.HPYS_YSZX;
import com.hpms.jjfp.service.HpysRlcbysSer;
import com.rongda.framework.util.StringUtil;

@Controller
@RequestMapping("jjfp/HpysRlcbzxAction")
public class HpysRlcbzxAction {
	
	@RequestMapping
	public String showIndex(){
		return "jjfp/hpysRlcbzx";
	}

	@Autowired
	private HpysRlcbysSer hrSer;
//查询年度总预算和实际值
	@RequestMapping(value="/queryNdSum",method=RequestMethod.POST)
	@ResponseBody
	public Object queryNdSum(HttpServletRequest request,HttpServletResponse response){
		List<HPYS_YSZX> list2 = new ArrayList<HPYS_YSZX>();
		DecimalFormat df = new DecimalFormat("0.00");
		String nd = request.getParameter("nd");
		List<HPYS_YSZX> list = hrSer.selectNdSum(nd);
		for (HPYS_YSZX yx : list) {
			Double zzxl = yx.getSjzje()/yx.getYszje();
			yx.setZzxl(Double.parseDouble(df.format(zzxl)));
			list2.add(yx);
		}
		return list2;
	}
		
}
