package com.hpms.yljx.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.hpms.util.UtilStr;
import com.hpms.yljx.entity.HPCS_HXXMZD;
import com.hpms.yljx.service.HxxmzdSer;

/**
 * 
 * 类名：HxxmzdController <br>
 * 作者：吴陶君 <br>
 * 日期：2015年1月13日 <br>
 * 描述：核心项目字典控制器 <br>
 */
@Controller
@RequestMapping("/yljx/hxxmzd")
public class HxxmzdController {

	@Autowired
	private HxxmzdSer ser;
	
	@RequestMapping
	public String show() {
		return "/yljx/hxxmzd";
	}
	
	/**
	 * 按照项目类别编码查找核心项目字典列表
	 * @param page
	 * @param rows
	 * @param ksnm
	 * @return
	 */
	@RequestMapping("/query")
	@ResponseBody
	public Map<String, Object> query(int page, int rows, @RequestParam(required=false) String hxlbbm,
			@RequestParam(required=false) String xmlbbm, @RequestParam(required=false) String xmmc) {
		Map<String,Object> map = new HashMap<String, Object>();
		if (hxlbbm != null && !"".equals(hxlbbm)) {
			map.put("hxlbbm", hxlbbm);
		}
		if (xmlbbm != null && !"".equals(xmlbbm)) {
			map.put("xmlbbm", xmlbbm);
		}
		if (xmmc != null && !"".equals(xmmc)) {
			map.put("xmmc", xmmc);
		}
		return ser.findXm(page, rows, map);
	}
	
	/**
	 * 新增核心项目字典
	 * @param bean
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String add(HPCS_HXXMZD bean) {
		try {
			if (ser.existXMBM(bean.getXmbm())) {
				return "e1";
			}
			if (ser.existXMMC(bean.getXmmc())) {
				return "e2";
			}
			bean.setCjr("admin");
			ser.add(bean);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 更新核心项目字典
	 * @param item
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String update(HPCS_HXXMZD item) {
		try {
			HPCS_HXXMZD bean = ser.findById(item.getXmbm());
			bean.setXmlbbm(item.getXmlbbm());
			bean.setXmmc(item.getXmmc());
			//bean.setHxlbbm(item.getHxlbbm());
			//bean.setXmlx(item.getXmlx());
			bean.setDsjsa(item.getDsjsa());
			bean.setDsdeb(item.getDsdeb());
			ser.update(bean);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 根据项目编码删除核心项目字典
	 * @param xmbm
	 * @return
	 */
	@RequestMapping("/remove")
	@ResponseBody
	public String remove(String xmbm) {
		try {
			ser.removeById(xmbm);
		} catch (Exception e) {
			return "fail";
		}
		return "success";
	}
	
	/**
	 * 按照项目类别编码查找核心项目字典列表
	 * @param page
	 * @param rows
	 * @param ksnm
	 * @return
	 */
	@RequestMapping("/download")
	@ResponseBody
	public void download(@RequestParam(required=false) String hxlbbm, @RequestParam(required=false) String xmlbbm, 
			@RequestParam(required=false) String xmmc, HttpServletRequest request ,HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String, Object>();
		/*if (hxlbbm != null && !"".equals(hxlbbm)) {
			map.put("hxlbbm", hxlbbm);
		}*/
		if (xmlbbm != null && !"".equals(xmlbbm)) {
			map.put("xmlbbm", xmlbbm);
		}
		if (xmmc != null && !"".equals(xmmc)) {
			map.put("xmmc", xmmc);
		}
		List<HPCS_HXXMZD> list = ser.findXm(map);
		HSSFWorkbook book = ser.getBook(list, "核心项目字典维护表");
		OutputStream ouputStream = null;
		try {
			ouputStream = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + UtilStr.encodeFileName(request, "核心项目字典维护表.xls"));
			book.write(ouputStream);
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
	
	@SuppressWarnings("resource")
	@RequestMapping(value="/import", produces="text/html;charset=UTF-8")
	@ResponseBody
	public String importtodb(@RequestParam("file") MultipartFile file) {
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
					} catch (Exception ex) {
					}
					// 读取表格的第一个sheet页
					Sheet sheet = book.getSheetAt(0);
					// 定义 row、cell
					Row row;
					// 总共有多少行,从0开始
					int totalRows = sheet.getLastRowNum();
					List<HPCS_HXXMZD> list = new ArrayList<HPCS_HXXMZD>();
					List<String> listXMBM = new ArrayList<String>();
					for (int i = 2; i <= totalRows; i++) {
						row = sheet.getRow(i);
						if(row == null) {
							continue;
						}
						Cell cell = row.getCell(0);
						//项目编码处理
						String xmbm = null;
						if (cell == null || "".equals(cell.toString().trim())) {
							break;
						}
						if (row.getCell(2) == null) {
							return "第"+(i+1)+"行项目编码不能为空！";
						} else {
							xmbm = row.getCell(2).toString().trim();
							if ("".equals(xmbm)) {
								return "第"+(i+1)+"行项目编码不能为空！";
							}
							if (xmbm.indexOf(".")>0) {
								xmbm = xmbm.substring(0, xmbm.indexOf(".")+1);
							}
							if (xmbm.length()>32) {
								return "项目编码存在超过32位，不能导入!";
							}
						}
						//判断是否存在
						if (ser.existXMBM(xmbm)) {
							continue;
						}
						if (listXMBM.contains(xmbm)) {
							return "第"+(listXMBM.indexOf(xmbm)+2)+"行与第"+(i+1)+"行编码重复！";
						}
						listXMBM.add(xmbm);
						//项目类别编码处理
						String xmlbbm = null;
						cell = row.getCell(0);
						if (cell == null) {
							return "第"+(i+1)+"行项目类别编码不能为空！";
						} else {
							xmlbbm = cell.toString().trim();
							if (xmlbbm==null || "".equals(xmlbbm)) {
								//return "第"+(i+1)+"行项目类别编码不能为空！";
								return "项目类别编码有空值存在,不能导入!";
							}
						}
						/*String hxlbbm = row.getCell(0).toString().trim();
						if (hxlbbm==null || "".equals(hxlbbm)) {
							return "核心类别编码有空值存在,不能导入!";
						}
						String xmlx = row.getCell(4).toString().trim();
						if (xmlx==null || "".equals(xmlx)) {
							return "项目类型有空值存在,不能导入";
						}
						if ("个性项目".equals(xmlx)) {
							xmlx = "1";
						} else if ("公共项目".equals(xmlx)) {
							xmlx = "2";
						} else if ("材料项目".equals(xmlx)) {
							xmlx = "3";
						}*/
						Cell cell2 = row.getCell(1);
						String xmmc = "";
						if (cell2!=null) {
							xmmc = cell2.toString().trim();
						}
						/******/
						if (xmmc==null || "".equals(xmmc)) {
							return "项目名称有空值存在,不能导入!";
						}
						
						/**
						 * 去除重复
						 */
						
						//String dsjsa = row.getCell(7).toString().trim();
						String dsdeb = row.getCell(4).toString().trim();
						HPCS_HXXMZD bean = new HPCS_HXXMZD();
						//bean.setHxlbbm(hxlbbm);
						bean.setXmlbbm(xmlbbm);
						//bean.setXmlx(xmlx);
						bean.setXmbm(xmbm);
						bean.setXmmc(xmmc);
						bean.setCjr("admin");
						bean.setZt("1");
						/*if (dsjsa== null || dsjsa == "") {
							bean.setDsjsa(0.0);
						} else {
							bean.setDsjsa(Double.parseDouble(dsjsa));
						}*/
						if (dsdeb== null || dsdeb == "") {
							bean.setDsdeb(0.0);
						} else {
							bean.setDsdeb(Double.parseDouble(dsdeb));
						}
						list.add(bean);
					}
					ser.addBatch(list);
				} catch (IOException e) {
					e.printStackTrace();
					return "不能正常读取数据，请稍后重试！";
				}
			}
		}
		return "success";
	}
}
