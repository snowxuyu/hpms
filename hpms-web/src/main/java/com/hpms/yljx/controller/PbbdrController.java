package com.hpms.yljx.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hpms.util.DateUtil;
import com.hpms.yljx.entity.HPCS_BBFLK;
import com.hpms.yljx.entity.HPCS_PBBDR;
import com.hpms.yljx.entity.HPXT_KSZCXX;
import com.hpms.yljx.service.BbflkSer;
import com.hpms.yljx.service.KszcxxSer;
import com.hpms.yljx.service.PbbdrSer;
import com.hpms.yljx.service.YgzcxxSer;

@Controller
@RequestMapping("/yljx/pbbdr")
public class PbbdrController {

	private static Logger logger = Logger.getLogger(PbbdrController.class);
	
	@Autowired
	private BbflkSer bbflkSer;
	
	@Autowired
	private YgzcxxSer ygzcxxSer;
	
	@Autowired
	private KszcxxSer kszcxxSer;
	
	@Autowired
	private PbbdrSer pbbdrSer;
	
	@RequestMapping
	public String show() {
		return "/yljx/pbbdr";
	}
	
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> query(int page, int rows, @RequestParam(required=false) String ksmc, @RequestParam(required=false) String ygmc) {
		return pbbdrSer.findByKSMCWithYGMC(page, rows, ksmc, ygmc);
	}
	
	@RequestMapping("/query/jjb")
	@ResponseBody
	public Map<String, Object> queryJjb(int page, int rows, @RequestParam(required=false) String ksmc, @RequestParam(required=false) String ygmc) {
		return pbbdrSer.findByKSMCWithYGMC2(page, rows, ksmc, ygmc);
	}
	
	@RequestMapping(value="/import", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String importtodb(@RequestParam("file") MultipartFile file, @RequestParam("ny") String ny, @RequestParam("ksnm") String ksnm) {
		if (file.isEmpty()) {
			return "文件不能为空！";
		} else {
			String fileName = file.getOriginalFilename();
			String prefix=fileName.substring(fileName.lastIndexOf(".") + 1);
			String pre = prefix.toLowerCase();
			if (!"xls".equals(pre)) {
				return "文件必须为Excel类型！";
			} else {
				try {
					InputStream uploadFile = file.getInputStream();
					Workbook book = null;
					book = new HSSFWorkbook(uploadFile);
					// 读取表格的第一个sheet页
					Sheet sheet = book.getSheetAt(0);
					// 定义 row、cell
					Row row;
					// 总共有多少行,从0开始 -- 3
					int totalRows = sheet.getLastRowNum();
					
					// 先看用户 ->
					Map<String,Object> mapAll = new HashMap<String, Object>();
					List<String> list = null;
					String year = ny.substring(0, 4);
					String month = ny.substring(ny.indexOf("-")+1);
					logger.info(year + "年" + month + "月");
					Integer days = DateUtil.getThisMonthDays(year, month);
					logger.info("当月有：" + days + "天");
					
					pbbdrSer.removeByNY(year,month,ksnm);
					
					//查找科室
					HPXT_KSZCXX kszcxx = kszcxxSer.findById(ksnm);
					
					//查班别
					List<HPCS_BBFLK> bbListAll = bbflkSer.findAll();
					List<String> bbList = new ArrayList<String>();
					Map<String,String> _map = new HashMap<String, String>();
					Map<String,String> _map2 = new HashMap<String, String>();
					for (HPCS_BBFLK bean : bbListAll) {
						//保存班别简写
						bbList.add(bean.getBbjx());
						_map.put(bean.getBbjx(), bean.getBbmc());
						_map2.put(bean.getBbjx(), bean.getBbbm());
					}
					
					int rowfrom = 3;
					int colfrom = 2;
					HPCS_PBBDR bean = null;
					boolean _con = true;
					List<HPCS_PBBDR> list_to = new ArrayList<HPCS_PBBDR>();
					for (int i = rowfrom; i < totalRows; i++) {
						colfrom = 2;
						_con = true;
						row = sheet.getRow(i);
						Cell cell = row.getCell(colfrom);
						if (cell == null || "".equals(cell.toString().trim())) {
							continue;
						}
						String ygbh = cell.toString().trim(); //员工编号
						if (ygbh.indexOf(".")>0) {
							ygbh = ygbh.substring(0, ygbh.indexOf("."));
						}
						cell = row.getCell(colfrom+1);
						if (cell == null || "".equals(cell.toString().trim())) {
							continue;
						}
						String ygxm = cell.toString().trim(); //员工名称
						colfrom = 4;
						String bbjx = "";
						list = new ArrayList<String>();
						int k = 1;
						for (int j = colfrom; j < colfrom + days; j++) {
							bbjx = row.getCell(j).toString().trim(); //班别名称
							if (!bbList.contains(bbjx)) {
								_con = false;
								break;
							}
							bean = new HPCS_PBBDR();
							bean.setKsnm(ksnm);
							bean.setKsmc(kszcxx.getKsmc());
							bean.setYgbh(ygbh);
							bean.setYgmc(ygxm);
							bean.setNd(Integer.parseInt(year));
							bean.setYd(Integer.parseInt(month));
							bean.setTs(k);
							bean.setBbbm(_map2.get(bbjx));
							bean.setBbmc(_map.get(bbjx));
							bean.setBbcs(1.0);
							bean.setCjr("admin");
							list_to.add(bean);
							k++;
						}
						
						if (!_con) {
							continue;
						}
						
						
						String jjb = row.getCell(colfrom + days).toString().trim();
						if (jjb != null && !"".equals(jjb)) {
							try {
								bean = new HPCS_PBBDR();
								bean.setKsnm(ksnm);
								bean.setKsmc(kszcxx.getKsmc());
								bean.setYgbh(ygbh);
								bean.setYgmc(ygxm);
								bean.setNd(Integer.parseInt(year));
								bean.setYd(Integer.parseInt(month));
								bean.setTs(32);
								bean.setBbbm("99");
								bean.setBbmc("加减班");
								bean.setBbcs(Double.parseDouble(jjb));
								bean.setCjr("admin");
								list_to.add(bean);
								
							} catch (Exception e) {
								return "加减班有误，请查看模版第" + (i + 1) + "行的第" + (colfrom + days + 1) + "列！";
							}
						}
						logger.info("当前核对用户至：" + ygxm);
					}
					
					pbbdrSer.addBatch(list_to);
				} catch (IOException e) {
					e.printStackTrace();
					return "不能正常读取数据，请稍后重试！";
				}
			}
		}
		return "success";
	}
	
	/*@RequestMapping(value="/import", produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public String importtodb(@RequestParam("file") MultipartFile file, @RequestParam("ny") String ny) {
		if (file.isEmpty()) {
			return "文件不能为空！";
		} else {
			String fileName = file.getOriginalFilename();
			String prefix=fileName.substring(fileName.lastIndexOf(".") + 1);
			String pre = prefix.toLowerCase();
			if (!"xls".equals(pre) && !"xlsx".equals(pre)) {
				return "文件必须为Excel类型！";
			} else {
				try {
					InputStream uploadFile = file.getInputStream();
					Workbook book = null;
					book = new HSSFWorkbook(uploadFile);
					try {
						// Excel 2007获取方法
//						book = new XSSFWorkbook(uploadFile);
					} catch (Exception ex) {
						// Excel 2003获取方法
						book = new HSSFWorkbook(uploadFile);
					}
					// 读取表格的第一个sheet页
					Sheet sheet = book.getSheetAt(0);
					// 定义 row、cell
					Row row;
					// 总共有多少行,从0开始 -- 3
					int totalRows = sheet.getLastRowNum();
					
					// 先看用户 ->
					Map<String,Object> mapAll = new HashMap<String, Object>();
					List<String> list = null;
					String year = ny.substring(0, 4);
					String month = ny.substring(ny.indexOf("-")+1);
					logger.info(year + "年" + month + "月");
					Integer days = DateUtil.getThisMonthDays(year, month);
					logger.info("当月有：" + days + "天");
					
					List<HPCS_BBFLK> bbListAll = bbflkSer.findAll();
					List<String> bbList = new ArrayList<String>();
					Map<String,String> _map = new HashMap<String, String>();
					for (HPCS_BBFLK bean : bbListAll) {
						bbList.add(bean.getBbjx());
						_map.put(bean.getBbjx(), bean.getBbmc());
					}
					
					List<PbbYGVO> ygListAll = ygzcxxSer.findAll();
					List<String> ygList = new ArrayList<String>();
					for (PbbYGVO bean : ygListAll) {
						ygList.add(bean.getYgxm());
					}
					int rowfrom = 3;
					int colfrom = 4;
					for (int i = rowfrom; i < totalRows; i++) {
						row = sheet.getRow(i);
						Cell cell = row.getCell(2);
						if (cell == null || "".equals(cell.toString().trim())) {
							break;
						}
						String ygxm = cell.toString().trim();
						if (mapAll.containsKey(ygxm)) {
							return "员工姓名重复，请查看" + (i + 1) + "行数据！";
						} else if (!ygList.contains(ygxm)) {
							return "模版第" + (i + 1) + "行的第" + colfrom + "列未在系统中找到员工编号！";
						}
						String bbmc = "";
						list = new ArrayList<String>();
						for (int j = colfrom; j < colfrom + days; j++) {
							bbmc = row.getCell(j).toString().trim();
//							logger.info("班别名称：" + bbmc);
							if (!bbList.contains(bbmc)) {
								return "模版第" + (i + 1) + "行的第" + (j + 1) + "列未在系统中找到班别编码！";
							}
							list.add(bbmc);
						}
						String jjb = row.getCell(colfrom + days).toString().trim();
						if (jjb != null && !"".equals(jjb)) {
							try {
								Double.parseDouble(jjb);
								list.add(jjb);
							} catch (Exception e) {
								return "加减班有误，请查看模版第" + (i + 1) + "行的第" + (colfrom + days + 1) + "列！";
							}
						}
						logger.info("当前核对用户至：" + ygxm);
						mapAll.put(ygxm, list);
					}
					Set<String> keySet = mapAll.keySet();
					List<String> _list = null;
					
					Map<String,Object> map = new HashMap<String, Object>();
					for (PbbYGVO bean : ygListAll) {
						map.put(bean.getYgxm(), bean);
					}
					
					Map<String,String> map2 = new HashMap<String, String>();
					for (HPCS_BBFLK bean : bbListAll) {
						map2.put(bean.getBbjx(), bean.getBbbm());
					}
					
					List<HPCS_PBBDR> list_to = new ArrayList<HPCS_PBBDR>();
					HPCS_PBBDR bean = null;
					for (String ygmc : keySet) {
						_list = (List<String>) mapAll.get(ygmc);
						for (int i = 1; i <= days; i++) {
							PbbYGVO yg = (PbbYGVO) map.get(ygmc);
							bean = new HPCS_PBBDR();
							bean.setKsnm(yg.getKsnm());
							bean.setKsmc(yg.getKsmc());
							bean.setYgbh(yg.getYgbh());
							bean.setYgmc(ygmc);
							bean.setNd(Integer.parseInt(year));
							bean.setYd(Integer.parseInt(month));
							bean.setTs(i);
							bean.setBbbm(map2.get(_list.get(i - 1)));
							bean.setBbmc(_map.get(_list.get(i - 1)));
							bean.setBbcs(1.0);
							bean.setCjr("admin");
							list_to.add(bean);
						}
						if (_list.size() == days + 1) {
							PbbYGVO yg = (PbbYGVO) map.get(ygmc);
							bean = new HPCS_PBBDR();
							bean.setKsnm(yg.getKsnm());
							bean.setKsmc(yg.getKsmc());
							bean.setYgbh(yg.getYgbh());
							bean.setYgmc(ygmc);
							bean.setNd(Integer.parseInt(year));
							bean.setYd(Integer.parseInt(month));
							bean.setTs(32);
							bean.setBbbm("99");
							bean.setBbmc("加减班");
							bean.setBbcs(Double.parseDouble(_list.get(days)));
							bean.setCjr("admin");
							list_to.add(bean);
						}
					}
					pbbdrSer.addBatch(list_to);
				} catch (IOException e) {
					e.printStackTrace();
					return "不能正常读取数据，请稍后重试！";
				}
			}
		}
		return "success";
	}*/
	
}
