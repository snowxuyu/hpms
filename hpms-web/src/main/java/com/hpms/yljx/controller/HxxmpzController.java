package com.hpms.yljx.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hpms.util.UtilStr;
import com.hpms.yljx.entity.HPCS_HXXMPZ;
import com.hpms.yljx.service.HxxmpzSer;
import com.hpms.yljx.service.HxxmzdSer;
import com.hpms.yljx.vo.Combobox;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月14日-下午1:34:55
 *
 * 类名： 科室核心项目配置控制器
 *
 * 描述:
 *
 */
@Controller
@RequestMapping("/yljx/hxxmpz")
public class HxxmpzController {
	
	@Resource
	private HxxmpzSer hxxmpzSer;
	
	@Autowired
	private HxxmzdSer hxxmzdSer;
	
	@RequestMapping
	public String showIndex(){
		return "yljx/hxxmpz";
	}
	
	//主界面的查询
	@RequestMapping(value="/find")
	@ResponseBody
	public Map<String, Object> find(int page, int rows, HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		String ksnm = request.getParameter("ksnm");
		String hxlbbm = request.getParameter("hxlbbm");
		String xmlbbm = request.getParameter("xmlbbm");
		String xmmc = request.getParameter("xmmc");
		if (!"".equals(ksnm) && null!=ksnm)
			map.put("ksnm", ksnm);
		if (!"".equals(hxlbbm) && null!=hxlbbm)
			map.put("hxlbbm", hxlbbm);
		if (!"".equals(xmlbbm) && null!=xmlbbm)
			map.put("xmlbbm", xmlbbm);
		if (!"".equals(xmmc) && null!=xmmc)
			map.put("xmmc", xmmc);
		return hxxmpzSer.find(page, rows, map);
	}
	
	@RequestMapping(value="/mhQueryHxxm")
	@ResponseBody
	public Map<String, Object> mhQueryHxxm(int page, int rows, HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		String xmmc = request.getParameter("xmmc");
		if (!"".equals(xmmc) && null!=xmmc)
			map.put("xmmc", xmmc);
		return hxxmpzSer.mhQueryHxxm(page, rows, map);
	}
	
	//点击新增界面的查询，查询核心项目字典 根据项目名称模糊查询
	@RequestMapping(value="/findhxxmzd")
	@ResponseBody
	public Map<String, Object> findhxxmzd(int page, int rows, HttpServletRequest request){
		String xmmc = request.getParameter("xmmc");
		Map<String, Object> map = new HashMap<String, Object>();
		if (!"".equals(xmmc) && null!=xmmc)
			map.put("xmmc", xmmc);
		return hxxmzdSer.findHxxmzd(page, rows, map);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HPCS_HXXMPZ item){
System.out.println("流水号:"+item.getLsh()+","+"科室内码:"+item.getKsnm()+","+"项目编码:"+item.getXmbm()+","+"项目名称:"+item.getXmmc()+","+"核心类别编码:"+item.getHxlbbm()+","+"项目类别编码:"+item.getXmlbbm()+","+"项目类型:"+item.getXmlx()+","+"A:"+item.getDsjsa()+","+"B:"+item.getDsdeb());
		try{
			item.setLsh(null);
			item.setCjr("admin");
			item.setZt("1");
			hxxmpzSer.add(item);
		} catch(Exception e) {
			return "error";
		}
		return "success";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(HPCS_HXXMPZ item){
		try{
			HPCS_HXXMPZ hxxmpz = hxxmpzSer.findById(item.getLsh());
			//hxxmpz.setDsjsa(item.getDsjsa());
			hxxmpz.setDsdeb(item.getDsdeb());
			hxxmpzSer.update(hxxmpz);
		} catch (Exception e){
			return "error";
		}
		return "success";
	}
	
	@RequestMapping(value="/removeById", method=RequestMethod.POST)
	@ResponseBody
	public String deleteById(String lsh){
		try{
			hxxmpzSer.removeById(lsh);
		} catch (Exception e){
			return "error";
		}
		return "success";
	}
	
	/**
	 * 查询项目分类
	 * @return
	 */
	@RequestMapping("/queryXmfl")
	@ResponseBody
	public List<Combobox> queryXmfl(){
		List<Combobox> list = hxxmpzSer.queryXmfl();
		list.add(0, new Combobox("","---请选择---"));
		return list;
	}
	
	/**
	 * 查询核心类别
	 * @return
	 */
	@RequestMapping(value="/queryHxlb")
	@ResponseBody
	public List<Combobox> queryHxlb(){
		List<Combobox> list = hxxmpzSer.queryHxlb();
		list.add(0, new Combobox("","---请选择---"));
		return list;
	}
	
	/**
	 * 查询项目标识
	 * @return
	 */
	@RequestMapping(value="/queryXmbz")
	@ResponseBody
	public List<Combobox> queryXmbz(){
		List<Combobox> list = hxxmpzSer.queryXmbz();
		list.add(0, new Combobox("","---请选择---"));
		return list;
	}
	
	/**
	 * 查询所有项目类别
	 * @return
	 */
	@RequestMapping(value="/queryAllXmlb")
	@ResponseBody
	public List<Combobox> queryAllXmlb() {
		List<Combobox> list = hxxmpzSer.queryAllXmlb();
		list.add(0, new Combobox("","---请选择---"));
		return list;
	}
	
	/**
	 * 级联 查询项目类别
	 * @return
	 */
	@RequestMapping(value="/queryXmlb")
	@ResponseBody
	public List<Combobox> queryXmlb(HttpServletRequest request){
		String fjbm = request.getParameter("fjbm");
		List<Combobox> list = hxxmpzSer.queryXmlb(fjbm);
		list.add(0, new Combobox("","---请选择---"));
		return list;
	}
	
	/**
	 * 科室核心项目导入
	 * @return
	 */
	@RequestMapping(value="/importHxxm", method=RequestMethod.POST)
	@ResponseBody
	public String importHxxm(@RequestParam("file") MultipartFile file, String ksnm){
		String str = null;
		try{
			str = hxxmpzSer.importHxxm(file,ksnm);
		} catch (Exception e){
			return str;
		}
		return str;
	}
	
	@RequestMapping(value="/exportHxxm", method=RequestMethod.POST)
	@ResponseBody
	public void exportHxxm(String ksnm, String xmlbbm, HttpServletRequest request, HttpServletResponse response){
		  OutputStream ouputStream = null;
		try {
			ouputStream = response.getOutputStream(); 
	        response.setContentType("application/vnd.ms-excel");     
	        response.setHeader("Content-disposition", "attachment;filename=" + UtilStr.encodeFileName(request, "科室核心项目.xls"));    
			HSSFWorkbook wb = hxxmpzSer.exportHxxm(ksnm, xmlbbm);
			wb.write(ouputStream);
		} catch (Exception e){
		} finally{
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
