package com.hpms.jjfp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hpms.jjfp.entity.HPZX_KSZXJC;
import com.hpms.jjfp.service.KszxjcSer;
import com.hpms.util.UtilStr;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月22日-下午1:23:41
 *
 * 类名： 科室专项奖惩控制器
 *
 * 描述:
 *
 */
@Controller
@RequestMapping("/jjfp/kszxjc")
public class KszxjcController {
	
	@Resource
	private  KszxjcSer kszxjcSer;
	
	@RequestMapping
	public String showIndex(){
		return "jjfp/kszxjc";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HPZX_KSZXJC item){
		try {
			item.setLsh(null);
			item.setCjr("admin");
			item.setZt("1");
			kszxjcSer.add(item);
		} catch (Exception e){
			return "error";
		}
		return "success";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HPZX_KSZXJC item){
		try {
			HPZX_KSZXJC kszxjc = kszxjcSer.findById(item.getLsh());
			kszxjc.setZx1(item.getZx1());
			kszxjc.setZx2(item.getZx2());
			kszxjc.setZx3(item.getZx3());
			kszxjc.setZx4(item.getZx4());
			kszxjc.setZx5(item.getZx5());
			kszxjc.setZx6(item.getZx6());
			kszxjc.setZx7(item.getZx7());
			kszxjc.setZx8(item.getZx8());
			kszxjc.setZx9(item.getZx9());
			kszxjc.setZx10(item.getZx10());
			kszxjc.setZx11(item.getZx11());
			kszxjc.setZx12(item.getZx12());
			kszxjc.setZx13(item.getZx13());
			kszxjc.setZx14(item.getZx14());
			kszxjcSer.update(kszxjc);
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}
	
	@RequestMapping(value="/deleteById", method=RequestMethod.POST)
	@ResponseBody
	public String deleteById(String lsh){
		try {
			kszxjcSer.removeById(lsh);
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}
	
	/**
	 * 页面数据的查询方法
	 * @param request
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value="/find", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request, int page, int rows){
		Map<String,Object> map = new HashMap<String, Object>();
		Integer nd = null;
		Integer yd = null;
		String _nd = request.getParameter("nd");
		String _yd = request.getParameter("yd");
		String ksnm = request.getParameter("ksnm");
		if (!"".equals(_nd) && null!=_nd)
		{
			nd = Integer.parseInt(_nd);
		}
		if (!"".equals(_yd) && null!=_yd)
		{
			yd = Integer.parseInt(_yd);
		}
		if (!"".equals(nd) && null!=nd) map.put("nd", nd);
		if (!"".equals(yd) && null!=yd) map.put("yd", yd);
		if (!"".equals(ksnm) && null!=ksnm) map.put("ksnm", ksnm);
		return kszxjcSer.find(page, rows, map);
	}
	
	/**
	 * 点击新增，修改 页面出选择科室按钮后弹出的查询
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findKsxx", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findKsxx(int page, int rows, HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		String ksmc = request.getParameter("ksmc");
		if (!"".equals(ksmc) && null!=ksmc){
			map.put("ksmc", ksmc);
		}
		return kszxjcSer.findKsxx(page, rows, map);
	}
	
	
	/**
	 * 科室专项奖金导入
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/importKszxjj", method=RequestMethod.POST)
	@ResponseBody
	public String importKszxjj(@RequestParam("file") MultipartFile file, HttpServletRequest request){
		String str = null;
		try {
			Integer nd = null;
			Integer yd = null;
			String _nd = request.getParameter("nd");
			String _yd = request.getParameter("yd");
			if (!"".equals(_nd) && null!=_nd)
			{
				nd = Integer.parseInt(_nd);
			}
			if (!"".equals(_yd) && null!=_yd)
			{
				yd = Integer.parseInt(_yd);
			}
			str = kszxjcSer.importKszxjj(file, nd, yd);
		} catch (Exception e) {
			return str;
		}
		return str;
	}
	
	/**
	 * 科室专项奖金导出
	 */
	@RequestMapping(value="/exportKszxjj", method=RequestMethod.POST)
	@ResponseBody
	public void exportKszxjj(HttpServletRequest request, HttpServletResponse response){
		Integer nd = null;
		Integer yd = null;
		String _nd = request.getParameter("nd");
		String _yd = request.getParameter("yd");
		if (!"".equals(_nd) && null!=_nd)
		{
			nd = Integer.parseInt(_nd);
		}
		if (!"".equals(_yd) && null!=_yd)
		{
			yd = Integer.parseInt(_yd);
		}
		OutputStream ouputStream = null;
		try {
			ouputStream = response.getOutputStream();
	        response.setContentType("application/vnd.ms-excel");     
	        response.setHeader("Content-disposition", "attachment;filename=" + UtilStr.encodeFileName(request, nd+"年"+yd+"月"+"科室专项奖金.xls"));  
	        HSSFWorkbook wb = kszxjcSer.exportKszxjj(nd, yd);
	        wb.write(ouputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ouputStream!=null){
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
