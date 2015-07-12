package com.hpms.qmys.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.jjfp.service.GrzxjcSer;
import com.hpms.jjfp.service.KszxjcSer;
import com.hpms.qmys.service.BhgzlSer;
import com.hpms.qmys.vo.BhgzlCondition;
import com.hpms.qmys.vo.BhgzlFPVO;
import com.hpms.qmys.vo.BhgzlLBVO;
import com.hpms.qmys.vo.BhgzlNYVO;
import com.hpms.qmys.vo.BhgzlTJVO;
import com.hpms.qmys.vo.GRJJVO;
import com.hpms.qmys.vo.TempVO;
import com.hpms.util.UtilStr;
import com.hpms.xtwh.service.ZhzcxxSer;
import com.hpms.yljx.entity.HPXT_YGZCXX;

/**
 * 
 * 类名：BhgzlController <br>
 * 作者：吴陶君 <br>
 * 日期：2015年2月5日 <br>
 * 描述：标化工作量控制器 <br>
 */
@Controller
@RequestMapping("/qmys/cx")
public class BhgzlController {

	@Autowired
	private BhgzlSer ser;
	private HSSFWorkbook wb;
	@Autowired
	private KszxjcSer kszxjcSer;
	@Autowired
	private GrzxjcSer grzxjcSer;
	@Autowired
	private ZhzcxxSer zhzcxxSer;
	
	
	Map<String, Object> _map = new HashMap<String, Object>();
	
	/**
	 * 根据年月查询，请求视图
	 * @param detail
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ny", method=RequestMethod.GET)
	public String queryNY(String detail,Model model) {
		String[] strs = detail.split(",");
		model.addAttribute("nd", strs[0]);
		model.addAttribute("yd", strs[1]);
		return "/qmys/bhgzlNY";
	}
	
	/**
	 * 根据年月查询
	 * @param page
	 * @param rows
	 * @param nd
	 * @param yd
	 * @return
	 */
	@RequestMapping(value="/ny", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryByNY(HttpSession session,@RequestParam("page") int page, @RequestParam("rows") int rows, String nd, String yd, @RequestParam(required=false) String q) {
		BhgzlCondition condition = new BhgzlCondition();
		condition.setNd(nd);
		condition.setYd(yd);
		Map<String, Object> map = null;
		if (q != null && "1".equals(q)) {
			Object zhfz = session.getAttribute("zhfz");
			if ("B01".equals(zhfz)) {
				HPXT_YGZCXX ygzcxx = zhzcxxSer.findByZHNM((String) session.getAttribute("username"));
				condition.setKsnm(ygzcxx.getKsnm());
				map = ser.findByNY(page, rows, condition.getMap());
			} else if ("C01".equals(zhfz)) {
				map = ser.findByNY(page, rows, condition.getMap());
				BhgzlTJVO bean = ser.findByNY(condition.getMap());
				HashMap<String, Object> footMap = new HashMap<String, Object>();
				ArrayList<Object> list = new ArrayList<Object>();
				footMap.put("kslbmc", bean.getName());
				footMap.put("hj", bean.getP1());
				footMap.put("fpjj", bean.getP2());
				list.add(footMap);
				map.put("footer", list);
			}
		} else {
			map = ser.findByNY(page, rows, condition.getMap());
			BhgzlTJVO bean = ser.findByNY(condition.getMap());
			HashMap<String, Object> footMap = new HashMap<String, Object>();
			ArrayList<Object> list = new ArrayList<Object>();
			footMap.put("kslbmc", bean.getName());
			footMap.put("hj", bean.getP1());
			footMap.put("fpjj", bean.getP2());
			list.add(footMap);
			map.put("footer", list);
		}
		return map;
		
	}
	
	/**
	 * 根据分配原则查询，请求视图
	 * @param detail
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ny/fp", method=RequestMethod.GET)
	public String queryFP(String detail,Model model) {
		String[] strs = detail.split(",");
		model.addAttribute("nd", strs[0]);
		model.addAttribute("yd", strs[1]);
		model.addAttribute("ksbh", strs[4].equals("null")?"":strs[2]);
		model.addAttribute("fpyz", strs[3]);
		model.addAttribute("ksmc", strs[4].equals("null")?"":strs[4]);
		return "/qmys/bhgzlFP";
	}
	
	/**
	 * 根据分配原则查询
	 * @param page
	 * @param rows
	 * @param nd
	 * @param yd
	 * @param ksbh
	 * @param fpyz
	 * @return
	 */
	@RequestMapping(value="/ny/fp", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryByFP(@RequestParam("page") int page, @RequestParam("rows") int rows, String nd, String yd, String ksbh, String fpyz) {
		BhgzlCondition condition = new BhgzlCondition();
		condition.setNd(nd);
		condition.setYd(yd);
		condition.setKsnm(ksbh);
		condition.setFpyz("0".equals(fpyz)?null:fpyz);
		HashMap<String, Object> _map = new HashMap<String, Object>();
		_map.put("nd1", nd);
		_map.put("yd1", yd);
		_map.put("ksnm1", ksbh);
		_map.put("fpyz1", "0".equals(fpyz)?null:fpyz);
		condition.getMap().putAll(condition.getMap());
		_map.putAll(condition.getMap());
		Map<String, Object> map = ser.findByFP(page, rows, _map);
		HashMap<String, Object> footMap = new HashMap<String, Object>();
		ArrayList<Object> list = new ArrayList<Object>();
		BhgzlTJVO bean = ser.findByFP(_map);
		footMap.put("xmlbdm", bean.getName());
		footMap.put("sl", bean.getP1());
		footMap.put("bhgzl", bean.getP2());
		list.add(footMap);
		map.put("footer", list);
		return map;
	}
	
	/**
	 * 根据项目类别查询，请求视图
	 * @param detail
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ny/fp/lb", method=RequestMethod.GET)
	public String queryLB(String detail,Model model) {
		String[] strs = detail.split(",");
		model.addAttribute("nd", strs[0]);
		model.addAttribute("yd", strs[1]);
		model.addAttribute("ksbh", strs[4].equals("null")?"":strs[2]);
		model.addAttribute("fpyz", strs[3]);
		model.addAttribute("ksmc", strs[4].equals("null")?"":strs[4]);
		model.addAttribute("xmlbdm", strs[6].equals("null")?"":strs[5]);
		model.addAttribute("xmlbmc", strs[6].equals("null")?"":strs[6]);
		return "/qmys/bhgzlLB";
	}
	
	/**
	 * 根据项目类别查询
	 * @param page
	 * @param rows
	 * @param nd
	 * @param yd
	 * @param ksbh
	 * @param fpyz
	 * @param xmlbdm
	 * @return
	 */
	@RequestMapping(value="/ny/fp/lb", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryByLB(@RequestParam("page") int page, @RequestParam("rows") int rows, String nd, String yd, String ksbh, String fpyz, String xmlbdm) {
		BhgzlCondition condition = new BhgzlCondition();
		condition.setNd(nd);
		condition.setYd(yd);
		condition.setKsnm(ksbh);
		condition.setFpyz("0".equals(fpyz)?null:fpyz);
		condition.setXmlbdm(xmlbdm);
		Map<String, Object> map = ser.findByLB(page, rows, condition.getMap());
		HashMap<String, Object> footMap = new HashMap<String, Object>();
		ArrayList<Object> list = new ArrayList<Object>();
		BhgzlTJVO bean = ser.findByLB(condition.getMap());
		footMap.put("xmdm", bean.getName());
		footMap.put("sl", bean.getP1());
		footMap.put("bhgzl", bean.getP2());
		list.add(footMap);
		map.put("footer", list);
		return map;
	}
	
	/**
	 * 根据项目类别和员工编号查询，请求视图
	 * @param detail
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ny/fp/lbxm", method=RequestMethod.GET)
	public String queryLBXM(String detail,Model model) {
		String[] strs = detail.split(",");
		model.addAttribute("nd", strs[0]);
		model.addAttribute("yd", strs[1]);
		model.addAttribute("ksbh", strs[4].equals("null")?"":strs[2]);
		model.addAttribute("fpyz", strs[3]);
		model.addAttribute("ksmc", strs[4].equals("null")?"":strs[4]);
		model.addAttribute("xmlbdm", strs[6].equals("null")?"":strs[5]);
		model.addAttribute("xmlbmc", strs[6].equals("null")?"":strs[6]);
		model.addAttribute("ygbh", strs[7]);
		model.addAttribute("ygxm", strs[8]);
		return "/qmys/bhgzlLBXM";
	}
	
	/**
	 * 根据项目类别和员工编号查询
	 * @param page
	 * @param rows
	 * @param nd
	 * @param yd
	 * @param ksbh
	 * @param fpyz
	 * @param xmlbdm
	 * @return
	 */
	@RequestMapping(value="/ny/fp/lbxm", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryByLBXM(@RequestParam("page") int page, @RequestParam("rows") int rows, String nd, String yd, String ksbh, String fpyz, String xmlbdm, String ygbh) {
		BhgzlCondition condition = new BhgzlCondition();
		condition.setYgbh(ygbh);
		condition.setNd(nd);
		condition.setYd(yd);
		condition.setKsnm(ksbh);
		condition.setFpyz("0".equals(fpyz)?null:fpyz);
		condition.setXmlbdm(xmlbdm);
		Map<String, Object> map = ser.findByLBXM(page, rows, condition.getMap());
		HashMap<String, Object> footMap = new HashMap<String, Object>();
		ArrayList<Object> list = new ArrayList<Object>();
		BhgzlTJVO bean = ser.findByLBXM(condition.getMap());
		footMap.put("xmdm", bean.getName());
		footMap.put("sl", bean.getP1());
		footMap.put("bhgzl", bean.getP2());
		list.add(footMap);
		map.put("footer", list);
		return map;
	}
	
	/**
	 * 根据项目类别查询，请求视图
	 * @param detail
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ny/fp/lb/grbhgzl", method=RequestMethod.GET)
	public String queryByGrgzl(String detail,Model model) {
		String[] strs = detail.split(",");
		model.addAttribute("nd", strs[0]);
		model.addAttribute("yd", strs[1]);
		model.addAttribute("ksbh", strs[2].equals("null")?"":strs[2]);
		model.addAttribute("ksmc", strs[2].equals("null")?"":strs[3]);
		model.addAttribute("xmlbdm", strs[4].equals("null")?"":strs[4]);
		model.addAttribute("xmlbmc", strs[4].equals("null")?"":strs[5]);
		return "/qmys/bhgzlLBGr";
	}
	
	/**
	 * 根据项目类别查询
	 * @param page
	 * @param rows
	 * @param nd
	 * @param yd
	 * @param ksbh
	 * @param fpyz
	 * @param xmlbdm
	 * @return
	 */
	@RequestMapping(value="/ny/fp/lb/grbhgzl", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryByGrgzl(@RequestParam("page") int page, @RequestParam("rows") int rows, String nd, String yd, String ksbh, String xmlbdm) {
		HashMap<String, Object> footMap = new HashMap<String, Object>();
		ArrayList<Object> list = new ArrayList<Object>();
		BhgzlCondition condition = new BhgzlCondition();
		condition.setNd(nd);
		condition.setYd(yd);
		condition.setKsnm(ksbh);
		condition.setXmlbdm(xmlbdm);
		Map<String, Object> map = ser.findByLBGr(page, rows, condition.getMap());
		
		BhgzlTJVO bean = ser.findByLBKsGr(condition.getMap());
		footMap.put("ygxm", bean.getName());
		footMap.put("grbhgz", bean.getP1());
		list.add(footMap);
		map.put("footer", list);
		return map;
	}
	
	/**
	 * 按科室无项目类别查询个人标化工作量，请求视图
	 * @param page
	 * @param rows
	 * @param nd
	 * @param yd
	 * @param ksbh
	 * @param ygbh
	 * @return
	 */
	@RequestMapping(value="/ny/fp/lb/ksgrbhgzl", method=RequestMethod.GET)
	public String queryByKsGrgzl(String detail,Model model) {
		String[] strs = detail.split(",");
		model.addAttribute("nd", strs[0]);
		model.addAttribute("yd", strs[1]);
		model.addAttribute("ksbh", strs[2].equals("null")?"":strs[2]);
		model.addAttribute("ksmc", strs[2].equals("null")?"":strs[3]);
		model.addAttribute("ygbh", strs[4]);
		model.addAttribute("ygxm", strs[5]);
		return "/qmys/bhgzlLBKsGr";
	}
	
	/**
	 * 按科室无项目类别查询个人标化工作量
	 * @param page
	 * @param rows
	 * @param nd
	 * @param yd
	 * @param ksbh
	 * @param ygbh
	 * @return
	 */
	@RequestMapping(value="/ny/fp/lb/ksgrbhgzl", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> queryByKsGrgzl(@RequestParam("page") int page, @RequestParam("rows") int rows, String nd, String yd, String ksbh, String ygbh) {
		HashMap<String, Object> footMap = new HashMap<String, Object>();
		ArrayList<Object> list = new ArrayList<Object>();
		BhgzlCondition condition = new BhgzlCondition();
		condition.setNd(nd);
		condition.setYd(yd);
		condition.setKsnm(ksbh);
		condition.setYgbh(ygbh);
		Map<String, Object> map = ser.findByLBKsGr(page, rows, condition.getMap());
		
		BhgzlTJVO bean = ser.findByLBKsGr(condition.getMap());
		footMap.put("xmlbdm", bean.getName());
		footMap.put("sl", bean.getP1());
		footMap.put("bhgzl", bean.getP2());
		list.add(footMap);
		map.put("footer", list);
		return map;
	}
	
	/**
	 * 根据科室及年月范围查询收入
	 * @param detail
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ny/sr", method=RequestMethod.GET)
	public String querySr(String detail,Model model) {
		return "/qmys/bhgzlSR";
	}
	
	/**
	 * 根据科室及年月范围查询收入
	 * @param page
	 * @param rows
	 * @param ny1
	 * @param ny2
	 * @param ksnm
	 * @return
	 */
	@RequestMapping(value="/ny/sr", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> querySr(@RequestParam("page") int page, @RequestParam("rows") int rows, 
			@RequestParam(required=false) String ny1, @RequestParam(required=false) String ny2, @RequestParam(required=false) String ksnm) {
		_map = new HashMap<String,Object>();
		if (UtilStr.isNotNull(ny1)) {
			String year1 = ny1.substring(0, 4);
			String month1 = ny1.substring(ny1.indexOf("-")+1);
			if (UtilStr.isNotNull(year1)) {
				_map.put("nds1", year1);
			}
			if (UtilStr.isNotNull(year1)) {
				_map.put("nds2", year1);
			}
			if (UtilStr.isNotNull(month1)) {
				_map.put("yds1", month1);
			}
		}
		if (UtilStr.isNotNull(ny2)) {
			String year2 = ny2.substring(0, 4);
			String month2 = ny2.substring(ny2.indexOf("-")+1);
			if (UtilStr.isNotNull(year2)) {
				_map.put("nde1", year2);
			}
			
			if (UtilStr.isNotNull(year2)) {
				_map.put("nde2", year2);
			}
			if (UtilStr.isNotNull(month2)) {
				_map.put("yde1", month2);
			}
		} else {
			Calendar calendar = Calendar.getInstance();
			
			_map.put("nde1", calendar.get(Calendar.YEAR));
			_map.put("nde2", calendar.get(Calendar.YEAR));
			_map.put("yde1", calendar.get(Calendar.MONTH)+1);
		}
		if (UtilStr.isNotNull(ksnm)) {
			_map.put("ksnm", ksnm);
		}
		
		Map<String, Object> map = ser.findSR(page, rows, _map);
		return map;
	}
	
	@RequestMapping(value="/zx/ks",method=RequestMethod.GET)
	public String kszx(String detail,Model model){
		String[] strs = detail.split(",");
		model.addAttribute("nd", strs[0]);
		model.addAttribute("yd", strs[1]);
		model.addAttribute("ksnm", strs[3].equals("null")?"":strs[2]);
		model.addAttribute("ksmc", strs[3].equals("null")?"":strs[3]);
		return "/qmys/kszx";
	}
	
	@RequestMapping(value="/zx/ks",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> kszx(@RequestParam("page") int page, @RequestParam("rows") int rows, String nd, String yd, String ksnm){
		Map<String,Object> map = new HashMap<String, Object>();
		if (!"".equals(nd) && null!=nd) {
			map.put("nd", nd);
		}
		if (!"".equals(yd) && null!=yd) {
			map.put("yd", yd);
		}
		if (!"".equals(ksnm) && null!=ksnm) {
			map.put("ksnm", ksnm);
		}
		return kszxjcSer.find(page, rows, map);
	}
	
	@RequestMapping(value="/zx/gr",method=RequestMethod.GET)
	public String grzx(String detail,Model model){
		String[] strs = detail.split(",");
		model.addAttribute("nd", strs[0]);
		model.addAttribute("yd", strs[1]);
		model.addAttribute("ksnm", strs[3].equals("null")?"":strs[2]);
		model.addAttribute("ksmc", strs[3].equals("null")?"":strs[3]);
		model.addAttribute("ygbh", strs[4].equals("null")?"":strs[4]);
		return "/qmys/grzx";
	}
	
	@RequestMapping(value="/zx/gr",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> grzx(@RequestParam("page") int page, @RequestParam("rows") int rows, String nd, String yd, String ksnm,String ygbh){
		Map<String,Object> map = new HashMap<String, Object>();
		if (!"".equals(nd) && null!=nd) {
			map.put("nd", nd);
		}
		if (!"".equals(yd) && null!=yd) {
			map.put("yd", yd);
		}
		if (!"".equals(ksnm) && null!=ksnm) {
			map.put("ksnm", ksnm);
		}
		if (!"".equals(ygbh) && null!=ygbh) {
			map.put("ygbh", ygbh);
		}
		return grzxjcSer.find(page, rows, map);
	}
	
	@RequestMapping(value="/ny/sr/exportExcel")
	@ResponseBody
	public void querySr(HttpServletRequest request,HttpServletResponse response) {
		
		wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("标化工作量及收入表");
		sheet.setDefaultColumnWidth(20);
		
		HSSFRow row = sheet.createRow((short)0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		HSSFCell cell0 = row.createCell(0);
		CellStyle newCellStyle = wb.createCellStyle();
		newCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //垂直居中  
        newCellStyle.setAlignment(CellStyle.ALIGN_CENTER); //水平居中
        cell0.setCellStyle(newCellStyle);
		cell0.setCellValue("标化工作量及收入表");
		
		row = sheet.createRow((short)1);
		//创建列头
		String[] str = new String[]{"科室名称","项目代码","项目名称","价格","RBRVS","数量 "};
		for (int i = 0; i < str.length; i++) {
			row.createCell(i).setCellValue(str[i]);
		}
		List<TempVO> list = ser.findSR(_map);
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((short)i+2);
			row.createCell(0).setCellValue(list.get(i).getP2());
			row.createCell(1).setCellValue(list.get(i).getP3());
			row.createCell(2).setCellValue(list.get(i).getP4());
			row.createCell(3).setCellValue(list.get(i).getP5());
			row.createCell(4).setCellValue(list.get(i).getP6());
			row.createCell(5).setCellValue(list.get(i).getP7());
		}
		OutputStream ouputStream = null;
		try {
			ouputStream = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + UtilStr.encodeFileName(request, "标化工作量及收入表.xls"));
			wb.write(ouputStream);
		} catch (Exception e) {
		} finally {
			if (ouputStream != null) {
				try {
					ouputStream.flush();
					ouputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@RequestMapping(value="/ny/exportExcel")
	@ResponseBody
	public void queryAllByNY(HttpSession session, String nd, String yd, HttpServletRequest request,HttpServletResponse response) {
		BhgzlCondition condition = new BhgzlCondition();
		condition.setNd(nd);
		condition.setYd(yd);
		
		Object zhfz = session.getAttribute("zhfz");
		if ("B01".equals(zhfz)) {
			HPXT_YGZCXX ygzcxx = zhzcxxSer.findByZHNM((String) session.getAttribute("username"));
			condition.setKsnm(ygzcxx.getKsnm());
		}
		
		wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(nd+"年"+yd+"月安亭医院标化工作量汇总表");
		sheet.setDefaultColumnWidth(20);
		
		HSSFRow row = sheet.createRow((short)0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
		HSSFCell cell0 = row.createCell(0);
		CellStyle newCellStyle = wb.createCellStyle();
		newCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //垂直居中  
        newCellStyle.setAlignment(CellStyle.ALIGN_CENTER); //水平居中
        cell0.setCellStyle(newCellStyle);
		cell0.setCellValue(nd+"年"+yd+"月安亭医院标化工作量汇总表");
		
		row = sheet.createRow((short)1);
		//创建列头
		String[] str = new String[]{"科室类别","一级科室","二级科室","奖金","工作总量"};
		for (int i = 0; i < str.length; i++) {
			row.createCell(i).setCellValue(str[i]);
		}
		List<BhgzlNYVO> list = ser.findAllByNY(condition.getMap());
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((short)i+2);
			row.createCell(0).setCellValue(list.get(i).getKslbmc());
			row.createCell(1).setCellValue(list.get(i).getYjks());
			row.createCell(2).setCellValue(list.get(i).getEjks());
			row.createCell(3).setCellValue(list.get(i).getFpjj());
			row.createCell(4).setCellValue(list.get(i).getHj());
		}
		OutputStream ouputStream = null;
		try {
			ouputStream = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + UtilStr.encodeFileName(request, nd+"年"+yd+"月安亭医院标化工作量汇总表.xls"));
			wb.write(ouputStream);
		} catch (Exception e) {
		} finally {
			if (ouputStream != null) {
				try {
					ouputStream.flush();
					ouputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@RequestMapping(value="/ny/fp/exportExcel")
	@ResponseBody
	public void queryAllByFP(String nd, String yd, String ksmc, String ksbh, String fpyz, HttpServletRequest request,HttpServletResponse response) {
		BhgzlCondition condition = new BhgzlCondition();
		condition.setNd(nd);
		condition.setYd(yd);
		condition.setKsnm(ksbh);
		condition.setFpyz("0".equals(fpyz)?null:fpyz);
		
		wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(nd+"年"+yd+"月安亭医院"+ksmc+"标化工作量汇总表");
		sheet.setDefaultColumnWidth(20);
		
		HSSFRow row = sheet.createRow((short)0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
		HSSFCell cell0 = row.createCell(0);
		CellStyle newCellStyle = wb.createCellStyle();
		newCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //垂直居中  
        newCellStyle.setAlignment(CellStyle.ALIGN_CENTER); //水平居中
        cell0.setCellStyle(newCellStyle);
		cell0.setCellValue(nd+"年"+yd+"月安亭医院"+ksmc+"标化工作量汇总表");
		
		row = sheet.createRow((short)1);
		//创建列头
		String[] str = new String[]{"项目类别代码","项目类别名称","数量","标化工作量","占比（%）"};
		for (int i = 0; i < str.length; i++) {
			row.createCell(i).setCellValue(str[i]);
		}
		List<BhgzlFPVO> list = ser.findAllByFP(condition.getMap());
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((short)i+2);
			row.createCell(0).setCellValue(list.get(i).getXmlbdm());
			row.createCell(1).setCellValue(list.get(i).getXmlbmc());
			row.createCell(2).setCellValue(list.get(i).getSl());
			row.createCell(3).setCellValue(list.get(i).getBhgzl());
			row.createCell(4).setCellValue(list.get(i).getZb());
		}
		OutputStream ouputStream = null;
		try {
			ouputStream = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + UtilStr.encodeFileName(request, nd+"年"+yd+"月安亭医院"+ksmc+"标化工作量汇总表.xls"));
			wb.write(ouputStream);
		} catch (Exception e) {
		} finally {
			if (ouputStream != null) {
				try {
					ouputStream.flush();
					ouputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@RequestMapping(value="/ny/fp/lb/exportExcel")
	@ResponseBody
	public void queryAllByLB(String nd, String yd, String ksbh, String fpyz, String xmlbdm, String xmlbmc, HttpServletRequest request,HttpServletResponse response) {
		BhgzlCondition condition = new BhgzlCondition();
		condition.setNd(nd);
		condition.setYd(yd);
		condition.setKsnm(ksbh);
		condition.setFpyz("0".equals(fpyz)?null:fpyz);
		condition.setXmlbdm(xmlbdm);
		
		wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(nd+"年"+yd+"月安亭医院"+xmlbmc+"标化工作量明细");
		sheet.setDefaultColumnWidth(20);
		
		HSSFRow row = sheet.createRow((short)0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
		HSSFCell cell0 = row.createCell(0);
		CellStyle newCellStyle = wb.createCellStyle();
		newCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //垂直居中  
        newCellStyle.setAlignment(CellStyle.ALIGN_CENTER); //水平居中
        cell0.setCellStyle(newCellStyle);
		cell0.setCellValue(nd+"年"+yd+"月安亭医院"+xmlbmc+"标化工作量明细");
		
		row = sheet.createRow((short)1);
		//创建列头
		String[] str = new String[]{"项目代码","项目名称","RBRVS点数","数量","标化工作量"};
		for (int i = 0; i < str.length; i++) {
			row.createCell(i).setCellValue(str[i]);
		}
		List<BhgzlLBVO> list = ser.findAllByLB(condition.getMap());
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((short)i+2);
			row.createCell(0).setCellValue(list.get(i).getXmdm());
			row.createCell(1).setCellValue(list.get(i).getXmmc());
			row.createCell(2).setCellValue(list.get(i).getRbrvs());
			row.createCell(3).setCellValue(list.get(i).getSl());
			row.createCell(4).setCellValue(list.get(i).getBhgzl());
		}
		OutputStream ouputStream = null;
		try {
			ouputStream = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + UtilStr.encodeFileName(request, nd+"年"+yd+"月安亭医院"+xmlbmc+"标化工作量明细.xls"));
			wb.write(ouputStream);
		} catch (Exception e) {
		} finally {
			if (ouputStream != null) {
				try {
					ouputStream.flush();
					ouputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@RequestMapping(value="/ny/fp/lb/gr/exportExcel")
	@ResponseBody
	public void queryAllByLB(String nd, String yd, String ksbh, String xmlbdm, String xmlbmc, HttpServletRequest request,HttpServletResponse response) {
		BhgzlCondition condition = new BhgzlCondition();
		condition.setNd(nd);
		condition.setYd(yd);
		condition.setKsnm(ksbh);
		condition.setXmlbdm(xmlbdm);
		
		wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(nd+"年"+yd+"月安亭医院"+xmlbmc+"个人标化工作量明细");
		sheet.setDefaultColumnWidth(20);
		
		HSSFRow row = sheet.createRow((short)0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
		HSSFCell cell0 = row.createCell(0);
		CellStyle newCellStyle = wb.createCellStyle();
		newCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //垂直居中  
        newCellStyle.setAlignment(CellStyle.ALIGN_CENTER); //水平居中
        cell0.setCellStyle(newCellStyle);
		cell0.setCellValue(nd+"年"+yd+"月安亭医院"+xmlbmc+"个人标化工作量明细");
		
		row = sheet.createRow((short)1);
		//创建列头
		String[] str = new String[]{"姓名","个人标化工作量"};
		for (int i = 0; i < str.length; i++) {
			row.createCell(i).setCellValue(str[i]);
		}
		List<GRJJVO> list = ser.findAllByLBGr(condition.getMap());
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((short)i+2);
			row.createCell(0).setCellValue(list.get(i).getYgxm());
			row.createCell(1).setCellValue(list.get(i).getGrbhgz());
		}
		OutputStream ouputStream = null;
		try {
			ouputStream = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + UtilStr.encodeFileName(request, nd+"年"+yd+"月安亭医院"+xmlbmc+"个人标化工作量明细.xls"));
			wb.write(ouputStream);
		} catch (Exception e) {
		} finally {
			if (ouputStream != null) {
				try {
					ouputStream.flush();
					ouputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
