package com.hpms.xtwh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.xtwh.entity.HPXT_CDMKZC;
import com.hpms.xtwh.service.CdmkzcSer;

@Controller
@RequestMapping("/xtwh/cdmkzc")
public class CdmkzcController {
	@Resource
	private CdmkzcSer cdmkzcser;
	@RequestMapping
	public String show() {
		return "/xtwh/cdmkzc";
	}
	@RequestMapping(value="/query", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query(int page, int rows, @RequestParam(required=false) String ks) {
		return cdmkzcser.findByPage(page, rows, null);
	}
	
	@RequestMapping("/show/{pg}")
	public String showPage(@PathVariable("pg") String pg, HttpSession session, Model model) {
		Object obj = session.getAttribute("menu");
		Map<String, List<HPXT_CDMKZC>> menu = null;
		if (obj == null) {
			menu = cdmkzcser.findToMap();
			session.setAttribute("menu", menu);
		} else {
			menu = (Map<String, List<HPXT_CDMKZC>>) obj;
		}
		List<HPXT_CDMKZC> list = menu.get("2");
		List<HPXT_CDMKZC> cdList = new ArrayList<HPXT_CDMKZC>();
		for (HPXT_CDMKZC bean : list) {
			if (pg.equals(bean.getFjbm())) {
				cdList.add(bean);
			}
		}
		model.addAttribute("cdList", cdList);
		model.addAttribute("pg", pg);
		return "/index2";
	}
	
	@RequestMapping(value="/menu/{jb}")
	@ResponseBody
	public List<HPXT_CDMKZC> query1(HttpSession session, @PathVariable String jb, @RequestParam(required=false) String p) {
		Object obj = session.getAttribute("menu");
		Map<String, List<HPXT_CDMKZC>> menu = null;
		if (obj == null) {
			menu = cdmkzcser.findToMap();
			session.setAttribute("menu", menu);
		} else {
			menu = (Map<String, List<HPXT_CDMKZC>>) obj;
		}
		
		if ("1".equals(jb)) {
			if (p == null) {
				return menu.get("1");
			} else {
				List<HPXT_CDMKZC> list = menu.get("2");
				List<HPXT_CDMKZC> cdList = new ArrayList<HPXT_CDMKZC>();
				for (HPXT_CDMKZC bean : list) {
					if (p.equals(bean.getFjbm())) {
						cdList.add(bean);
					}
				}
				return cdList;
			}
		}
		if ("2".equals(jb)) {
			if (p == null) {
				return null;
			} else {
				List<HPXT_CDMKZC> list = menu.get("3");
				List<HPXT_CDMKZC> cdList = new ArrayList<HPXT_CDMKZC>();
				for (HPXT_CDMKZC bean : list) {
					if (p.equals(bean.getFjbm())) {
						cdList.add(bean);
					}
				}
				return cdList;
			}
		}
		return null;
	}
	
}
