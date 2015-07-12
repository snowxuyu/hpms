package com.hpms.kjjx.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.kjjx.service.KjkmxzSer;
import com.rongda.framework.util.StringUtil;

@Controller
@RequestMapping("kjjx/kjkmxz")
public class KjkmxzController {

	@Autowired
	private KjkmxzSer ser;
	
	@RequestMapping
	public String showIndex(){
		return "kjjx/kjkmxz";
	}
	
	@RequestMapping(value="/queryAll", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryAll(HttpServletRequest request,HttpServletResponse response,int page,int rows){
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String kslb = request.getParameter("kslb");
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		if(!StringUtil.isNullAndSpace(startTime)){
			hashMap.put("startTime", startTime);
		}
		if(!StringUtil.isNullAndSpace(endTime)){
			hashMap.put("endTime", endTime);
		}
		if(!StringUtil.isNullAndSpace(kslb)){
			hashMap.put("kslb", kslb);
		}
		
		Map<String, Object> map = ser.selectAll(page, rows, hashMap);
		return map;
	}
	
	@RequestMapping(value = "/ksmxexpor", method = RequestMethod.GET)
	@ResponseBody
	public void ksmxexpor(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String kslb = request.getParameter("kslb");
		Map<String, Object> map = new HashMap<String, Object>();
		if ((!"".equals(startTime)) && startTime != null) {
			map.put("startTime", startTime);
		}
		if ((!"".equals(endTime)) && startTime != null) {
			map.put("endTime", endTime);
		}
		if ((!"".equals(kslb)) && kslb != null) {
			map.put("kslb", kslb);
		}
		
		List<Object> data = ser.exportList(map);
		
		OutputStream ouputStream = null;
		ouputStream = response.getOutputStream();
		String filename = "科教按科室明细表.xls";
		response.setHeader("Content-Type", "application/force-download");
		response.setHeader("Content-Type", "application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(filename.getBytes("gb2312"), "ISO8859-1"));// 设定输出文件头
		HSSFWorkbook wb = ser.export("科教按科室明细表", "科室名称,A科技成果,B发表论文,C职务专利,D科研项目,E三新项目,F学术兼职,G院内培训,H三基考试,I继续教育,J带教情况,K重点学科,L人才培养,合计,科研平均分,教育平均分"
				, data);
		wb.write(ouputStream);
	}
}
