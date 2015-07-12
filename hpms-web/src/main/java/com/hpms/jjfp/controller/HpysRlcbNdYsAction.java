package com.hpms.jjfp.controller;

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

@Controller
@RequestMapping("jjfp/HpysRlcbNdYsAction")
public class HpysRlcbNdYsAction {
	
	@RequestMapping
	public String showIndex(){
		return "jjfp/hpysRlcbndys";
	}

	@Autowired
	private HpysRlcbysSer hrSer;
//查询年度总预算和实际值
	@RequestMapping(value="/queryNdYs",method=RequestMethod.POST)
	@ResponseBody
	public Object queryNdYs(HttpServletRequest request,HttpServletResponse response){
		String nd = request.getParameter("nd");
		List<HPYS_YSZX> list = hrSer.selectNdYs(nd);
		return list;
	}
		
}
