package com.pbcs.pbgl.controller;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
//import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pbcs.pbgl.service.KsqqSer;
import com.rongda.framework.util.StringUtil;

@Controller
@RequestMapping("pbcs/pbgl/ksqqtj")
public class KsqqtjController {

	@Resource
	private KsqqSer ksqqSer;

	@RequestMapping
	public String showIndex() {
		return "pbcs/pbgl/ksqqtj";
	}

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query(HttpServletRequest request, int page,
			int rows) {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String ksnm = request.getParameter("ksnm");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isNullAndSpace(startTime)) {
			map.put("startTime", startTime);
		}
		if (!StringUtil.isNullAndSpace(endTime)) {
			map.put("endTime", endTime);
		}
		if ((!"".equals(ksnm)) && ksnm != null) {
			map.put("ksnm", ksnm);
		}
		return ksqqSer.find(page, rows, map);
	}

	@RequestMapping(value = "/exporExcel", method = RequestMethod.GET)
	@ResponseBody
	public void exporExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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

		List<Object> data = ksqqSer.exportList(map);

		OutputStream ouputStream = null;
		ouputStream = response.getOutputStream();
		String filename = "科室缺勤统计表.xls";
		response.setHeader("Content-Type", "application/force-download");
		response.setHeader("Content-Type", "application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(filename.getBytes("gb2312"), "ISO8859-1"));// 设定输出文件头
		HSSFWorkbook wb = ksqqSer.export("科室缺勤统计表",
				"科室内码,科室名称,姓名,在岗天数,进修挂职,病假,事假,产假,婚假,探亲假,丧假,公假", data);
		wb.write(ouputStream);
	}
}
