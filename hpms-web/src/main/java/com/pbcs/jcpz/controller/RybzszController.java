package com.pbcs.jcpz.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpms.util.UtilStr;
import com.pbcs.jcpz.entity.PBCS_KSBZSZ;
import com.pbcs.jcpz.service.RybzszSer;


/**
 * 
 * @author 高国祥 
 *
 * 创建时间：2015年6月4日-下午3:55:49
 *
 * 类名： 人员编制设置控制器
 *
 * 描述:
 *
 */
@Controller
@RequestMapping("/jcpz/rybzsz")
public class RybzszController {
	
	@Resource
	private RybzszSer rybzszSer;
	
	@RequestMapping
	public String showIndex() {
		return "jcpz/rybzsz";
	}
	
	@RequestMapping("/findListNoPage")
	@ResponseBody
	public Map<String, Object> findListNoPage(HttpServletRequest request) {
		Map<String,Object> map1 = new HashMap<String, Object>();
		List<PBCS_KSBZSZ> list1 = new ArrayList<PBCS_KSBZSZ>();
		Integer _nd = null;
		Integer _yd = null;
		String nd = request.getParameter("nd");
		String yd = request.getParameter("yd");
		
		if (nd!=null && !"".equals(nd)){
			_nd = Integer.parseInt(nd);
		}
		if (yd!=null && !"".equals(yd)) {
			_yd = Integer.parseInt(yd);
		}
		map1.put("nd", _nd);
		map1.put("yd", _yd);
		list1 =  rybzszSer.listByNy(map1);
		return this.findList(request, 1, list1.size(), map1);
	}
	
	/**
	 * 更新数据 对数据进行更新或者是新增
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public String update(String data,String nd, String yd) {
		try {
			PBCS_KSBZSZ ksbzsz = null;
			Integer _nd = null;
			Integer _yd = null;
			//List<String> list2 = new ArrayList<String>();
			Map<String,Object> map = new HashMap<String, Object>();
			if (nd!=null && !"".equals(nd)){
				_nd = Integer.parseInt(nd);
			}
			if (yd!=null && !"".equals(yd)) {
				_yd = Integer.parseInt(yd);
			}
			map.put("nd", _nd);
			map.put("yd", _yd);
			String[] beanArr = data.split(";");
			for (String bean : beanArr) {
				ksbzsz = new PBCS_KSBZSZ();
				String[] foo =  bean.split(",");
				ksbzsz.setKsnm(foo[1]);
				if (UtilStr.isNotNull(foo[2]) && !"null".equals(foo[2])) {
					ksbzsz.setNd(Integer.parseInt(foo[2]));
				}
				if (UtilStr.isNotNull(foo[3]) && !"null".equals(foo[3])) {
					ksbzsz.setYd(Integer.parseInt(foo[3]));
				}
				if (UtilStr.isNotNull(foo[4]) && !"null".equals(foo[4])) {
					ksbzsz.setBzs(Integer.parseInt(foo[4]));
				}
				ksbzsz.setCjr("admin");
				ksbzsz.setZt("1");
				
				rybzszSer.removeById(foo[0]);
				rybzszSer.add(ksbzsz);
			}
			/*List<PBCS_KSBZSZ> list = rybzszSer.listByNy(map);
			for (PBCS_KSBZSZ li : list) {
				list2.add(li.getLsh());
			}
			if (list2.contains(ksbzsz.getLsh())) {
				rybzszSer.remove(ksbzsz);
			}*/
			return "success";
		} catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping(value="/copyLast", method=RequestMethod.POST)
	@ResponseBody
	public String copyLast(String nd, String yd){
		try {
			Integer _nd = null;
			Integer _yd = null;
			PBCS_KSBZSZ bean = null;
		//	List<PBCS_KSBZSZ> listBean = new ArrayList<PBCS_KSBZSZ>();
			Map<String,Object> map = new HashMap<String, Object>();
			if (UtilStr.isNotNull(nd)) {
				_nd = Integer.parseInt(nd);
			}
			if (UtilStr.isNotNull(yd)) {
				_yd = Integer.parseInt(yd);
			}
			if (_yd==1) {
				map.put("nd", _nd-1);
				map.put("yd", _yd+11);
			} else {
				map.put("nd", _nd);
				map.put("yd", _yd-1);
			}
			
			List<PBCS_KSBZSZ> list = rybzszSer.listByNy(map);
			for (PBCS_KSBZSZ li: list) {
				bean = new PBCS_KSBZSZ();
				bean.setKsnm(li.getKsnm());
				bean.setNd(_nd);
				bean.setYd(_yd);
				bean.setBzs(li.getBzs());
				bean.setCjr("admin");
				bean.setZt("1");
				rybzszSer.add(bean);
				//listBean.add(bean);
			}
			//rybzszSer.addBatch(listBean);
			return "success";
		} catch(Exception e) {
			return "error";
		}
	}

	private Map<String, Object> findList(HttpServletRequest request, int page, int rows, Map<String, Object> map) {
		return rybzszSer.find(page, rows, map);
	}


}
