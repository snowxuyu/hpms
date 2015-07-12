package com.hpms.jjfp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.jjfp.entity.HPYS_RLCBYS;
import com.hpms.jjfp.entity.HPYS_YSZX;
import com.hpms.jjfp.service.RlcbysSer;
import com.hpms.jjfp.service.RlcbzxSer;
import com.hpms.jjfp.vo.RlcbysVO;
import com.hpms.jjfp.vo.RlcbysVOListForm;
import com.hpms.jjfp.vo.Xj;
import com.hpms.util.DateUtil;
import com.hpms.util.DoubleUtil;
import com.hpms.util.UtilStr;
import com.hpms.xtwh.service.ZhzcxxSer;
import com.hpms.yljx.entity.HPXT_YGZCXX;
import com.rongda.common.util.DBUtil;

/**
 * 
 * 类名：RlcbysController <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月20日 <br>
 * 描述：人力成本预算控制器 <br>
 */
@Controller
@RequestMapping("/jjfp/rlcbys")
public class RlcbysController {

	private static Logger logger = Logger.getLogger(RlcbysController.class);
	
	@Autowired
	private RlcbysSer ser;
	
	@Autowired
	private RlcbzxSer rlcbzxSer;
	
	@Autowired
	private ZhzcxxSer zhzcxxSer;
	
	private HSSFWorkbook wb = new HSSFWorkbook();
	
	@RequestMapping
	public String show() {
		return "/jjfp/rlcbys";
	}
	
	/**
	 * 根据年度查询预算信息
	 * @return
	 */
	@RequestMapping(value="/query")
	@ResponseBody
	public List<HPYS_YSZX> query(@RequestParam(required=false) String nd) {
		return ser.selectNdYs(nd);
	}
	
	/**
	 * 根据年度查询预算信息
	 * @return
	 */
	@RequestMapping(value="/query/fpqkb")
	public String queryFPQKB(HttpSession session, Model model) {
		
		Object zhfz = session.getAttribute("zhfz");
		if ("A01".equals(zhfz)) {
			HPXT_YGZCXX ygzcxx = zhzcxxSer.findByZHNM((String) session.getAttribute("username"));
			model.addAttribute("ygxm", ygzcxx.getYgxm());
			model.addAttribute("ygbh", ygzcxx.getYgbh());
			model.addAttribute("ksbh", ygzcxx.getKsnm());
			model.addAttribute("ksmc", ygzcxx.getKsmc());
			Calendar calendar = Calendar.getInstance();
			model.addAttribute("nd", calendar.get(Calendar.YEAR));
			model.addAttribute("yd", calendar.get(Calendar.MONTH)+1);
			
			return "/jjfp/jj_gr";
		}
		model.addAttribute("listFPB", ser.selectNdYs(null));
		return "/jjfp/fpqkb";
	}
	
	/**
	 * 查询预算表
	 * @param nd
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/query/nd")
	public String query(@RequestParam(required=false) String nd, Model model, HttpSession session) {
		if (nd == null || "".equals(nd)) {
			nd = String.valueOf(DateUtil.getYear());
		}
		session.setAttribute("nd", nd);
		List<RlcbysVO> list = ser.findByNd(nd);
		//处理相同数据
		int count = 0;
		String fjmc = null;
		List<Integer> listCount = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			if (fjmc == null) {
				fjmc = list.get(i).getFjmc();
				count = 1;
				continue;
			}
			if (list.get(i).getFjmc().equals(fjmc) && i != list.size() - 1) {
				count++;
			} else if (i == list.size() - 1) {
				count++;
				listCount.add(count);
			} else {
				listCount.add(count);
				count = 1;
				fjmc = list.get(i).getFjmc();
			}
		}
		List<Xj> listXj = new ArrayList<Xj>();
		Xj xj = new Xj();
		if (listCount.size() == 0) {
			listCount.add(1);
		}
		count = listCount.get(0);
		int cs = 0;
		// 小计
		for (int i = 0; i < list.size(); i++) {
			
			xj.setY1(xj.getY1() + DoubleUtil.parseLower(list.get(i).getY1()));
			xj.setY2(xj.getY2() + DoubleUtil.parseLower(list.get(i).getY2()));
			xj.setY3(xj.getY3() + DoubleUtil.parseLower(list.get(i).getY3()));
			xj.setY4(xj.getY4() + DoubleUtil.parseLower(list.get(i).getY4()));
			xj.setY5(xj.getY5() + DoubleUtil.parseLower(list.get(i).getY5()));
			xj.setY6(xj.getY6() + DoubleUtil.parseLower(list.get(i).getY6()));
			xj.setY7(xj.getY7() + DoubleUtil.parseLower(list.get(i).getY7()));
			xj.setY8(xj.getY8() + DoubleUtil.parseLower(list.get(i).getY8()));
			xj.setY9(xj.getY9() + DoubleUtil.parseLower(list.get(i).getY9()));
			xj.setY10(xj.getY10() + DoubleUtil.parseLower(list.get(i).getY10()));
			xj.setY11(xj.getY11() + DoubleUtil.parseLower(list.get(i).getY11()));
			xj.setY12(xj.getY12() + DoubleUtil.parseLower(list.get(i).getY12()));
			
			if (i == count - 1) {
				listXj.add(xj);
				cs++;
				if (cs == listCount.size()) {
					break;
				}
				count += listCount.get(cs);
				xj = new Xj();
			}
		}
		count = 0;
		System.out.println(listXj.size());
		for (Xj xj2 : listXj) {
			count += xj2.getY1()+xj2.getY2()+xj2.getY3()+xj2.getY4()+xj2.getY5()+xj2.getY6()
					+xj2.getY7()+xj2.getY8()+xj2.getY9()+xj2.getY10()+xj2.getY11()+xj2.getY12();
		}
		
		List<HPYS_YSZX> ndysList = rlcbzxSer.selectNdYs(nd);
		model.addAttribute("ysje", ndysList.get(0).getYszje());
		model.addAttribute("listCount", listCount);
		model.addAttribute("rlcbysList", list);
		model.addAttribute("xjList", listXj);
		return "/jjfp/rlcbys_ys";
	}
	
	/**
	 * 修改预算值
	 * @param listForm
	 * @param session
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String update(RlcbysVOListForm listForm, HttpSession session) {
		String nd = (String) session.getAttribute("nd");
		logger.info("年度：" + nd + "," + listForm.getRlcbysList().size());
		List<RlcbysVO> rlcbysList = listForm.getRlcbysList();
		for (RlcbysVO rlcbysVO : rlcbysList) {
			List<HPYS_RLCBYS> list = ser.findByXmbm(nd, rlcbysVO.getXmbm());
			
			list.get(0).setYsz(rlcbysVO.getY1());
			list.get(1).setYsz(rlcbysVO.getY2());
			list.get(2).setYsz(rlcbysVO.getY3());
			list.get(3).setYsz(rlcbysVO.getY4());
			list.get(4).setYsz(rlcbysVO.getY5());
			list.get(5).setYsz(rlcbysVO.getY6());
			list.get(6).setYsz(rlcbysVO.getY7());
			list.get(7).setYsz(rlcbysVO.getY8());
			list.get(8).setYsz(rlcbysVO.getY9());
			list.get(9).setYsz(rlcbysVO.getY10());
			list.get(10).setYsz(rlcbysVO.getY11());
			list.get(11).setYsz(rlcbysVO.getY12());
			
			try {
				//ser.updateBatch(list);
				DBUtil.updateBatch(list);
			} catch (Exception e) {
				logger.info(rlcbysVO.getXmbm(),e);
			}
			
			logger.info(rlcbysVO.getXmbm());
		}
		return "success";
	}
	
	/**
	 * 查看执行
	 * @return
	 */
	@RequestMapping("/ck")
	public String queryZx() {
		return "/jjfp/rlcbys_ck";
	}
	
	@RequestMapping("/query/ck")
	@ResponseBody
	public List<HPYS_YSZX> queryYs(@RequestParam(required=false) String nd) {
		return ser.selectNdYs(nd);
	}
	
	@RequestMapping("/query/ck/nd")
	public String queryYs(@RequestParam(required=false) String nd, Model model, HttpSession session) {
		if (nd == null || "".equals(nd)) {
			nd = String.valueOf(DateUtil.getYear());
		}
		session.setAttribute("nd", nd);
		List<RlcbysVO> list = ser.findByNd(nd);
		
		wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(nd + "人力总成本预算表");
		sheet.setDefaultColumnWidth(15);
		HSSFRow row = sheet.createRow((short)0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));
		HSSFCell cell0 = row.createCell(0);
		CellStyle newCellStyle = wb.createCellStyle();
		newCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //垂直居中  
        newCellStyle.setAlignment(CellStyle.ALIGN_CENTER); //水平居中
        cell0.setCellStyle(newCellStyle);
		cell0.setCellValue(nd + "人力总成本预算表");
		row = sheet.createRow((short)1);
		//创建列头
		String[] str = new String[]{"预算项目","一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));
		row.createCell(0).setCellValue(str[0]);
		for (int i = 2; i <= str.length; i++) {
			row.createCell(i).setCellValue(str[i-1]);
		}
		
		
		//处理相同数据
		int count = 0;
		String fjmc = null;
		List<Integer> listCount = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			if (fjmc == null) {
				fjmc = list.get(i).getFjmc();
				count = 1;
				continue;
			}
			if (list.get(i).getFjmc().equals(fjmc) && i != list.size() - 1) {
				count++;
			} else if (i == list.size() - 1) {
				count++;
				listCount.add(count);
			} else {
				listCount.add(count);
				count = 1;
				fjmc = list.get(i).getFjmc();
			}
		}
		List<Xj> listXj = new ArrayList<Xj>();
		Xj xj = new Xj();
		if (listCount.size() == 0) {
			listCount.add(1);
		}
		count = listCount.get(0);
		int cs = 0;
		// 小计
		for (int i = 0; i < list.size(); i++) {
			
			xj.setY1(xj.getY1() + DoubleUtil.parseLower(list.get(i).getY1()));
			xj.setY2(xj.getY2() + DoubleUtil.parseLower(list.get(i).getY2()));
			xj.setY3(xj.getY3() + DoubleUtil.parseLower(list.get(i).getY3()));
			xj.setY4(xj.getY4() + DoubleUtil.parseLower(list.get(i).getY4()));
			xj.setY5(xj.getY5() + DoubleUtil.parseLower(list.get(i).getY5()));
			xj.setY6(xj.getY6() + DoubleUtil.parseLower(list.get(i).getY6()));
			xj.setY7(xj.getY7() + DoubleUtil.parseLower(list.get(i).getY7()));
			xj.setY8(xj.getY8() + DoubleUtil.parseLower(list.get(i).getY8()));
			xj.setY9(xj.getY9() + DoubleUtil.parseLower(list.get(i).getY9()));
			xj.setY10(xj.getY10() + DoubleUtil.parseLower(list.get(i).getY10()));
			xj.setY11(xj.getY11() + DoubleUtil.parseLower(list.get(i).getY11()));
			xj.setY12(xj.getY12() + DoubleUtil.parseLower(list.get(i).getY12()));
			
			if (i == count - 1) {
				listXj.add(xj);
				cs++;
				if (cs == listCount.size()) {
					break;
				}
				count += listCount.get(cs);
				xj = new Xj();
			}
		}
		List<HPYS_YSZX> ndysList = rlcbzxSer.selectNdYs(nd);
		model.addAttribute("ndysList", ndysList);
		model.addAttribute("listCount", listCount);
		model.addAttribute("rlcbysList", list);
		model.addAttribute("rlcbzxList", rlcbzxSer.findByNd(nd));
		model.addAttribute("xjList", listXj);
		
		int countTemp = 1;
		int countNext = 0;
		int sumCount = listCount.get(0);
		for (int i = 0; i < list.size(); i++) {
			
			if (sumCount == i) {
				row = sheet.createRow(i+countTemp+1);
				row.createCell(1).setCellValue("小计");
				sheet.addMergedRegion(new CellRangeAddress(i+countTemp+1, i+countTemp+1, 2, 4));
				sheet.addMergedRegion(new CellRangeAddress(i+countTemp+1, i+countTemp+1, 5, 7));
				sheet.addMergedRegion(new CellRangeAddress(i+countTemp+1, i+countTemp+1, 8, 10));
				sheet.addMergedRegion(new CellRangeAddress(i+countTemp+1, i+countTemp+1, 11, 13));
				row.createCell(2).setCellValue(listXj.get(countNext).getJ1());
				row.createCell(5).setCellValue(listXj.get(countNext).getJ2());
				row.createCell(8).setCellValue(listXj.get(countNext).getJ3());
				row.createCell(11).setCellValue(listXj.get(countNext).getJ4());
				countNext++;
				countTemp++;
				sumCount += listCount.get(countNext);
			}
			row = sheet.createRow(i+countTemp+1);
			
			if (i == 0) {
				sheet.addMergedRegion(new CellRangeAddress(2, sumCount+2, 0, 0));
				row.createCell(0).setCellValue(list.get(i).getFjmc());
			} else if (i == sumCount - listCount.get(countNext)) {
				sheet.addMergedRegion(new CellRangeAddress(i+countTemp+1, i+countTemp+1+listCount.get(countNext), 0, 0));
				row.createCell(0).setCellValue(list.get(i+1).getFjmc());
			}
			
			row.createCell(1).setCellValue(list.get(i).getXmmc());
			String sjzt = list.get(i).getSjzt();
			for (int j = 0; j < 12; j++) {
				if ("2".equals(sjzt)) {
					if (j%3==0) {
						sheet.addMergedRegion(new CellRangeAddress(i+countTemp+1, i+countTemp+1, j+2, j+4));
						continue;
					}
					if ((j-1)%3==0) {
						continue;
					}
					if ((j-2)%3==0) {
						Double d = list.get(i).getyList().get(j);
						if (d != null) {
							row.createCell(j).setCellValue(d);
						}
						continue;
					}
				}
				Double d = list.get(i).getyList().get(j);
				if (d != null) {
					row.createCell(j+2).setCellValue(d);
				}
			}
			
		}
		row = sheet.createRow(list.size()+countTemp+1);
		row.createCell(1).setCellValue("小计");
		sheet.addMergedRegion(new CellRangeAddress(list.size()+countTemp+1, list.size()+countTemp+1, 2, 4));
		sheet.addMergedRegion(new CellRangeAddress(list.size()+countTemp+1, list.size()+countTemp+1, 5, 7));
		sheet.addMergedRegion(new CellRangeAddress(list.size()+countTemp+1, list.size()+countTemp+1, 8, 10));
		sheet.addMergedRegion(new CellRangeAddress(list.size()+countTemp+1, list.size()+countTemp+1, 11, 13));
		row.createCell(2).setCellValue(listXj.get(countNext).getJ1());
		row.createCell(5).setCellValue(listXj.get(countNext).getJ2());
		row.createCell(8).setCellValue(listXj.get(countNext).getJ3());
		row.createCell(11).setCellValue(listXj.get(countNext).getJ4());
		
		return "/jjfp/rlcbys_ck_ys";
	}
	
	@RequestMapping("/query/ck/{nd}/exportExcel/{param}")
	public void export(@PathVariable String nd, @PathVariable String param, HttpServletRequest request,HttpServletResponse response) {
		
		OutputStream ouputStream = null;
		try {
			ouputStream = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			if ("1".equals(param)) {
				response.setHeader("Content-disposition", "attachment;filename=" + UtilStr.encodeFileName(request, nd+"人力总成本预算表.xls"));
				wb.write(ouputStream);
			} else if ("2".equals(param)) {
				
			} else if ("3".equals(param)) {
				
			}
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
