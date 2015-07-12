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
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.util.Region;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hpms.jjfp.entity.HPZX_GRZXJC;
import com.hpms.jjfp.service.GrzxjcSer;
import com.hpms.service.impl.BaseSerImpl;
import com.hpms.yljx.entity.HPXT_YGZCXX;
import com.hpms.yljx.vo.Combobox;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

@Service("grzxjcSer")
public class GrzxjcSerImpl extends BaseSerImpl<HPZX_GRZXJC> implements GrzxjcSer {
	
	private static Logger logger = Logger.getLogger(GrzxjcSerImpl.class);
	
	@Override
	public Map<String, Object> find(int page, int rows, Map<String, Object> map) {
		
		return this.findByPage("hpzx_grzxjc.findByPage", page, rows, map);
	}
	
	@Override
	public Map<String, Object> findYgxx(int page, int rows,
			Map<String, Object> map) {
		return this.findByPage("hpzx_grzxjc.find_ygxx", page, rows, map);
	}

	@Override
	public List<Combobox> queryYg() {
		SqlResult sql = SqlUtil.getSql("hpzx_grzxjc.query_yg", null);
		return DBUtil.find(sql.getSql(), Combobox.class);
	}

	@Override
	@Transactional
	public String importGrzxjj(MultipartFile file, Integer nd, Integer yd) {
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
		//先执行删除 再执行导入
		this.deleteByNY(nd, yd);
		HSSFWorkbook wb = null;
		HPZX_GRZXJC grzxjc = null;
		List<HPZX_GRZXJC> list = new ArrayList<HPZX_GRZXJC>();
		String ygbh = null;
		String ygxm = null;
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
		try {
			InputStream is = file.getInputStream();
			wb = new HSSFWorkbook(is); //创建一个excel工作薄
			HSSFSheet sheet = wb.getSheetAt(0); //获得第一个sheet页
			HSSFRow row = sheet.getRow(0); //获得第一行
			int totalRows = sheet.getLastRowNum(); //获得总共多少行
			//int totalCells = row.getPhysicalNumberOfCells(); //获得总共多少列
			for (int i=1; i<=totalRows; i++) {
				//循环出每一行的数据
				row = sheet.getRow(i);
				HSSFCell c1 = row.getCell(1); // 工号
				if (null==c1) continue;
				HSSFCell c2 = row.getCell(2); //职工姓名
				if (null==c2) continue;
				HSSFCell c3 = row.getCell(3); //药比抗菌比奖惩
				if (null==c3) continue;
				HSSFCell c4 = row.getCell(4); //报病卡奖惩
				if (null==c4) continue;
				HSSFCell c5 = row.getCell(5); //劳动纪律奖惩
				if (null==c5) continue;
				HSSFCell c6 = row.getCell(6); //规范服务
				if (null==c6) continue;
				HSSFCell c7 = row.getCell(7); //表扬奖励
				if (null==c7) continue;
				HSSFCell c8 = row.getCell(8); //全面质量奖惩（医技）
				if (null==c8) continue;
				HSSFCell c9 = row.getCell(9); //全面质量奖惩（护理）
				if (null==c9) continue;
				HSSFCell c10 = row.getCell(10); //纠纷投诉
				if (null==c10) continue;
				HSSFCell c11 = row.getCell(11); //护理质量奖惩
				if (null==c11) continue;
				HSSFCell c12 = row.getCell(12); //胃肠镜劳务
				if (null==c12) continue;
				HSSFCell c13 = row.getCell(13); //个人专项-其它
				if (null==c13) continue;
				HSSFCell c14 = row.getCell(14); //门急诊处方不合理用药奖惩
				if (null==c14) continue;
				HSSFCell c15 = row.getCell(15); //大肠癌筛查加班
				if (null==c15) continue;
				HSSFCell c16 = row.getCell(16); //肠道门诊加班费
				if (null==c16) continue;
				
				ygbh = c1.toString().trim();
				ygxm = c2.toString().trim();
				zx1 = c3.toString().trim();
				zx2 = c4.toString().trim();
				
				_zx3 = c5.toString().trim();
				if (!"".equals(_zx3) && null!=_zx3)
					zx3 = Double.parseDouble(_zx3);
				
				_zx4 = c6.toString().trim();
				if (!"".equals(_zx4) && null!=_zx4)
					zx4 = Double.parseDouble(_zx4);
				
				_zx5 = c7.toString().trim();
				if (!"".equals(_zx5) && null!=_zx5)
					zx5 = Double.parseDouble(_zx5);
				
				_zx6 = c8.toString().trim();
				if (!"".equals(_zx6) && null!=_zx6)
					zx6 = Double.parseDouble(_zx6);
				
				_zx7 = c9.toString().trim();
				if (!"".equals(_zx7) && null!=_zx7)
					zx7 = Double.parseDouble(_zx7);
				
				_zx8 = c10.toString().trim();
				if (!"".equals(_zx8) && null!=_zx8)
					zx8 = Double.parseDouble(_zx8);
				
				_zx9 = c11.toString().trim();
				if (!"".equals(_zx9) && null!=_zx9)
					zx9 = Double.parseDouble(_zx9);
				
				_zx10 = c12.toString().trim();
				if (!"".equals(_zx10) && null!=_zx10)
					zx10 = Double.parseDouble(_zx10);
				
				_zx11 = c13.toString().trim();
				if (!"".equals(_zx11) && null!=_zx11)
					zx11 = Double.parseDouble(_zx11);
				
				_zx12 = c14.toString().trim();
				if (!"".equals(_zx12) && null!=_zx12)
					zx12 = Double.parseDouble(_zx12);
				
				_zx13 = c15.toString().trim();
				if (!"".equals(_zx13) && null!=_zx13)
					zx13 = Double.parseDouble(_zx13);
				
				_zx14 = c16.toString().trim();
				if (!"".equals(_zx14) && null!=_zx14)
					zx14 = Double.parseDouble(_zx14);
				
				grzxjc = new HPZX_GRZXJC();
				if (null!=ygbh) {
					grzxjc.setYgbh(ygbh);
					ksnm = queryKsnmByYgbh(ygbh);
				} 
				if (null!=ksnm) grzxjc.setKsnm(ksnm);
				if (null!=ygxm) grzxjc.setYgxm(ygxm);
				if (null!=zx1) grzxjc.setZx1(zx1);
				if (null!=zx2) grzxjc.setZx2(zx2);
				if (null!=zx3) grzxjc.setZx3(zx3);
				if (null!=zx4) grzxjc.setZx4(zx4);
				if (null!=zx5) grzxjc.setZx5(zx5);
				if (null!=zx6) grzxjc.setZx6(zx6);
				if (null!=zx7) grzxjc.setZx7(zx7);
				if (null!=zx8) grzxjc.setZx8(zx8);
				if (null!=zx9) grzxjc.setZx9(zx9);
				if (null!=zx10) grzxjc.setZx10(zx10);
				if (null!=zx11) grzxjc.setZx11(zx11);
				if (null!=zx12) grzxjc.setZx12(zx12);
				if (null!=zx13) grzxjc.setZx13(zx13);
				if (null!=zx14) grzxjc.setZx14(zx14);
				grzxjc.setNd(nd);
				grzxjc.setYd(yd);
				grzxjc.setCjr("admin");
				grzxjc.setZt("1");
				list.add(grzxjc);
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
			System.out.println(e.getMessage());
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
	public HSSFWorkbook exportGrzxjj(String ksnm, Integer nd, Integer yd) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFFont titleFont = wb.createFont(); 
        titleFont.setFontHeight((short) 220);
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(titleFont);   
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);   
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFCellStyle cellStyle = wb.createCellStyle(); 
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setWrapText(true);
        style.setHidden(true);  
		HSSFSheet sheet = wb.createSheet("个人专项奖金");
		sheet.setDefaultColumnWidth(15);
		HSSFRow row = sheet.createRow((short)0); 
		//获取到查询的数据
		List<HPZX_GRZXJC> grList = queryKsRy(ksnm, nd, yd);
		
		/*  合并单元格的代码 ，有问题 被注释起来了， 等有时间再研究
		//存放科室名称的list
		ArrayList<String> ksmcList = new ArrayList<String>();
		//存放科室名称个数的list
		ArrayList<Integer> ksmcNum = new ArrayList<Integer>();
		//临时变量，用来存放科室的个数
		int ksmcTmp = 0;
		
		for (int i=0; i<grList.size(); i++){
			if (0==i) {
				ksmcList.add(grList.get(0).getKsmc());
				ksmcTmp++;
				continue;
			}
			HPZX_GRZXJC gr = grList.get(i);
			if (ksmcList.contains(gr.getKsmc())){
				ksmcTmp++;
			} else {
				ksmcNum.add(ksmcTmp);
				ksmcList.add(grList.get(i).getKsmc());
				ksmcTmp=1;
			}
			if ((grList.size()-1)==i) {
				
				if (!ksmcList.contains(gr.getKsmc())) {
					ksmcNum.add(ksmcTmp);
					ksmcList.add(grList.get(i).getKsmc());
					ksmcNum.add(1);
					break;
				} 
				ksmcTmp++;
				ksmcNum.add(ksmcTmp);
			}
		}
		
		int tmp = 1;
		//合并单元格
		for (int i=0; i<ksmcList.size(); i++){
			HSSFRow rowTmp = sheet.createRow((short)tmp); 
			HSSFCell cell0 = rowTmp.createCell(0);
			cell0.setCellValue(ksmcList.get(i));
			cell0.setCellStyle(cellStyle);
			sheet.addMergedRegion(new CellRangeAddress(tmp,ksmcNum.get(i)+tmp,0,0)); 
			tmp += ksmcNum.get(i) + 1;
		}
		*/
		for (int i=0; i<grList.size(); i++){
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
			
			if (null==grList.get(i).getKsmc()) continue;
			cell0.setCellValue(grList.get(i).getKsmc());
			cell0.setCellStyle(cellStyle);
			
			if (null==grList.get(i).getYgbh()) continue;
			cell1.setCellValue(grList.get(i).getYgbh());
			cell1.setCellStyle(cellStyle);
			
			if (null==grList.get(i).getYgxm()) continue;
			cell2.setCellValue(grList.get(i).getYgxm());
			cell2.setCellStyle(cellStyle);
			
			if (null==grList.get(i).getZx1()) continue;
			cell3.setCellValue(grList.get(i).getZx1());
			cell3.setCellStyle(cellStyle);
			
			if (null==grList.get(i).getZx2()) continue;
			cell4.setCellValue(grList.get(i).getZx2());
			cell4.setCellStyle(cellStyle);
			
			if (null==grList.get(i).getZx3()) continue;
			cell5.setCellValue(grList.get(i).getZx3());
			cell5.setCellStyle(cellStyle);
			
			if (null==grList.get(i).getZx4()) continue;
			cell6.setCellValue(grList.get(i).getZx4());
			cell6.setCellStyle(cellStyle);
			
			if (null==grList.get(i).getZx5()) continue;
			cell7.setCellValue(grList.get(i).getZx5());
			cell7.setCellStyle(cellStyle);
			
			if (null==grList.get(i).getZx6()) continue;
			cell8.setCellValue(grList.get(i).getZx6());
			cell8.setCellStyle(cellStyle);
			
			if (null==grList.get(i).getZx7()) continue;
			cell9.setCellValue(grList.get(i).getZx7());
			cell9.setCellStyle(cellStyle);
			
			if (null==grList.get(i).getZx8()) continue;
			cell10.setCellValue(grList.get(i).getZx8());
			cell10.setCellStyle(cellStyle);
			
			if (null==grList.get(i).getZx9()) continue;
			cell11.setCellValue(grList.get(i).getZx9());
			cell11.setCellStyle(cellStyle);
			
			if (null==grList.get(i).getZx10()) continue;
			cell12.setCellValue(grList.get(i).getZx10());
			cell12.setCellStyle(cellStyle);
			
			if (null==grList.get(i).getZx11()) continue;
			cell13.setCellValue(grList.get(i).getZx11());
			cell13.setCellStyle(cellStyle);
			
			if (null==grList.get(i).getZx12()) continue;
			cell14.setCellValue(grList.get(i).getZx12());
			cell14.setCellStyle(cellStyle);
			
			if (null==grList.get(i).getZx13()) continue;
			cell15.setCellValue(grList.get(i).getZx13());
			cell15.setCellStyle(cellStyle);
			
			if (null==grList.get(i).getZx14()) continue;
			cell16.setCellValue(grList.get(i).getZx14());
			cell16.setCellStyle(cellStyle);
		}
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
		
		//设置列头标题
		c0.setCellValue("科室");
		c1.setCellValue("工号");
		c2.setCellValue("职员姓名");
		c3.setCellValue("药比抗菌比奖惩");
		c4.setCellValue("报病卡奖惩");
		c5.setCellValue("劳动纪律奖惩");
		c6.setCellValue("规范服务");
		c7.setCellValue("表扬奖励");
		c8.setCellValue("全面质量奖惩（医技）");
		c9.setCellValue("全面质量奖惩（护理）");
		c10.setCellValue("纠纷投诉");
		c11.setCellValue("护理质量奖惩");
		c12.setCellValue("胃肠镜劳务");
		c13.setCellValue("个人专项-其它");
		c14.setCellValue("门急诊处方不合理用药奖惩");
		c15.setCellValue("大肠癌筛查加班");
		c16.setCellValue("肠道门诊加班费");
		
		return wb;
	}
	
	/**
	 * 根据年月删除数据
	 * @param nd
	 * @param yd
	 */
	private void deleteByNY(Integer nd, Integer yd) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("nd", nd);
		map.put("yd", yd);
		DBUtil.deleteByProperties(HPZX_GRZXJC.class, map);
	}
	
	/**
	 * 根据员工编号查询科室内码
	 * @param ygbh
	 * @return
	 */
	private String queryKsnmByYgbh(String ygbh) {
		HPXT_YGZCXX yg = DBUtil.findById(HPXT_YGZCXX.class, ygbh);
		String ksnm = yg.getKsnm();
//System.out.println(ksnm);
		return ksnm;
	}
	
	/**
	 * 查询所选年月的科室和每个科室所对应的人数放到List中
	 * 
	 * 个人专项奖金导出的查询
	 * @param nd
	 * @param yd
	 * @return
	 */
	private List<HPZX_GRZXJC> queryKsRy(String ksnm, Integer nd, Integer yd){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ksnm", ksnm);
		map.put("nd", nd);
		map.put("yd", yd);
		SqlResult sql = SqlUtil.getSql("hpzx_grzxjc.queryKsRy", map);
		return DBUtil.find(sql.getSql(), sql.getParam(), HPZX_GRZXJC.class);
	}
}
