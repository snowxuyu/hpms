package com.hpms.yljx.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.yljx.entity.HPCS_XMLBDZ;
import com.hpms.yljx.service.HxxmzdSer;
import com.hpms.yljx.service.XmlbdzSer;
import com.hpms.yljx.vo.Combobox;

/**
 * 
 * 类名：XmlbdzController <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月17日 <br>
 * 描述：核心项目类别控制器 <br>
 */
@Controller
@RequestMapping("/yljx/hxxmlb")
public class HxxmlbController {

	@Autowired
	private XmlbdzSer ser;
	
	@Autowired
	private HxxmzdSer hxxmzdSer;
	
	@RequestMapping
	public String show() {
		return "/yljx/hxxmlb";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> query(int page, int rows, @RequestParam(required=false) String xmlbmc) {
		return ser.findByXMLBMC(page, rows, xmlbmc);
	}
	
	@RequestMapping("/query/fj")
	@ResponseBody
	public List<Combobox> query() {
		List<Combobox> list = ser.findFJ();
		list.add(0, new Combobox("", "---请选择父级---"));
		return list;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(HPCS_XMLBDZ item) {
		try {
			if (ser.findById(item.getXmlbbm()) != null) {
				return "exist";
			}
			ser.add(item);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public String update(HPCS_XMLBDZ item) {
		try {
			HPCS_XMLBDZ bean = ser.findById(item.getXmlbbm());
			bean.setXmlbmc(item.getXmlbmc());
			//bean.setFjbm(item.getFjbm());
			bean.setBzxx(item.getBzxx());
			ser.update(bean);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	@RequestMapping("/remove")
	@ResponseBody
	public String remove(String xmlbbm) {
		try {
			//HPCS_XMLBDZ xmlbdz = ser.findById(xmlbbm);
			/*if (xmlbdz.getFjbm() == null) {
				if (ser.findByPID(xmlbbm).size() == 0) {
					if (hxxmzdSer.existHXLBBM(xmlbbm)) {
						return "exist";
					}
				} else {
					return "exist";
				}
			} else {
				if (hxxmzdSer.existXMLBBM(xmlbbm)) {
					return "exist";
				}
			}*/
			if (hxxmzdSer.existXMLBBM(xmlbbm)) {
				return "exist";
			}
			ser.removeById(xmlbbm);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
}
