package com.pbcs.pbgl.controller;

import java.io.OutputStream;
import java.util.ArrayList;
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

import com.pbcs.pbgl.service.KsZgrySer;
import com.pbcs.pbgl.vo.PBCS_ZGRYTJVO;
import com.rongda.framework.util.StringUtil;

@Controller
@RequestMapping("pbcs/pbgl/zgrytj")
public class ZgrytjController {

	@Resource
	private KsZgrySer ksZgrySer;

	@RequestMapping
	public String showIndex() {
		return "pbcs/pbgl/zgrytj";
	}

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> query(HttpServletRequest request, int page,
			int rows) {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!StringUtil.isNullAndSpace(startTime)) {
			map.put("startTime", startTime);
		}
		if (!StringUtil.isNullAndSpace(endTime)) {
			map.put("endTime", endTime);
		}
		List<PBCS_ZGRYTJVO> list1 = ksZgrySer.find(map);
		Map<String, Object> map1 = ksZgrySer.find(page, rows, map);
		Map<String, Object> map2 = new HashMap<String, Object>();
		if (list1.size() > 0) {
			map2.put("ksmc", "合计");
			map2.put("bzs", list1.get(0).getBzs());
			map2.put("zgrs", list1.get(0).getZgrs());
			List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
			list2.add(map2);
			map1.put("footer", list2);
		}

		return map1;
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

		List<Object> data = ksZgrySer.exportList(map);

		OutputStream ouputStream = null;
		ouputStream = response.getOutputStream();
		String filename = "科室在岗人员统计表.xls";
		response.setHeader("Content-Type", "application/force-download");
		response.setHeader("Content-Type", "application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(filename.getBytes("gb2312"), "ISO8859-1"));// 设定输出文件头
		HSSFWorkbook wb = ksZgrySer.export("科室在岗人员统计表",
				"科室内码,科室名称,编制数,在岗人数,月度,年度", data);
		wb.write(ouputStream);
	}
}
