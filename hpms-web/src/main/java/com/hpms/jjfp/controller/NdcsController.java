package com.hpms.jjfp.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.jjfp.entity.BPE_JX001;
import com.hpms.jjfp.service.NdcsSer;

@Controller
@RequestMapping("/jjfp/ndcs")
public class NdcsController {
	@Resource
	private NdcsSer ndcsser;
	@RequestMapping
	public String show() {
		return "/jjfp/ndcs";
	}
	@RequestMapping(value="/query", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query(int page, int rows, @RequestParam(required=false) String nd) {
		return ndcsser.findByPage(page, rows, nd);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(BPE_JX001 item) {
		try {
			item.setJxbh(null);
			item.setCreator("admin");
			item.setStatus("1");
			ndcsser.add(item);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(BPE_JX001 item) {
		try {
			BPE_JX001 bpe = ndcsser.findById(item.getJxbh());
			bpe.setJxnd(item.getJxnd());
			bpe.setJxdj(item.getJxdj());
			ndcsser.update(bpe);
		} catch (Exception e) {	
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public String update(String jxbh) {
		System.out.println("jxbh"+jxbh);
		try {
			ndcsser.removeById(jxbh);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
}
