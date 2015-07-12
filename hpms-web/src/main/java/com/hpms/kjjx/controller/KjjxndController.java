package com.hpms.kjjx.controller;

import java.io.OutputStream;
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

import com.hpms.hpxt.service.ZdSer;
import com.hpms.kjjx.service.KjjxndSer;
import com.hpms.yljx.service.KszcxxSer;
import com.hpms.yljx.vo.Combobox;

@Controller
@RequestMapping("/kjjx/jxndAction")
public class KjjxndController {

	@Resource
	private KszcxxSer kszcxxSer;
	@Resource
	private KjjxndSer kjjxndSer;
	@Resource
	private ZdSer zdSer;

	@RequestMapping
	public String show() {
		return "/kjjx/kjjxnd";
	}

	@RequestMapping(value = "/findByPage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> find(int page, int rows,
			HttpServletRequest request) {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String ksdm = request.getParameter("ksdm");
		Map<String, Object> map = new HashMap<String, Object>();
		if ((!"".equals(startTime)) && startTime != null) {
			map.put("startTime", startTime);
		}
		if ((!"".equals(endTime)) && startTime != null) {
			map.put("endTime", endTime);
		}
		if ((!"".equals(ksdm)) && ksdm != null) {
			map.put("ksdm", ksdm);
		}
		return kjjxndSer.find(page, rows, map);
	}

	@RequestMapping(value = "/getKsList", method = RequestMethod.POST)
	@ResponseBody
	public List<Combobox> getKsList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flbm", "HPMS.1016");
		List<Combobox> list = zdSer.getList(map);
		list.add(0, new Combobox("", "请选择"));
		return list;
	}

	@RequestMapping(value = "/xmexpor", method = RequestMethod.GET)
	@ResponseBody
	public void xmexpor(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		OutputStream ouputStream = null;
		ouputStream = response.getOutputStream();
		response.reset();// 清空输出流
		String filename = "科教绩效科室年度汇总.xls";
		response.setHeader("Content-Type", "application/force-download");
		response.setHeader("Content-Type", "application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(filename.getBytes("gb2312"), "ISO8859-1"));// 设定输出文件头

		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String ksdm = request.getParameter("ksdm");
		Map<String, Object> map = new HashMap<String, Object>();
		if ((!"".equals(startTime)) && startTime != null) {
			map.put("startTime", startTime);
		}
		if ((!"".equals(endTime)) && startTime != null) {
			map.put("endTime", endTime);
		}
		if ((!"".equals(ksdm)) && ksdm != null) {
			map.put("ksdm", ksdm);
		}
		List<Object> data = kjjxndSer.exportList(map);
		kjjxndSer.exportList(map);
		HSSFWorkbook wb = kjjxndSer.export("科教绩效科室年度汇总",
				"科室内码,科室名称,科研得分,教学得分,总分,科研平均得分,教学平均得分,人均平均分", data);
		wb.write(ouputStream);
	}
}