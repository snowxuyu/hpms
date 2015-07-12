package com.hpms.yljx.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.yljx.entity.HPCS_HXXMZD;
import com.hpms.yljx.service.HxxmzdSer;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service
public class HxxmzdSerImpl extends BaseSerImpl<HPCS_HXXMZD> implements HxxmzdSer {

	@Override
	public Map<String, Object> findByPage(int page, int rows, String xmlbbm) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("xmlbbm", xmlbbm);
		return findByPage("hpcs_hxxmzd.query_xmlbbm", page, rows, map);
	}
	
	@Override
	public Map<String, Object> findHxxmzd(int page, int rows,
			Map<String, Object> map) {
		return this.findByPage("hpcs_hxxmpz.query_hxxmzd", page, rows, map);
	}
	
	@Override
	public Map<String, Object> findXm(int page, int rows, Map<String, Object> map) {
		return findByPage("hpcs_hxxmzd.query_xm", page, rows, map);
	}
	
	@Override
	public List<HPCS_HXXMZD> findXm(Map<String, Object> map) {
		SqlResult sql = SqlUtil.getSql("hpcs_hxxmzd.query_xm", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), HPCS_HXXMZD.class);
	}

	@Override
	public List<HPCS_HXXMZD> selectXmmc() {
		SqlResult sql = SqlUtil.getSql("kjxmmx.queryXmmc");
		List<HPCS_HXXMZD> list = DBUtil.find(sql.getSql(), HPCS_HXXMZD.class);
		return list;
	}

	@Override
	public HPCS_HXXMZD findByXMBM(String xmbm) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("xmbm", xmbm);
		SqlResult sql = SqlUtil.getSql("hpcs_hxxmzd.query_exist",map);
		List<HPCS_HXXMZD> list = DBUtil.find(sql.getSql(), sql.getParam(), HPCS_HXXMZD.class);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public HPCS_HXXMZD findByXMMC(String xmmc) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("xmmc", xmmc);
		SqlResult sql = SqlUtil.getSql("hpcs_hxxmzd.query_exist",map);
		List<HPCS_HXXMZD> list = DBUtil.find(sql.getSql(), sql.getParam(), HPCS_HXXMZD.class);
		if (list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public boolean existXMBM(String xmbm) {
		if (findByXMBM(xmbm) == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean existXMMC(String xmmc) {
		if (findByXMMC(xmmc) == null) {
			return false;
		}
		return true;
	}

	@Override
	public List<HPCS_HXXMZD> findByHXLBBM(String hxlbbm) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("hxlbbm", hxlbbm);
		SqlResult sql = SqlUtil.getSql("hpcs_hxxmzd.query_exist",map);
		List<HPCS_HXXMZD> list = DBUtil.find(sql.getSql(), sql.getParam(), HPCS_HXXMZD.class);
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	@Override
	public List<HPCS_HXXMZD> findByXMLBBM(String xmlbbm) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("xmlbbm", xmlbbm);
		SqlResult sql = SqlUtil.getSql("hpcs_hxxmzd.query_exist",map);
		List<HPCS_HXXMZD> list = DBUtil.find(sql.getSql(), sql.getParam(), HPCS_HXXMZD.class);
		if (list.size() == 0) {
			return null;
		}
		return list;
	}

	@Override
	public boolean existHXLBBM(String hxlbbm) {
		if (findByHXLBBM(hxlbbm) == null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean existXMLBBM(String xmlbbm) {
		if (findByXMLBBM(xmlbbm) == null) {
			return false;
		}
		return true;
	}

	@Override
	public HSSFWorkbook getBook(List<HPCS_HXXMZD> list, String name) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(name);
		sheet.setDefaultColumnWidth(15);
		
		HSSFRow row = sheet.createRow((short)0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
		HSSFCell cell0 = row.createCell(0);
		CellStyle newCellStyle = wb.createCellStyle();
		newCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //垂直居中  
        newCellStyle.setAlignment(CellStyle.ALIGN_CENTER); //水平居中
        cell0.setCellStyle(newCellStyle);
		cell0.setCellValue(name);
		
		row = sheet.createRow((short)1);  
		//创建列头
		String[] str = new String[]{"项目类别编码","项目类别名称","项目编码","项目名称","RBRVS点数"};
		for (int i = 0; i < str.length; i++) {
			row.createCell(i).setCellValue(str[i]);
		}
		int i = 1;
		for (HPCS_HXXMZD bean : list) {
			row = sheet.createRow(i+1);
			//row.createCell(0).setCellValue(bean.getHxlbbm());
			//row.createCell(1).setCellValue(bean.getHxlbmc());
			row.createCell(0).setCellValue(bean.getXmlbbm());
			row.createCell(1).setCellValue(bean.getXmlbmc());
			/*if ("1".equals(bean.getXmlx())) {
				row.createCell(4).setCellValue("个性项目");
			} else if ("2".equals(bean.getXmlx())) {
				row.createCell(4).setCellValue("公共项目");
			} else if ("3".equals(bean.getXmlx())) {
				row.createCell(4).setCellValue("材料项目");
			}*/
			row.createCell(2).setCellValue(bean.getXmbm());
			row.createCell(3).setCellValue(bean.getXmmc());
			//row.createCell(4).setCellValue(bean.getDsjsa());
			row.createCell(4).setCellValue(bean.getDsdeb());
			i++;
		}
		return wb;
	}
}
