package com.hpms.jjfp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.jjfp.entity.HPCS_CLJSSZ;
import com.hpms.jjfp.service.CljsszSer;

/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年1月11日-下午3:28:14
 *
 * 类名： 常量基数设置控制器
 *
 * 描述:
 *
 */
@Controller
@RequestMapping("/jjfp/cljssz")
public class CljsszController {
	
	@Resource
	private CljsszSer cljsszSer;
	
	@RequestMapping
	public String showIndex(){
		return "jjfp/cljssz";
	}
	
	/**
	 * 常量明细
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showClmx")
	public String showClmx(HttpServletRequest request, Model model) {
		String nd = request.getParameter("nd");
		String yd = request.getParameter("yd");
		model.addAttribute("nd", nd);
		model.addAttribute("yd", yd);
		return "jjfp/showclmx";
	}
	
	@RequestMapping(value="/findAll", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findAll(int page, int rows){
		return cljsszSer.findAll(page, rows);
	}
	
	@RequestMapping(value="/find", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> find(HttpServletRequest request, int page, int rows){

		Map<String,Object> map = new HashMap<String, Object>();
		Integer nd = null;
		Integer yd = null;
		String _nd = request.getParameter("nd");
		String _yd = request.getParameter("yd");
		if (!"".equals(_nd) && null!=_nd){
			nd = Integer.parseInt(_nd);
		}
		if (!"".equals(_yd) && null!=_yd) {
			yd = Integer.parseInt(_yd);
		}
		if (null!=nd)
			map.put("nd", nd);
		if (null!=yd)
			map.put("yd", yd);
		map = cljsszSer.find(page, rows, map);
		 return map;
	}
	
	/*//List查询
	@RequestMapping(value="/findListByNY", method=RequestMethod.POST)
	public String findListByNy(HttpServletRequest request, Model model){
		Integer nd = null;
		Integer yd = null;
		String _nd = request.getParameter("nd");
		String _yd = request.getParameter("yd");
		if (!"".equals(_nd) && null!=_nd){
			nd = Integer.parseInt(_nd);
		}
		if (!"".equals(_yd) && null!=_yd) {
			yd = Integer.parseInt(_yd);
		}
		List<HPCS_CLJSSZ> list = cljsszSer.findListByNy(nd, yd);
		model.addAttribute("list", list);
		return "jjfp/editcljssz";
	}*/
	
	@RequestMapping(value="/findByNYNoPageRows", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findByNyNoPageRows(HttpServletRequest request){
		Integer nd = null;
		Integer yd = null;
		String _nd = request.getParameter("nd");
		String _yd = request.getParameter("yd");
		if (!"".equals(_nd) && null!=_nd){
			nd = Integer.parseInt(_nd);
		}
		if (!"".equals(_yd) && null!=_yd) {
			yd = Integer.parseInt(_yd);
		}
		List<HPCS_CLJSSZ> list = cljsszSer.findListByNy(nd, yd);
		return this.findByNY(request, 1, list.size());
	}
	
	@RequestMapping(value="/findByNY", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> findByNY(HttpServletRequest request, int page, int rows){
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> map2 = new HashMap<String, Object>();
		HashMap<String, Object> footMap = new HashMap<String, Object>();
		ArrayList<Object> list = new ArrayList<Object>();
		Integer nd = null;
		Integer yd = null;
		String _nd = request.getParameter("nd");
		String _yd = request.getParameter("yd");
		if (!"".equals(_nd) && null!=_nd){
			nd = Integer.parseInt(_nd);
		}
		if (!"".equals(_yd) && null!=_yd) {
			yd = Integer.parseInt(_yd);
		}
		if (null!=nd){
			map2.put("nd", nd);
		}
		if (null!=yd){
			map2.put("yd", yd);
		}
		footMap.put("ksfl", "合计");
		footMap.put("kscl", cljsszSer.sumKscl(nd, yd).get(0).getKscl());
		list.add(footMap);
		map = cljsszSer.findByNY(page, rows, map2);
		if (map.get("total").equals(0)) {
			//说明没有数据要进行新增
			List<HPCS_CLJSSZ> listksnm = cljsszSer.queryKsnm();
			HPCS_CLJSSZ item = new HPCS_CLJSSZ();
			for (int i=0; i<listksnm.size(); i++) {
				try {
					item.setKsnm(listksnm.get(i).getKsnm());
					item.setLsh(null);
					item.setCjr("superadmin");
					item.setZt("1");
					item.setNd(nd);
					item.setYd(yd);
					cljsszSer.add(item);
				} catch (Exception e) {
					System.out.println("=========>>新增报错<<========");
					e.printStackTrace();
				}
			}
			map = cljsszSer.findByNY(page, rows, map2);
		}
		map.put("footer", list);
		return map;
		
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	@ResponseBody
	public String deleteById(String lsh){
		try {
			cljsszSer.removeById(lsh);
		} catch (Exception e){
			return "error";
		}
		return "success";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	@ResponseBody
	public String add(HPCS_CLJSSZ item){
		try {
			//List<HPCS_CLJSSZ> list = new ArrayList<HPCS_CLJSSZ>();
			item.setLsh(null);
			item.setCjr("superadmin");
			item.setZt("1");
			cljsszSer.add(item);
			//list.add(item);
			//cljsszSer.addBatch(list);;
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(String lshs, String kscls){
		String[] lshArr = lshs.split(",");
		String[] ksclArr = kscls.split(",");
		try {
			for (int i=0; i<lshArr.length; i++) {
				HPCS_CLJSSZ cljssz = cljsszSer.findById(lshArr[i]);
				cljssz.setKscl(Double.valueOf(ksclArr[i]));
				cljsszSer.update(cljssz);
			}
		} catch (Exception e) {
			return "error";
		}
		return "success";
	}
}
