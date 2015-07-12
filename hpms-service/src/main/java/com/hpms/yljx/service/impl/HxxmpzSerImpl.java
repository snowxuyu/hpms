package com.hpms.yljx.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hpms.service.impl.BaseSerImpl;
import com.hpms.yljx.entity.HPCS_HXXMPZ;
import com.hpms.yljx.entity.HPCS_HXXMZD;
import com.hpms.yljx.service.HxxmpzSer;
import com.hpms.yljx.vo.Combobox;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月14日-下午1:38:27
 *
 * 类名： 科室核心项目service层
 *
 * 描述:
 *
 */
@Service("hxxmpzSer")
public class HxxmpzSerImpl extends BaseSerImpl<HPCS_HXXMPZ> implements HxxmpzSer {
	
	private static Logger logger = Logger.getLogger(HxxmpzSerImpl.class);
	
	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map){
		
		return this.findByPage("hpcs_hxxmpz.query_hxxm", page, rows, map);
	}
	
	@Override
	public Map<String, Object> mhQueryHxxm(int page, int rows,
			Map<String, Object> map) {
		return this.findByPage("hpcs_hxxmpz.mh_query_hxxmmc", page, rows, map);
	}

	@Override
	public List<Combobox> queryXmlb(String fjbm) {
		Map<String,Object> map = new HashMap<String, Object>();
		if (!"".equals(fjbm) && null!=fjbm)
			map.put("fjbm", fjbm);
		SqlResult sql = SqlUtil.getSql("hpcs_hxxmpz.query_xmlb", map);
		return DBUtil.find(sql.getSql(), sql.getParam() ,Combobox.class);
	}
	
	public List<Combobox> queryAllXmlb() {
		SqlResult sql = SqlUtil.getSql("hpcs_hxxmpz.query_allxmlb", null);
		return DBUtil.find(sql.getSql() ,Combobox.class);
	}
	
	@Override
	public List<Combobox> queryHxlb() {
		SqlResult sql = SqlUtil.getSql("hpcs_hxxmpz.query_hxlb", null);
		return DBUtil.find(sql.getSql(), Combobox.class);
	}
	
	@Override
	public List<Combobox> queryXmbz() {
		SqlResult sql = SqlUtil.getSql("hpcs_hxxmpz.query_xmbs", null);
		return DBUtil.find(sql.getSql(),Combobox.class);
	}

	@SuppressWarnings("unused")
	@Override
	public String importHxxm(MultipartFile file, String ksnm) {
		if (file.isEmpty()){
			logger.info("文件为空或没有文件存在");
			 return null;
		}
		String filename = file.getOriginalFilename();
		String prefix=filename.substring(filename.lastIndexOf(".") + 1);
		String pre = prefix.toLowerCase();
		if(!"xls".equals(pre) && !"xlsx".equals(pre)){
			logger.info("文件类型错误");
			return null;
		}
		String ksnm1 = null;
		String ksmc = null;
		String hxlbbm = null;
		String xmlbmc = null;
		String xmbm = null;
		String xmmc = null;
		Double dsjsa = null;
		Double dsdeb = null;
		String _dsjsa = null;
		String _dsdeb = null;
		HPCS_HXXMPZ hxxmpz = null;
		List<HPCS_HXXMPZ> list = new ArrayList<HPCS_HXXMPZ>();
		HSSFWorkbook wb = null;
		try {
			InputStream uploadFile = file.getInputStream(); //把文件以流的方式读入
			wb = new HSSFWorkbook(uploadFile);  //创建excel文件
			HSSFSheet sheet = wb.getSheetAt(0); //获得excel第一页
			HSSFRow row =sheet.getRow(0); //获得第一行
			int totalRows = sheet.getLastRowNum(); //总共有多少行
			int totalCells = row.getPhysicalNumberOfCells(); //总共有多少列
			List<HPCS_HXXMPZ> listAll = findAll();   //查询所有核心项目配置
			List<HPCS_HXXMZD> listxmbm = findxmbmByZd(); //查询核心项目字典
			List<String> listXM = new ArrayList<String>();
			Map<Object, Object> maplist = new HashMap<Object, Object>();
			List<String> listbm = new ArrayList<String>();
			for (HPCS_HXXMPZ bean : listAll) {
				listXM.add(bean.getXmbm());
				maplist.put(bean.getKsnm(), bean.getXmbm());
			}
			
			for (HPCS_HXXMZD bean : listxmbm) {
				listbm.add(bean.getXmbm());
			}
			for (int i=1; i<=totalRows; i++){   //循环输出表格中的内容,首先循环取出行,再根据行循环取出列
				 row = sheet.getRow(i);
				 //判断行空
				 if (row == null) {
					 continue;
				 }
				 
				HSSFCell c0 = row.getCell(0);  //科室内码
				if (null == c0) continue;
				HSSFCell c1 = row.getCell(1);  //科室名称
				if (null == c1) continue;
				HSSFCell c2 = row.getCell(2);  //核心类别
				if (null == c2) continue;
				HSSFCell c3 = row.getCell(3);  //项目类别
				if (null == c3) continue;
				HSSFCell c4 = row.getCell(4);  //项目编码
				if (null == c4) continue;
				HSSFCell c5 = row.getCell(5);  //项目名称
				if (null == c5) continue;
				HSSFCell c6 = row.getCell(6);  //点数基数A
				if (null == c6) continue;
				HSSFCell c7 = row.getCell(7);  //点数定额B
				if (null == c7) continue;
				
				/* 第一种写法
				ksnm = c0.getStringCellValue();    //在类型转换的时候出错
				ksmc = c1.getStringCellValue();
				hxlbbm = c2.getStringCellValue();
				xmlbmc = c3.getStringCellValue();
				xmbm = c4.getStringCellValue();
				xmmc = c5.getStringCellValue();
				dsjsa = c6.getNumericCellValue(); 
				dsdeb = c7.getNumericCellValue();*/
				
				ksnm1 = c0.toString().trim(); 
				ksmc = c1.toString().trim();
				hxlbbm = c2.toString().trim();
				xmlbmc = c3.toString().trim();
				xmbm = c4.toString().trim();
				xmmc = c5.toString().trim();
				_dsjsa = c6.toString().trim();
				
				//判断导入的科室是否正确
				if (!ksnm.equals(ksnm1)) {
					logger.info("选择的科室和导入的科室不一致");
					return null;
				}
				
				//去除没有的项目编码  如果项目字典表没有就不能插入
				if (!listbm.contains(xmbm)) {
					continue;
				}
				
				
				
				//判断重复
				//if (listXM.contains(xmbm)) {
				if (maplist.containsKey(ksnm) && maplist.containsValue(xmbm)){
					//删除整个科室的
					Map<String, Object> map2 = new HashMap<String, Object>();
					if (null!=ksnm && !"".equals(ksnm)) {
						map2.put("ksnm", ksnm);
					}
					SqlResult sql = SqlUtil.getSql("hpcs_hxxmpz.queryCf", map2);
					List<HPCS_HXXMPZ> find = DBUtil.find(sql.getSql(), sql.getParam() ,HPCS_HXXMPZ.class);
					this.removeBatch(find);
					//删除该条记录
					/*Map<String, Object> map3 = new HashMap<String, Object>();
					if (null!=ksnm && !"".equals(ksnm)) {
						map3.put("ksnm", ksnm);
					}
					map3.put("xmbm", xmbm);
					SqlResult sql = SqlUtil.getSql("hpcs_hxxmpz.queryCf", map3);
					List<HPCS_HXXMPZ> find = DBUtil.find(sql.getSql(), sql.getParam() ,HPCS_HXXMPZ.class);
					this.remove(find.get(0));*/
					//continue;
				}
				
				if (!"".equals(_dsjsa) && null!=_dsjsa)
					dsjsa = Double.parseDouble(_dsjsa); 
				_dsdeb = c7.toString().trim();
				if (!"".equals(_dsdeb) && null!=_dsdeb)
					dsdeb = Double.parseDouble(_dsdeb);
				
				hxxmpz = new HPCS_HXXMPZ();
				if (null!=ksnm) hxxmpz.setKsnm(ksnm);
				if (null!=ksmc) hxxmpz.setKsmc(ksmc);
				if (null!=hxlbbm) hxxmpz.setHxlbbm(hxlbbm);
				if (null!=xmlbmc) hxxmpz.setXmlbmc(xmlbmc);
				if (null!=xmbm) hxxmpz.setXmbm(xmbm);
				if (null!=xmmc) hxxmpz.setXmmc(xmmc);
				if (null!=dsjsa) hxxmpz.setDsjsa(dsjsa);
				if (null!=dsdeb) hxxmpz.setDsdeb(dsdeb);
				hxxmpz.setCjr("admin");
				hxxmpz.setZt("1");
				list.add(hxxmpz);
			}
			
			if(null!=list && list.size()>0){
				try {
					DBUtil.createBatch(list);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		} finally{
			if (wb!=null) {
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "success";
	}

	@Override
	public HSSFWorkbook exportHxxm(String ksnm, String xmlbbm) {
		Map<String,Object> map = new HashMap<String, Object>();
		if (null!=ksnm && !"".equals(ksnm)) {
			map.put("ksnm", ksnm);
		}
		if (null!=xmlbbm && !"".equals(xmlbbm)) {
			map.put("xmlbbm", xmlbbm);
		}
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFFont titleFont = wb.createFont(); 
        titleFont.setFontHeight((short) 220);
		HSSFCellStyle style = wb.createCellStyle();
		HSSFDataFormat format = wb.createDataFormat();
		style.setFont(titleFont);   
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setWrapText(true);
        style.setHidden(true);
        style.setDataFormat(format.getFormat("@"));
		HSSFSheet sheet = wb.createSheet("科室核心项目");
		sheet.setDefaultColumnWidth(15);
		HSSFRow row = sheet.createRow((short)0); 
		
		//创建列头
		HSSFCell c0=row.createCell(0);
		HSSFCell c1=row.createCell(1);
		HSSFCell c2=row.createCell(2);
		HSSFCell c3=row.createCell(3);
		HSSFCell c4=row.createCell(4);
		HSSFCell c5=row.createCell(5);
		HSSFCell c6=row.createCell(6);
		HSSFCell c7=row.createCell(7);
		//设置列的格式
		c0.setCellStyle(style);
		c1.setCellStyle(style); 
		c2.setCellStyle(style);
		c3.setCellStyle(style); 
		c4.setCellStyle(style); 
		c5.setCellStyle(style); 
		c6.setCellStyle(style); 
		c7.setCellStyle(style); 
		//设置列头标题
		c0.setCellValue("科室内码");
		c1.setCellValue("科室名称");
		c2.setCellValue("核心类别");
		c3.setCellValue("项目类别");
		c4.setCellValue("项目编码");
		c5.setCellValue("项目名称");
		c6.setCellValue("点数基数A");
		c7.setCellValue("RBRVS点数");
		SqlResult sql = SqlUtil.getSql("hpcs_hxxmpz.query_hxxm", map);
		List<HPCS_HXXMPZ> list=DBUtil.find(sql.getSql(), sql.getParam(), HPCS_HXXMPZ.class);
		if(list!=null&&list.size()>0){
        	for(int i=0;i<list.size();i++){
        		HPCS_HXXMPZ bean=list.get(i);
        		row = sheet.createRow(i+1);
        		HSSFCell c00 = row.createCell(0);
        		HSSFCell c11 = row.createCell(1);
        		HSSFCell c22 = row.createCell(2);
        		HSSFCell c33 = row.createCell(3);
        		HSSFCell c44 = row.createCell(4);
        		HSSFCell c55 = row.createCell(5);
        		HSSFCell c66 = row.createCell(6);
        		HSSFCell c77 = row.createCell(7);
        		c00.setCellStyle(style);
        		c00.setCellValue(bean.getKsnm());
        		c11.setCellStyle(style);
        		c11.setCellValue(bean.getKsmc());
        		c22.setCellStyle(style);
        		c22.setCellValue(bean.getHxlbbm());
        		c33.setCellStyle(style);
        		c33.setCellValue(bean.getXmlbmc());
        		c44.setCellStyle(style);
        		c44.setCellValue(bean.getXmbm());
        		c55.setCellStyle(style);
        		c55.setCellValue(bean.getXmmc());
        		c66.setCellValue(bean.getDsjsa());
        		c77.setCellValue(bean.getDsdeb());
        	}
        }
		return wb;
	}

	@Override
	public List<HPCS_HXXMPZ> findAll() {
		SqlResult sql = SqlUtil.getSql("hpcs_hxxmpz.queryhxxmpz", null);
		return DBUtil.find(sql.getSql(), sql.getParam() ,HPCS_HXXMPZ.class);
	}
	
	@Override
	public List<HPCS_HXXMZD> findxmbmByZd() {
		SqlResult sql = SqlUtil.getSql("hpcs_hxxmpz.queryAll", null);
		return DBUtil.find(sql.getSql(), sql.getParam() ,HPCS_HXXMZD.class);
	}

	@Override
	public List<Combobox> queryXmfl() {
		SqlResult sql = SqlUtil.getSql("hpcs_hxxmpz.queryxmfl", null);
		return DBUtil.find(sql.getSql() ,Combobox.class);
	}

	
}
