package com.pbcs.pbgl.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pbcs.pbgl.service.ZybffSer;
import com.pbcs.pbgl.vo.PBCS_ZYBFFVO;
import com.rongda.framework.util.StringUtil;

@Controller
@RequestMapping("pbcs/zybff")
public class ZybffController {

	@Resource
	private ZybffSer ser;
	
	@RequestMapping
	public String showIndex(){
		return "pbcs/pbgl/zybffqktj";
	}
	
	@RequestMapping(value="/queryAll", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> queryAll(HttpServletRequest request,HttpServletResponse response,int page,int rows){
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String ksnm = request.getParameter("ksnm");
		Map<String,Object> hashMap = new HashMap<String,Object>();
		if(!StringUtil.isNullAndSpace(startTime)){
			hashMap.put("startTime", startTime);
		}
		if(!StringUtil.isNullAndSpace(endTime)){
			hashMap.put("endTime", endTime);
		}
		if(!StringUtil.isNullAndSpace(ksnm)){
			hashMap.put("ksnm", ksnm);
		}
		
		Map<String, Object> map = ser.find(page, rows, hashMap);
		Map<String, Object> map1 = new HashMap<String, Object>();
		List<PBCS_ZYBFFVO> list2 = ser.selectSum(hashMap);
		map1.put("ksmc", "合计");
		map1.put("zbs", list2.get(0).getZbhj());
		map1.put("ybs", list2.get(0).getYbhj());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map1);
		map.put("footer", list);
		return map;
	}
	
	@RequestMapping(value = "/zybfexpor", method = RequestMethod.GET)
	@ResponseBody
	public void zybfexpor(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String ksnm = request.getParameter("ksnm");
		Map<String, Object> map = new HashMap<String, Object>();
		if ((!"".equals(startTime)) && startTime != null) {
			map.put("startTime", startTime);
		}
		if ((!"".equals(endTime)) && startTime != null) {
			map.put("endTime", endTime);
		}
		if ((!"".equals(ksnm)) && ksnm != null) {
			map.put("ksnm", ksnm);
		}
		
		List<Object> data = ser.exportList(map);
		
		OutputStream ouputStream = null;
		ouputStream = response.getOutputStream();
		String filename = "中夜班发放情况统计表.xls";
		response.setHeader("Content-Type", "application/force-download");
		response.setHeader("Content-Type", "application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(filename.getBytes("gb2312"), "ISO8859-1"));// 设定输出文件头
		HSSFWorkbook wb = ser.export("中夜班发放情况统计表", "科室名称,姓名,中班数,夜班数,值班数,备班数,中夜班费小计"
				, data);
		wb.write(ouputStream);
	}
}
