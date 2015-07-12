package com.hpms.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hpms.yljx.entity.HPCS_HXXMPZ;
import com.rongda.common.sql.SqlResult;
import com.rongda.common.util.DBUtil;
import com.rongda.common.util.SqlUtil;

/**
 * 
 * @author 张晓冬 2015-01-12 excel工具类
 * @param <T>
 */
public class ExcelUtil<T> {
	@SuppressWarnings("unchecked")
	public HSSFWorkbook export(String title, String cols, List<Object> data) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFFont titleFont = wb.createFont();
		titleFont.setFontHeight((short) 220);
		HSSFCellStyle style = wb.createCellStyle();
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 设置垂直居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置水平居中
		HSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体加粗
		headerFont.setFontName("Times New Roman"); // 设置字体类型
		headerFont.setFontHeightInPoints((short) 10); // 设置字体大小
		style.setFont(headerFont); // 为标题样式设置字体样式
		HSSFSheet sheet = wb.createSheet(title);
		sheet.setDefaultColumnWidth(15);
		HSSFRow row = sheet.createRow((short) 0);
		// Iterator<T> it = dataset.iterator();
		String array[] = cols.split(",");
		for (int i = 0; i < array.length; i++) {
			HSSFCell hs = row.createCell(i);
			hs.setCellStyle(style);
			hs.setCellValue(array[i]);
		}
		HSSFCellStyle style2 = wb.createCellStyle();
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 设置垂直居中
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置水平居中
		HSSFFont headerFont2 = (HSSFFont) wb.createFont(); // 创建字体样式
		headerFont2.setFontName("Times New Roman"); // 设置字体类型
		headerFont2.setFontHeightInPoints((short) 8); // 设置字体大小
		style2.setFont(headerFont2); // 为标题样式设置字体样式
		for (int i = 0; i < data.size(); i++) {
			row = sheet.createRow(i + 1);
			T t = (T) data.get(i);
			Field[] fields = t.getClass().getDeclaredFields();
			for (int j = 0; j < fields.length; j++) {
				HSSFCell cell = row.createCell(j);
				Field field = fields[j];
				String fieldName = field.getName();
				String getMethodName = "get"
						+ fieldName.substring(0, 1).toUpperCase()

						+ fieldName.substring(1);
				Class<? extends Object> tCls = t.getClass();
				Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
				Object value = getMethod.invoke(t, new Object[] {});
				String textValue = null;
				if (null == value)
				{
					textValue = null;
				} else
				{
					if (value instanceof Date)
					{
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						textValue = sdf.format(date);
					} else
					{
						textValue = value.toString();
					}
				}
				if (textValue != null)
				{
					cell.setCellStyle(style2);
					cell.setCellValue(textValue);
				}
			}
		}
		return wb;
	}
	
	/**
	 * 
	 * @param hwb  excel文件
	 * @param sqlId  调用数据库查询的Id
	 * @param obj  查询的对象
	 */
	@SuppressWarnings("unused")
	public static void RemoveDuplicate(HSSFWorkbook wb, String sqlId, Object obj) {
		//查询和导入excel有关的数据
		SqlResult sql = SqlUtil.getSql(sqlId, null);
		List<Object> list = DBUtil.find(sql.getSql(), sql.getParam() ,obj.getClass());
		
		//excel 操作
		HSSFSheet sheet = wb.getSheetAt(0); //获得excel第一页
		HSSFRow row =sheet.getRow(0); //获得第一行
		int totalRows = sheet.getLastRowNum(); //总共有多少行
		int totalCells = row.getPhysicalNumberOfCells(); //总共有多少列
		for (int i=1; i<totalRows; i++) {
			row = sheet.getRow(i);  //循环获得每一行
			if (null==row) continue; //如果改行为空就跳过
			
			//判断第一列如果有重复的就跳过重复项
			HSSFCell c0 = row.getCell(0);
			if (null==c0) continue;
			String str = c0.toString().trim();
			if (list.contains(str)) continue;
			
			/*//现在的循环 如果有任何一个单元格的数据和数据库查询的数据有重复就不能够插入
			for (int j=0; j<totalCells; j++) {
				HSSFCell cell = row.getCell(j);
				if (cell==null) continue;
				String str = cell.toString().trim();
				if (list.contains(str)) {
					continue; //如果数据库查询的数据和excel读取的数据有重复就跳过
				}
			}*/
		}
	}
}
