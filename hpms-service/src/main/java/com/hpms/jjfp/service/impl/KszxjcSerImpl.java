package com.hpms.jjfp.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hpms.jjfp.entity.HPZX_GRZXJC;
import com.hpms.jjfp.entity.HPZX_KSZXJC;
import com.hpms.jjfp.service.KszxjcSer;
import com.hpms.service.impl.BaseSerImpl;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service("kszxjcSer")
public class KszxjcSerImpl extends BaseSerImpl<HPZX_KSZXJC> implements KszxjcSer {
	
	private static Logger logger = Logger.getLogger(KszxjcSerImpl.class);
	
	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		return this.findByPage("hpzx_kszxjc.findByPage", page, rows, map);
	}

	@Override
	public Map<String, Object> findKsxx(int page, int rows,
			Map<String, Object> map) {
		return this.findByPage("hpzx_kszxjc.find_ksxx", page, rows, map);
	}

	@Override
	@Transactional
	public String importKszxjj(MultipartFile file, Integer nd, Integer yd) {
		if (file.isEmpty()){
			logger.info("文件为空或没有文件存在");
			return "error";
		}
		String filename = file.getOriginalFilename();
		String prefix = filename.substring(filename.lastIndexOf(".")+1); //获取文件后缀名
		if (!"xls".equalsIgnoreCase(prefix) && !"xlsx".equalsIgnoreCase(prefix)){
			logger.info("传入的文件类型错误");
			return "error";
		}
		
		//删除当前月的数据
		this.deleteKsjjByNY(nd, yd);
		HSSFWorkbook wb = null;
		HPZX_KSZXJC kszxjc = null;
		List<HPZX_KSZXJC> list = new ArrayList<HPZX_KSZXJC>();
		String ksnm = null;
		String zx1 = null;
		String zx2 = null;
		Double zx3 = null;
		Double zx4 = null;
		Double zx5 = null;
		Double zx6 = null;
		Double zx7 = null;
		Double zx8 = null;
		Double zx9 = null;
		Double zx10 = null;
		Double zx11 = null;
		Double zx12 = null;
		Double zx13 = null;
		Double zx14 = null;
		Double zx15 = null;
		Double zx16 = null;
		Double zx17 = null;
		Double zx18 = null;
		Double zx19 = null;
		Double zx20 = null;
		Double zx21 = null;
		String _zx3 = null;
		String _zx4 = null;
		String _zx5 = null;
		String _zx6 = null;
		String _zx7 = null;
		String _zx8 = null;
		String _zx9 = null;
		String _zx10 = null;
		String _zx11 = null;
		String _zx12 = null;
		String _zx13 = null;
		String _zx14 = null;
		String _zx15 = null;
		String _zx16 = null;
		String _zx17 = null;
		String _zx18 = null;
		String _zx19 = null;
		String _zx20 = null;
		String _zx21 = null;
		try {
			InputStream is = file.getInputStream();
			wb = new HSSFWorkbook(is); //把文件流写入excel
			HSSFSheet sheet = wb.getSheetAt(0); //获得第一个sheet页
			HSSFRow row = sheet.getRow(0);  //获取第一行，  注意不是创建第一行 sheet.getRow   not   sheet.createRow
			int totalRows = sheet.getLastRowNum(); //获得总共多少行
			
			for (int i=1; i<=totalRows; i++) {
				
				//循环出每一行的数据
				row = sheet.getRow(i);
				HSSFCell c1 = row.getCell(1);
				if (null==c1) continue;
				HSSFCell c3 = row.getCell(3);
				HSSFCell c4 = row.getCell(4);
				HSSFCell c5 = row.getCell(5);
				HSSFCell c6 = row.getCell(6);
				HSSFCell c7 = row.getCell(7);
				HSSFCell c8 = row.getCell(8);
				HSSFCell c9 = row.getCell(9);
				HSSFCell c10 = row.getCell(10);
				HSSFCell c11 = row.getCell(11);
				HSSFCell c12 = row.getCell(12);
				HSSFCell c13 = row.getCell(13);
				HSSFCell c14 = row.getCell(14);
				HSSFCell c15 = row.getCell(15);
				HSSFCell c16 = row.getCell(16);
				HSSFCell c17 = row.getCell(17);
				HSSFCell c18 = row.getCell(18);
				HSSFCell c19 = row.getCell(19);
				HSSFCell c20 = row.getCell(20);
				HSSFCell c21 = row.getCell(21);
				HSSFCell c22 = row.getCell(22);
				HSSFCell c23 = row.getCell(23);
				
				ksnm = c1.toString().trim();
				zx1 = c3.toString().trim();
				zx2 = c4.toString().trim();
				
				_zx3 = c5.toString().trim();
				if (!"".equals(_zx3) && null!=_zx3) {
					zx3 = Double.parseDouble(_zx3);
				} else {
					zx3 = 0D;
				}
					
				_zx4 = c6.toString().trim();
				if (!"".equals(_zx4) && null!=_zx4) {
					zx4 = Double.parseDouble(_zx4);
				} else {
					zx4 = 0D;
				}
					
				
				_zx5 = c7.toString().trim();
				if (!"".equals(_zx5) && null!=_zx5) {
					zx5 = Double.parseDouble(_zx5);
				} else {
					zx5 = 0D;
				}
					
				
				_zx6 = c8.toString().trim();
				if (!"".equals(_zx6) && null!=_zx6) {
					zx6 = Double.parseDouble(_zx6);
				} else {
					zx6=0D;
				}
					
				
				_zx7 = c9.toString().trim();
				if (!"".equals(_zx7) && null!=_zx7) {
					zx7 = Double.parseDouble(_zx7);
				} else {
					zx7=0D;
				}
					
				
				_zx8 = c10.toString().trim();
				if (!"".equals(_zx8) && null!=_zx8) {
					zx8 = Double.parseDouble(_zx8);
				} else {
					zx8=0D;
				}
					
				
				_zx9 = c11.toString().trim();
				if (!"".equals(_zx9) && null!=_zx9) {
					zx9 = Double.parseDouble(_zx9);
				} else {
					zx9=0D;
				}
					
				
				_zx10 = c12.toString().trim();
				if (!"".equals(_zx10) && null!=_zx10) {
					zx10 = Double.parseDouble(_zx10);
				} else {
					zx10 = 0D;
				}
					
				
				_zx11 = c13.toString().trim();
				if (!"".equals(_zx11) && null!=_zx11) {
					zx11 = Double.parseDouble(_zx11);
				} else {
					zx11 = 0D;
				}
					
				
				_zx12 = c14.toString().trim();
				if (!"".equals(_zx12) && null!=_zx12) {
					zx12 = Double.parseDouble(_zx12);
				} else {
					zx12 = 0D;
				}
					
				
				_zx13 = c15.toString().trim();
				if (!"".equals(_zx13) && null!=_zx13) {
					zx13 = Double.parseDouble(_zx13);
				} else {
					zx13 = 0D;
				}
					
				
				_zx14 = c16.toString().trim();
				if (!"".equals(_zx14) && null!=_zx14) {
					zx14 = Double.parseDouble(_zx14);
				} else {
					zx14 = 0D;
				}
					
				
				_zx15 = c17.toString().trim();
				if (!"".equals(_zx15) && null!=_zx15) {
					zx15 = Double.parseDouble(_zx15);
				} else {
					zx15 = 0D;
				}
					
				
				_zx16 = c18.toString().trim();
				if (!"".equals(_zx16) && null!=_zx16) {
					zx16 = Double.parseDouble(_zx16);
				} else {
					zx16 = 0D;
				}
					
				
				_zx17 = c19.toString().trim();
				if (!"".equals(_zx17) && null!=_zx17) {
					zx17 = Double.parseDouble(_zx17);
				} else {
					zx17 = 0D;
				}
					
				
				_zx18 = c20.toString().trim();
				if (!"".equals(_zx18) && null!=_zx18) {
					zx18 = Double.parseDouble(_zx18);
				} else {
					zx18 = 0D;
				}
					
				
				_zx19 = c21.toString().trim();
				if (!"".equals(_zx19) && null!=_zx19) {
					zx19 = Double.parseDouble(_zx19);
				} else {
					zx19 = 0D;
				}
					
				
				_zx20 = c22.toString().trim();
				if (!"".equals(_zx20) && null!=_zx20) {
					zx20 = Double.parseDouble(_zx20);
				} else {
					zx20 = 0D;
				}
					
				
				_zx21 = c23.toString().trim();
				if (!"".equals(_zx21) && null!=_zx21) {
					zx21 = Double.parseDouble(_zx21);
				} else {
					zx21 =0D;
				}
				
				kszxjc = new HPZX_KSZXJC();
				if (null!=ksnm) kszxjc.setKsnm(ksnm);
				if (null!=zx1) kszxjc.setZx1(zx1);
				if (null!=zx2) kszxjc.setZx2(zx2);
				if (null!=zx3) kszxjc.setZx3(zx3);
				if (null!=zx4) kszxjc.setZx4(zx4);
				if (null!=zx5) kszxjc.setZx5(zx5);
				if (null!=zx6) kszxjc.setZx6(zx6);
				if (null!=zx7) kszxjc.setZx7(zx7);
				if (null!=zx8) kszxjc.setZx8(zx8);
				if (null!=zx9) kszxjc.setZx9(zx9);
				if (null!=zx10) kszxjc.setZx10(zx10);
				if (null!=zx11) kszxjc.setZx11(zx11);
				if (null!=zx12) kszxjc.setZx12(zx12);
				if (null!=zx13) kszxjc.setZx13(zx13);
				if (null!=zx14) kszxjc.setZx14(zx14);
				if (null!=zx15) kszxjc.setZx15(zx15);
				if (null!=zx16) kszxjc.setZx16(zx16);
				if (null!=zx17) kszxjc.setZx17(zx17);
				if (null!=zx18) kszxjc.setZx18(zx18);
				if (null!=zx19) kszxjc.setZx19(zx19);
				if (null!=zx20) kszxjc.setZx20(zx20);
				if (null!=zx21) kszxjc.setZx21(zx21);
				kszxjc.setNd(nd);
				kszxjc.setYd(yd);
				kszxjc.setCjr("admin");
				kszxjc.setZt("1");
				list.add(kszxjc);
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
		} finally {
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
	public HSSFWorkbook exportKszxjj(Integer nd, Integer yd) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFFont titleFont = wb.createFont(); 
        titleFont.setFontHeight((short) 220);
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(titleFont);   
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setWrapText(true);
        style.setHidden(true);  
		HSSFSheet sheet = wb.createSheet("科室专项奖金");
		sheet.setDefaultColumnWidth(15);
		HSSFRow row = sheet.createRow((short)0); 
		
		//获取导出的数据
		List<HPZX_KSZXJC> ksList = queryKsZx(nd, yd);
		
		//创建表头
		HSSFCell c0 = row.createCell(0);
		HSSFCell c1 = row.createCell(1);
		HSSFCell c2 = row.createCell(2);
		HSSFCell c3 = row.createCell(3);
		HSSFCell c4 = row.createCell(4);
		HSSFCell c5 = row.createCell(5);
		HSSFCell c6 = row.createCell(6);
		HSSFCell c7 = row.createCell(7);
		HSSFCell c8 = row.createCell(8);
		HSSFCell c9 = row.createCell(9);
		HSSFCell c10 = row.createCell(10);
		HSSFCell c11 = row.createCell(11);
		HSSFCell c12 = row.createCell(12);
		HSSFCell c13 = row.createCell(13);
		HSSFCell c14 = row.createCell(14);
		HSSFCell c15 = row.createCell(15);
		HSSFCell c16 = row.createCell(16);
		HSSFCell c17 = row.createCell(17);
		HSSFCell c18 = row.createCell(18);
		HSSFCell c19 = row.createCell(19);
		HSSFCell c20 = row.createCell(20);
		HSSFCell c21 = row.createCell(21);
		HSSFCell c22 = row.createCell(22);
		HSSFCell c23 = row.createCell(23);
		
		//设置列的格式
		c0.setCellStyle(style); 
		c1.setCellStyle(style); 
		c2.setCellStyle(style);
		c3.setCellStyle(style); 
		c4.setCellStyle(style); 
		c5.setCellStyle(style); 
		c6.setCellStyle(style); 
		c7.setCellStyle(style); 
		c8.setCellStyle(style); 
		c9.setCellStyle(style); 
		c10.setCellStyle(style);
		c11.setCellStyle(style); 
		c12.setCellStyle(style); 
		c13.setCellStyle(style); 
		c14.setCellStyle(style); 
		c15.setCellStyle(style); 
		c16.setCellStyle(style); 
		c17.setCellStyle(style); 
		c18.setCellStyle(style); 
		c19.setCellStyle(style); 
		c20.setCellStyle(style); 
		c21.setCellStyle(style); 
		c22.setCellStyle(style); 
		c23.setCellStyle(style); 
		
		//设置列头标题
		c0.setCellValue("一级科室");
		c1.setCellValue("科室编号");
		c2.setCellValue("科室名称");
		c3.setCellValue("专项1");
		c4.setCellValue("专项2");
		c5.setCellValue("专项3");
		c6.setCellValue("专项4");
		c7.setCellValue("专项5");
		c8.setCellValue("专项6");
		c9.setCellValue("专项7");
		c10.setCellValue("专项8");
		c11.setCellValue("专项9");
		c12.setCellValue("专项10");
		c13.setCellValue("专项11");
		c14.setCellValue("专项12");
		c15.setCellValue("专项13");
		c16.setCellValue("专项14");
		c17.setCellValue("专项15");
		c18.setCellValue("专项16");
		c19.setCellValue("专项17");
		c20.setCellValue("专项18");
		c21.setCellValue("专项19");
		c22.setCellValue("专项20");
		c23.setCellValue("专项21");
		
		//循环遍历所有的数据，然后在每一行输出
		for (int i=0; i<ksList.size(); i++){
			HSSFRow rowTmp = sheet.createRow((short)i+1); 
			HSSFCell cell0 = rowTmp.createCell(0);
			HSSFCell cell1 = rowTmp.createCell(1);
			HSSFCell cell2 = rowTmp.createCell(2);
			HSSFCell cell3 = rowTmp.createCell(3);
			HSSFCell cell4 = rowTmp.createCell(4);
			HSSFCell cell5 = rowTmp.createCell(5);
			HSSFCell cell6 = rowTmp.createCell(6);
			HSSFCell cell7 = rowTmp.createCell(7);
			HSSFCell cell8 = rowTmp.createCell(8);
			HSSFCell cell9 = rowTmp.createCell(9);
			HSSFCell cell10 = rowTmp.createCell(10);
			HSSFCell cell11 = rowTmp.createCell(11);
			HSSFCell cell12 = rowTmp.createCell(12);
			HSSFCell cell13 = rowTmp.createCell(13);
			HSSFCell cell14 = rowTmp.createCell(14);
			HSSFCell cell15 = rowTmp.createCell(15);
			HSSFCell cell16 = rowTmp.createCell(16);
			HSSFCell cell17 = rowTmp.createCell(17);
			HSSFCell cell18 = rowTmp.createCell(18);
			HSSFCell cell19 = rowTmp.createCell(19);
			HSSFCell cell20 = rowTmp.createCell(20);
			HSSFCell cell21 = rowTmp.createCell(21);
			HSSFCell cell22 = rowTmp.createCell(22);
			HSSFCell cell23 = rowTmp.createCell(23);
			
			if (null==ksList.get(i).getFjmc()) continue;
			cell0.setCellValue(ksList.get(i).getFjmc());
			cell0.setCellStyle(style);
			if (null==ksList.get(i).getKsnm()) continue;
			cell1.setCellValue(ksList.get(i).getKsnm());
			cell1.setCellStyle(style);
			if (null==ksList.get(i).getKsmc()) continue;
			cell2.setCellValue(ksList.get(i).getKsmc());
			cell2.setCellStyle(style);
			if (null==ksList.get(i).getZx1()) continue;
			cell3.setCellValue(ksList.get(i).getZx1());
			cell3.setCellStyle(style);
			if (null==ksList.get(i).getZx2()) continue;
			cell4.setCellValue(ksList.get(i).getZx2());
			cell4.setCellStyle(style);
			if (null==ksList.get(i).getZx3()) continue;
			cell5.setCellValue(ksList.get(i).getZx3());
			cell5.setCellStyle(style);
			if (null==ksList.get(i).getZx4()) continue;
			cell6.setCellValue(ksList.get(i).getZx4());
			cell6.setCellStyle(style);
			if (null==ksList.get(i).getZx5()) continue;
			cell7.setCellValue(ksList.get(i).getZx5());
			cell7.setCellStyle(style);
			if (null==ksList.get(i).getZx6()) continue;
			cell8.setCellValue(ksList.get(i).getZx6());
			cell8.setCellStyle(style);
			if (null==ksList.get(i).getZx7()) continue;
			cell9.setCellValue(ksList.get(i).getZx7());
			cell9.setCellStyle(style);
			if (null==ksList.get(i).getZx8()) continue;
			cell10.setCellValue(ksList.get(i).getZx8());
			cell10.setCellStyle(style);
			if (null==ksList.get(i).getZx9()) continue;
			cell11.setCellValue(ksList.get(i).getZx9());
			cell11.setCellStyle(style);
			if (null==ksList.get(i).getZx10()) continue;
			cell12.setCellValue(ksList.get(i).getZx10());
			cell12.setCellStyle(style);
			if (null==ksList.get(i).getZx11()) continue;
			cell13.setCellValue(ksList.get(i).getZx11());
			cell13.setCellStyle(style);
			if (null==ksList.get(i).getZx12()) continue;
			cell14.setCellValue(ksList.get(i).getZx12());
			cell14.setCellStyle(style);
			if (null==ksList.get(i).getZx13()) continue;
			cell15.setCellValue(ksList.get(i).getZx13());
			cell15.setCellStyle(style);
			if (null==ksList.get(i).getZx14()) continue;
			cell16.setCellValue(ksList.get(i).getZx14());
			cell16.setCellStyle(style);
			if (null==ksList.get(i).getZx15()) continue;
			cell17.setCellValue(ksList.get(i).getZx15());
			cell17.setCellStyle(style);
			if (null==ksList.get(i).getZx16()) continue;
			cell18.setCellValue(ksList.get(i).getZx16());
			cell18.setCellStyle(style);
			if (null==ksList.get(i).getZx17()) continue;
			cell19.setCellValue(ksList.get(i).getZx17());
			cell19.setCellStyle(style);
			if (null==ksList.get(i).getZx18()) continue;
			cell20.setCellValue(ksList.get(i).getZx18());
			cell20.setCellStyle(style);
			if (null==ksList.get(i).getZx19()) continue;
			cell21.setCellValue(ksList.get(i).getZx19());
			cell21.setCellStyle(style);
			if (null==ksList.get(i).getZx20()) continue;
			cell22.setCellValue(ksList.get(i).getZx20());
			cell22.setCellStyle(style);
			if (null==ksList.get(i).getZx21()) continue;
			cell23.setCellValue(ksList.get(i).getZx21());
			cell23.setCellStyle(style);
		}
		
		return wb;
	}
	
	/**
	 * 科室专项奖金导出查询
	 * @param nd
	 * @param yd
	 * @return
	 */
	private List<HPZX_KSZXJC> queryKsZx(Integer nd, Integer yd){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("nd", nd);
		map.put("yd", yd);
		SqlResult sql = SqlUtil.getSql("hpzx_kszxjc.queryKsZx", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), HPZX_KSZXJC.class);
	}
	
	/**
	 * 根据年月删除科室专项奖惩信息
	 * @param nd
	 * @param yd
	 */
	private void deleteKsjjByNY(Integer nd, Integer yd){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("nd", nd);
		map.put("yd", yd);
		DBUtil.deleteByProperties(HPZX_KSZXJC.class, map);
	}

}
