package com.hpms.kjjx.controller;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import com.hpms.kjjx.entity.HPKJ_YGXMMX;
import com.hpms.kjjx.service.KjjxrySer;
import com.hpms.kjjx.service.KjygxmmxSer;
import com.hpms.yljx.service.KszcxxSer;
import com.hpms.yljx.vo.Combobox;

@Controller
@RequestMapping("/kjjx/jxryAction")
public class KjjxryController {

	@Resource
	private KszcxxSer kszcxxSer;
	@Resource
	private KjjxrySer kjjxrySer;
	@Resource
	private KjygxmmxSer kjygxmmxSer;
	@Resource
	private ZdSer zdSer;

	@RequestMapping
	public String show() {
		return "/kjjx/kjjxry";
	}

	@RequestMapping(value = "/findByPage", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> find(int page, int rows,
			HttpServletRequest request) {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String ksdm = request.getParameter("ksdm");
		String zc = request.getParameter("zc");
		String ksmc = request.getParameter("ksmc");
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
		if ((!"".equals(zc)) && zc != null) {
			map.put("zc", zc);
		}
		if ((!"".equals(ksmc)) && ksmc != null) {
			map.put("ksmc", ksmc);
		}
		return kjjxrySer.find(page, rows, map);
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

	@RequestMapping(value = "/getZcList", method = RequestMethod.POST)
	@ResponseBody
	public List<Combobox> getZcList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flbm", "PMS.9002");//HPMS.1011
		List<Combobox> list = zdSer.getList(map);
		list.add(0, new Combobox("", "请选择"));
		return list;
	}

	@RequestMapping(value = "/getXmmx")
	@ResponseBody
	public List<HPKJ_YGXMMX> getMx(HttpServletRequest request)
			throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<String, Object>();
		String ygbh = request.getParameter("ygbh");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		//String ksdm = request.getParameter("ksdm");
		//String zc = request.getParameter("zc");
		if ((!"".equals(ygbh)) && ygbh != null) {
			map.put("ygbh", ygbh);
		}
		if ((!"".equals(startTime)) && startTime != null) {
			map.put("startTime", startTime);
		}
		if ((!"".equals(endTime)) && startTime != null) {
			map.put("endTime", endTime);
		}
		List<HPKJ_YGXMMX> list = kjygxmmxSer.findBybh(map);
		return list;
	}

	@RequestMapping(value = "/xmexpor", method = RequestMethod.GET)
	@ResponseBody
	public void xmexpor(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OutputStream ouputStream = null;
		ouputStream = response.getOutputStream();
		response.reset();// 清空输出流
		String filename = "科教绩效人员年度明细.xls";
		response.setHeader("Content-Type", "application/force-download");
		response.setHeader("Content-Type", "application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String(filename.getBytes("gb2312"), "ISO8859-1"));// 设定输出文件头
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String ksdm = request.getParameter("ksdm");
		String zc = request.getParameter("zc");
		String ksmc = request.getParameter("ksmc");
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
		if ((!"".equals(zc)) && zc != null) {
			map.put("zc", zc);
		}
		if ((!"".equals(ksmc)) && ksmc != null) {
			map.put("ksmc", ksmc);
		}
		List<Object> data = kjjxrySer.exportList(map);
		//HPKJ_KJJXRY h = new HPKJ_KJJXRY();
		HSSFWorkbook wb = kjjxrySer
				.export("科教绩效人员年度明细",
						"员工编号,员工姓名,职称,A科技成果,B发表论文,C职务专利,D科研项目,E三新项目,F学术兼职,G院内培训,H三基考试,I继续教育,J带教情况,K重点学科,L人才培养,合计",
						data);
		wb.write(ouputStream);
	}
}