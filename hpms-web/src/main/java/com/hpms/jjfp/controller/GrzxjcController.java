package com.hpms.jjfp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import com.hpms.jjfp.entity.HPZX_GRZXJC;
import com.hpms.jjfp.service.GrzxjcSer;
import com.hpms.util.UtilStr;
import com.hpms.yljx.vo.Combobox;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月19日-下午1:57:53
 *
 * 类名： 个人专项奖惩控制器
 *
 * 描述:
 *
 */
@Controller
@RequestMapping("/jjfp/grzxjc")
public class GrzxjcController {
	
	@Resource
	private GrzxjcSer grzxjcSer;
	
	@RequestMapping
	public String showIndex(){
		return "jjfp/grzxjc";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HPZX_GRZXJC item){
		try {
			item.setLsh(null);
			item.setCjr("admin");
			item.setZt("1");
			grzxjcSer.add(item);
		} catch (Exception e){
			return "error";
		}
		return "success";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HPZX_GRZXJC item){
		try {
			HPZX_GRZXJC grzxjc = grzxjcSer.findById(item.getLsh());
			grzxjc.setZx1(item.getZx1());
			grzxjc.setZx2(item.getZx2());
			grzxjc.setZx3(item.getZx3());
			grzxjc.setZx4(item.getZx4());
			grzxjc.setZx5(item.getZx5());
			grzxjc.setZx6(item.getZx6());
			grzxjc.setZx7(item.getZx7());
			grzxjc.setZx8(item.getZx8());
			grzxjc.setZx9(item.getZx9());
			grzxjc.setZx10(item.getZx10());
			grzxjc.setZx11(item.getZx11());
			grzxjc.setZx12(item.getZx12());
			grzxjc.setZx13(item.getZx13());
			grzxjc.setZx14(item.getZx14());
			grzxjcSer.update(grzxjc);
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}
	
	@RequestMapping(value="/deleteById", method=RequestMethod.POST)
	@ResponseBody
	public String deleteById(String lsh){
		try {
			grzxjcSer.removeById(lsh);
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
		String ygxm = request.getParameter("ygxm");
		
		if (!"".equals(_nd) && null!=_nd){
			nd = Integer.parseInt(_nd);
		}
		if (!"".equals(_yd) && null!=_yd){
			yd = Integer.parseInt(_yd);
		}
		
		if (!"".equals(nd) && null!=nd) map.put("nd", nd);
		if (!"".equals(yd) && null!=yd) map.put("yd", yd);
		if (!"".equals(ksnm) && null!=ksnm) map.put("ksnm", ksnm);
		if (!"".equals(ygxm) && null!=ygxm) map.put("ygxm", ygxm);
		return grzxjcSer.find(page, rows, map);
	}
	
	/**
	 * 点击新增，修改 页面出选择人员按钮后弹出的查询
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/findYgxx", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findYgxx(int page, int rows, HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		String ygxm = request.getParameter("ygxm");
		if (!"".equals(ygxm) && null!=ygxm){
			map.put("ygxm", ygxm);
		}
		
		return grzxjcSer.findYgxx(page, rows, map);
	}
	
	@RequestMapping("/queryYG")
	@ResponseBody
	public List<Combobox> queryYg(){
		List<Combobox> list = new ArrayList<Combobox>();
		list = grzxjcSer.queryYg();
		list.add(0, new Combobox("", "---请选择---"));
		return list;
	}
	
	/**
	 * 个人专项奖金导入
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/importGrzxjj", method=RequestMethod.POST)
	@ResponseBody
	public String importGrzxjj(@RequestParam("file") MultipartFile file, HttpServletRequest request){
		String str = null;
		Integer nd = null;
		Integer yd = null;
		try {
			String _nd = request.getParameter("nd");
			String _yd = request.getParameter("yd");
			if (!"".equals(_nd) && null!=_nd){
				nd = Integer.parseInt(_nd);
			}
			if (!"".equals(_yd) && null!=_yd){
				yd = Integer.parseInt(_yd);
			}
			str = grzxjcSer.importGrzxjj(file, nd, yd);
		} catch (Exception e) {
			return str;
		}
		return str;
	}
	
	/**
	 * 个人专项奖金导出
	 */
	@RequestMapping(value="/exportGrzxjj", method=RequestMethod.POST)
	@ResponseBody
	public void exportGrzxjj(HttpServletRequest request, HttpServletResponse response){
		Integer nd = null;
		Integer yd = null;
		String _nd = request.getParameter("nd");
		String _yd = request.getParameter("yd");
		String ksnm = request.getParameter("ksnm");
		if (!"".equals(_nd) && null!=_nd){
			nd = Integer.parseInt(_nd);
		}
		if (!"".equals(_yd) && null!=_yd){
			yd = Integer.parseInt(_yd);
		}
		OutputStream ouputStream = null;
		try {
			ouputStream = response.getOutputStream();
	        response.setContentType("application/vnd.ms-excel");     
	        response.setHeader("Content-disposition", "attachment;filename=" + UtilStr.encodeFileName(request, nd+"年"+yd+"月"+"个人专项奖金.xls"));  
	        HSSFWorkbook wb = grzxjcSer.exportGrzxjj(ksnm,nd, yd);
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
