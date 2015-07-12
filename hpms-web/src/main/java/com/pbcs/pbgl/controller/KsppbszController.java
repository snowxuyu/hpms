package com.pbcs.pbgl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.qmys.vo.TempVO;
import com.hpms.yljx.entity.HPCS_BBFLK;
import com.pbcs.pbgl.service.KsppszSer;

@Controller
@RequestMapping("pbcs/pbgl/ppbsz")
public class KsppbszController {

	@Resource
	private KsppszSer ksppszSer;

	@RequestMapping
	public String showIndex() {
		return "pbcs/pbgl/ppbsz";
	}

	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String query(HttpServletRequest request) {
		String rq = request.getParameter("rq");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rq", rq);
		List<TempVO> list = ksppszSer.getTitle(map);
		String str = "";
		str = list.get(0).getP1();
		return str;
	}
	
	@RequestMapping(value = "/queryAll", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryAll(HttpServletRequest request, int page,
			int rows) {
		String ksnm = request.getParameter("ksnm");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ksnm", ksnm);
		Map<String, Object> kMap = ksppszSer.find(page, rows, map);
		return kMap;
	}
	
	@RequestMapping(value = "/queryBB", method = RequestMethod.POST)
	@ResponseBody
	public List<HPCS_BBFLK> queryBB(HttpServletRequest request) {
		String bbmc = request.getParameter("bbmc");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bbmc", bbmc);
		List<HPCS_BBFLK> list = ksppszSer.queryBB(map);
		return list;
	}
}
